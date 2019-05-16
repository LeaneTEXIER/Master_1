#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include "../include/vol.h"
#include "../include/driver.h"
#include "../include/mbr.h"
#include "../include/hwconfig.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"

struct superbloc_s super_bloc;
int current_volume = -1;

int cyl_of_bloc(unsigned int v, unsigned int b){
  assert(mbr.magic == MAGIC);
  assert(mbr.nb_volume > v);
  assert(mbr.vol[v].nb_sector > b);
  int nbCyl, nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbCyl=_in(HDA_DATAREGS)<<8;
  nbCyl+=_in(HDA_DATAREGS+1);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);
  int c;
  c = (mbr.vol[v].first_sector + b)/nbSec + mbr.vol[v].first_cylinder;
  assert(c < nbCyl);
  return c;
}

int sec_of_vol(unsigned int v, unsigned int b){
  assert(mbr.magic == MAGIC);
  assert(mbr.nb_volume > v);
  assert(mbr.vol[v].nb_sector > b);
  int nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);
  int s;
  s = (mbr.vol[v].first_sector + b)%nbSec;
  return s;
}

void read_bloc(unsigned int vol, unsigned int nbloc, unsigned char *buffer){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  read_bloc_n(vol, nbloc, buffer, sectorsize);
}

void read_bloc_n(unsigned int vol, unsigned int nbloc, unsigned char *buffer, unsigned int n){
  read_sector_n(cyl_of_bloc(vol, nbloc), sec_of_vol(vol, nbloc), buffer, n);
}

void write_bloc(unsigned int vol, unsigned int nbloc, const unsigned char *buffer){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  write_bloc_n(vol, nbloc, buffer, sectorsize);
}

void write_bloc_n(unsigned int vol, unsigned int nbloc, const unsigned char *buffer, unsigned int n){
  write_sector_n(cyl_of_bloc(vol, nbloc), sec_of_vol(vol, nbloc), buffer, n);
}

void format_vol(unsigned int vol){
  format_sector(mbr.vol[vol].first_cylinder, mbr.vol[vol].first_sector, mbr.vol[vol].nb_sector, 0);
}

void init_volume(char * name, int serial, int volume){
  int nb_volumes = mbr.nb_volume;
  int nb_sectors_vol = mbr.vol[volume].nb_sector;
  if(volume>=nb_volumes || volume<0){
    printf("Numero de volume incorrect.\n");
    exit(EXIT_FAILURE);
  }
  struct superbloc_s s;
  struct free_bloc_s f;
  s.serial = serial;
  strncpy(s.name, name, 32);
  s.first_inode = BLOC_NULL;
  s.first_free_bloc = 1;
  s.nb_free_bloc = nb_sectors_vol-1;
  s.magic = SB_MAGIC;
  write_bloc_n(volume, 0, (unsigned char *)&s, sizeof(s));
  for(int i = 1; i<nb_sectors_vol-1; i++){
    f.next = i + 1;
    f.magic = FB_MAGIC;
    write_bloc_n(volume, i, (unsigned char *)&f, sizeof(f));
  }
  if(nb_sectors_vol-1 > 0){
    f.next = BLOC_NULL;
    f.magic = FB_MAGIC;
    write_bloc_n(volume, nb_sectors_vol-1, (unsigned char *)&f, sizeof(f));
  }
}

int load_super(unsigned int vol){
  int nb_sectors = mbr.nb_volume;
  if(vol>=nb_sectors || vol<0){
    printf("Numero de volume incorrect.\n");
    exit(EXIT_FAILURE);
  }
  read_bloc_n(vol, 0, (unsigned char*)(&super_bloc), sizeof(super_bloc));
  current_volume = vol;
  return super_bloc.magic = SB_MAGIC;
}

void save_super(){
  write_bloc_n(current_volume, 0, (unsigned char*)(&super_bloc), sizeof(super_bloc));
}

unsigned int new_bloc(){
  int res = super_bloc.first_free_bloc;
  struct free_bloc_s free;
  if(res!=BLOC_NULL){
    read_bloc_n(current_volume, res,(unsigned char*)(&free),sizeof(free));
    assert(free.magic==FB_MAGIC);
    super_bloc.first_free_bloc = free.next;
    super_bloc.nb_free_bloc --;
  }
  save_super();
  return res;
}

void free_bloc(unsigned int bloc){
  //Verification que le bloc a libéré est bien occupé
  int free_bloc_nb = super_bloc.first_free_bloc;
  struct free_bloc_s frees;
  while(free_bloc_nb!=BLOC_NULL){
    if(free_bloc_nb==bloc){
      printf("Impossible de libérer un bloc déjà libre!\n");
      exit(EXIT_FAILURE);
    }
    read_bloc_n(current_volume, free_bloc_nb,(unsigned char*)(&frees),sizeof(frees));
    free_bloc_nb=frees.next;
  }
  //Liberation
  struct free_bloc_s free;
  free.next = super_bloc.first_free_bloc;
  free.magic = FB_MAGIC;
  super_bloc.first_free_bloc = bloc;
  super_bloc.nb_free_bloc ++;
  write_bloc_n(current_volume, bloc,(unsigned char*)(&free),sizeof(free));
  save_super();
}
