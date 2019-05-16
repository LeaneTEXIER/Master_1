#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include "../include/driver.h"
#include "../include/hwconfig.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"

void empty_it(){
  return;
}

void init(){
  unsigned int i;
  /* init hardware */
  if(init_hardware(INIFILENAME) == 0) {
	   fprintf(stderr, "Error in hardware initialization.\n");
     exit(EXIT_FAILURE);
  }
  /* Interreupt handlers */
  for(i=0; i<16; i++){
    IRQVECTOR[i] = empty_it;
  }
  /* Allows all IT */
  _mask(1);
}

void my_seek(unsigned int cylinder, unsigned int sector){
  //Verification du numero de cylindre et de secteur
  int nbCyl, nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbCyl=_in(HDA_DATAREGS)<<8;
  nbCyl+=_in(HDA_DATAREGS+1);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);
  if(cylinder<0 || cylinder>=nbCyl){
    printf("Numero de cylindre incorrect.\n");
    exit(EXIT_FAILURE);
  }
  if(sector<0 || sector>=nbSec){
    printf("Numero de secteur incorrect.\n");
    exit(EXIT_FAILURE);
  }
  //Seek
  _out(HDA_DATAREGS, (cylinder & 0xFF00)>>8);
  _out(HDA_DATAREGS+1, (cylinder & 0xFF));
  _out(HDA_DATAREGS+2, (sector & 0xFF00)>>8);
  _out(HDA_DATAREGS+3, (sector & 0xFF));
  _out(HDA_CMDREG, CMD_SEEK);
  _sleep(HDA_IRQ);
}

void read_sector_n(unsigned int cylinder, unsigned int sector, unsigned char * buffer, int n){
  // Recuperation de la taille du secteur
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  // Verification taille correct
  if(n<0 || n >sectorsize){
    printf("Size incorrect.\n");
    exit(EXIT_FAILURE);
  }
  // Rempli de 0 le buffer
  for(int i=0; i<sizeof(buffer); i++){
     buffer[i] = 0;
  }
  my_seek(cylinder,sector);
  _out(HDA_DATAREGS, 0);
  _out(HDA_DATAREGS+1, 1);
  _out(HDA_CMDREG, CMD_READ);
  _sleep(HDA_IRQ);
  for(int i=0; i<n; i++){
    buffer[i] = MASTERBUFFER[i];
  }
}

void read_sector(unsigned int cylinder, unsigned int sector, unsigned char * buffer){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  read_sector_n(cylinder, sector, buffer, sectorsize);
}

void write_sector_n(unsigned int cylinder, unsigned int sector, const unsigned char * buffer, int n){
  // Recuperation de la taille du secteur
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  // Verification taille correct
  if(n<0 || n >sectorsize){
    printf("Size incorrect.\n");
    exit(EXIT_FAILURE);
  }
  // Remplissage MASTERBUFFER
  for(int i=0; i<n; i++){
    MASTERBUFFER[i] = buffer[i];
  }
  for(int i=n; i<sectorsize; i++){
    MASTERBUFFER[i] = 0;
  }
  my_seek(cylinder,sector);
  _out(HDA_DATAREGS, 0);
  _out(HDA_DATAREGS+1, 1);
  _out(HDA_CMDREG, CMD_WRITE);
  _sleep(HDA_IRQ);
}

void write_sector(unsigned int cylinder, unsigned int sector, const unsigned char * buffer){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  write_sector_n(cylinder, sector, buffer, sectorsize);
}

void format_sector(unsigned int cylinder, unsigned int sector, unsigned int nsector, unsigned int value){
  int nbSec;
  _out(HDA_CMDREG, CMD_DSKINFO);
  nbSec=_in(HDA_DATAREGS+2)<<8;
  nbSec+=_in(HDA_DATAREGS+3);
  if(nsector<0 || nsector>nbSec){
    printf("Nombre de secteur incorrect.\n");
    exit(EXIT_FAILURE);
  }
  my_seek(cylinder,sector);
  _out(HDA_DATAREGS, (nsector & 0xFF00)>>8);
  _out(HDA_DATAREGS+1, (nsector & 0xFF));
  _out(HDA_DATAREGS+2, (value & 0xFF000000)>>24);
  _out(HDA_DATAREGS+3, (value & 0xFF0000)>>16);
  _out(HDA_DATAREGS+4, (value & 0xFF00)>>8);
  _out(HDA_DATAREGS+5, (value & 0xFF));
  _out(HDA_CMDREG, CMD_FORMAT);
  for(int i=0; i<nsector; i++){
    _sleep(HDA_IRQ);
  }
}
