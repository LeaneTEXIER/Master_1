#include <stdlib.h>
#include <stdio.h>
#include "../include/driver.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"
#include <ctype.h>

void dumps(unsigned int cylindre, unsigned int secteur){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);

  unsigned char buff[sectorsize];
  //Test d'écriture avant lecture
  // for (int l = 0; l < sectorsize; l++){
  //   buff[l]=l;
  // }
  // write_sector(cylindre, secteur, buff);

  read_sector(cylindre, secteur, buff);
  for(int i=0; i<sectorsize; i+=16){
    for(int j=0; j<16; j++){
      printf("%02x ", buff[i+j]);
    }
    printf("\n" );
  }
}

int main(int argc, char **argv){
  if(argc != 3) {
    printf("Usage: ./bin/dumps <cylindre> <secteur> \n");
    exit(EXIT_FAILURE);
  }
  unsigned int cylindre = atoi(argv[1]);
  unsigned int secteur = atoi(argv[2]);

  init();
  dumps(cylindre, secteur);

  exit(EXIT_SUCCESS);
}
