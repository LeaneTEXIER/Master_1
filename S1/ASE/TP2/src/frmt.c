#include <stdlib.h>
#include <stdio.h>
#include <getopt.h>
#include "../include/driver.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"

void frmt(int c){
  //Recupere le nombre de cylindres et de secteurs d'un disque
  int nbCyl, nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbCyl=_in(HDA_DATAREGS)<<8;
  nbCyl+=_in(HDA_DATAREGS+1);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);

  //Formate le disque
  if (c==-1){
    for(int cylindre=0; cylindre<nbCyl; cylindre++){
      format_sector(cylindre, 0, nbSec, 0);
    }
  }
  else{
    for(int cylindre=nbCyl-1; cylindre>=0; cylindre--){
      for (int secteur=nbSec-1; secteur>=0; secteur--){
        format_sector(cylindre, secteur, 1, 0);
      }
    }
  }
}

int main(int argc, char **argv){
  int c = getopt(argc, argv, "r");
  init();
  frmt(c);
  printf("Formatage effectué avec suucès\n");
  exit(EXIT_SUCCESS);
}
