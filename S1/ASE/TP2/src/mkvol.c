#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../include/mbr.h"
#include "../include/driver.h"
#include "../include/hwconfig.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"

void mkvol(int first_cyl, int first_sec, int nb_sec){
  struct vol_desc_s volume;
  struct vol_desc_s v;
  int deb_vol, fin_vol, deb_vol_new, fin_vol_new, fin_vol_prec;
  int dern_bloc;
  int find = 0;
  int i = 0;
  int nb_blocs_libres = 0;

  int nbCyl, nbSec, sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbCyl=_in(HDA_DATAREGS)<<8;
  nbCyl+=_in(HDA_DATAREGS+1);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);

  //Nombre maximal de volumes atteint
  if(mbr.nb_volume==NBVOL){
    printf("Nombre de volumes maximal atteint.\n");
    exit(EXIT_FAILURE);
  }

  //Calcul du dernier bloc
  dern_bloc = nbCyl * nbSec - 1;

  //Cas où l'utilisateur a donné le premier secteur et premier cylindre => Verification que c'est possible
  if(first_cyl!=-1 && first_sec!=-1){
    //Impossible d'écrire à l'enroit du mbr
    if(first_cyl==0 && first_sec==0){
      printf("Impossible de créer le volume car il est impossible d'écrire à l'endroit du mbr.\n");
      exit(EXIT_FAILURE);
    }
    deb_vol_new = first_cyl * nbSec + first_sec;
    fin_vol_new = deb_vol_new + nb_sec - 1;
    // Si le tableau sort du disque
    if(fin_vol_new>dern_bloc){
      printf("Impossible de créer le volume. Il est en dehors du disque.\n");
      exit(EXIT_FAILURE);
    }
    // Verifie qu'il n'y a pas de superposition entre 2 volumes et trouve où insérer le nouveau volume
    fin_vol_prec = 0;
    while(i<mbr.nb_volume && !find){
      v = mbr.vol[i];
      deb_vol = v.first_cylinder * nbSec + v.first_sector;
      fin_vol = deb_vol + v.nb_sector - 1;
      if (!((fin_vol_new<deb_vol) || (deb_vol_new>fin_vol))){
        printf("Impossible de créer le volume dû à la superposition avec un autre volume.\n");
        exit(EXIT_FAILURE);
      }
      if(fin_vol_prec<deb_vol_new && fin_vol_new<deb_vol){
        find = 1;
      }
      else{
        fin_vol_prec = fin_vol;
        i++;
      }
    }
  }
  else{
    //Cherche un endroit où on a b blocs libres
    fin_vol_prec = 0;
    while(i<mbr.nb_volume && !find){
      v = mbr.vol[i];
      deb_vol = v.first_cylinder * nbSec + v.first_sector;
      fin_vol = deb_vol + v.nb_sector - 1;
      if(deb_vol-fin_vol_prec-1>=nb_sec){
        find = 1;
        first_sec = (fin_vol_prec+1)%nbSec;
        first_cyl = (fin_vol_prec+1)/nbSec;
      }
      else{
        nb_blocs_libres += deb_vol-fin_vol_prec-1;
        fin_vol_prec = fin_vol;
        i++;
      }
    }
    if(dern_bloc-fin_vol_prec>=nb_sec){
      find = 1;
      first_sec = (fin_vol_prec+1)%nbSec;
      first_cyl = (fin_vol_prec+1)/nbSec;
    }
    else{
      nb_blocs_libres += dern_bloc-fin_vol_prec;
    }
    if(!find){
      if(nb_blocs_libres<nb_sec){
        printf("Impossible de créér le volume par manque de place sur le disque.\n");
        exit(EXIT_FAILURE);
      }
      else{
        printf("Impossible de créer le volume sans déplacé les volumes existants.\n");
        char d = '0';
        while (d!='o' && d!='n'){
          printf("Voulez-vous déplacer les volumes? o pour oui, n pour non\n");
          scanf(" %c", &d);
        }
        if(d=='o'){
          unsigned char buff[sectorsize];
          unsigned char buff_0[sectorsize];
          for(int i=0; i<sectorsize; i++){
             buff_0[i] = 0;
          }
          printf("Volumes déplacés pour pouvoir insérer le nouveau volume.\n");
          first_sec = 1;
          first_cyl = 0;
          for(int i = 0; i<mbr.nb_volume; i++){
            v = mbr.vol[i];
            // Décalage de ce qu'il y a dans le volume
            for(int j = 0; j<v.nb_sector; j++){
              int cyl = (v.first_sector + j)/nbSec + v.first_cylinder;
              int sec = (v.first_sector + j)%nbSec;
              read_sector(cyl, sec, buff);
              write_sector(cyl, sec, buff_0);
              cyl = (first_sec + j)/nbSec + first_cyl;
              sec = (first_sec + j)%nbSec;
              write_sector(cyl, sec, buff);
            }
            v.first_sector = first_sec;
            v.first_cylinder = first_cyl;
            mbr.vol[i] = v;
            first_sec = (v.first_sector + v.nb_sector + 1)%nbSec;
            first_cyl = (v.first_sector + v.nb_sector + 1)/nbSec + v.first_cylinder;
          }
        }
        else{
          printf("Volume non créé.\n");
          exit(EXIT_FAILURE);
        }
      }
    }
  }
  volume.first_sector = first_sec;
  volume.first_cylinder = first_cyl;
  volume.nb_sector = nb_sec;
  volume.type = BASE;
  for(int j = mbr.nb_volume + 1; j>i; j--){
    mbr.vol[j] = mbr.vol[j-1];
  }
  mbr.vol[i]=volume;
  mbr.nb_volume++;
  printf("Nouveau volume créé en cylindre %d et secteur %d\n", first_cyl, first_sec);

  save_mbr();
}

int main(int argc, char **argv){
  int first_sec = -1;
  int first_cyl = -1;
  int nb_sec = -1;

  init();
  load_mbr();

  /* Récupération du nombre de cylindres et de secteurs sur le disque */
  int nbCyl, nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbCyl=_in(HDA_DATAREGS)<<8;
  nbCyl+=_in(HDA_DATAREGS+1);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);

  int c;
  while ((c = getopt(argc, argv, "b:c:s:")) != -1) {
    switch (c) {
        case 'b':
            nb_sec = atol(optarg);
            if(nb_sec<=0 || nb_sec >= nbCyl*nbSec){
              printf("Le nombre de blocs, donné par l'option -b, doit être positif et inférieur à %d.\n", nbCyl*nbSec);
              exit(EXIT_FAILURE);
            }
            break;
        case 'c':
            first_cyl = atol(optarg);
            if(first_cyl<0 || first_cyl>=nbCyl){
              printf("Le numero du premier cylindre, donné par l'option -c, doit être positif et inférieur à %d.\n", nbCyl);
              exit(EXIT_FAILURE);
            }
            break;
        case 's':
            first_sec = atol(optarg);
            if(first_sec<0 || first_sec>=nbSec){
              printf("Le numero du premier secteur, donné par l'option -s, doit être positif et inférieur à %d.\n", nbSec);
              exit(EXIT_FAILURE);
            }
            break;
    }
  }

  if(nb_sec == -1){
    printf("Usage: ./bin/mkvol -b <nb_secteurs> ou ./bin/mkvol -b <nb_secteurs> -c <first_cylindre> -s <first_secteur> \n");
    exit(EXIT_FAILURE);
  }

  mkvol(first_cyl, first_sec, nb_sec);

  exit(EXIT_SUCCESS);
}
