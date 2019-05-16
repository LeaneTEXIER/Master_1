#include "../include/driver.h"
#include "../include/mbr.h"
#include "../include/hwconfig.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

struct mbr_s mbr;

void check_mbr_compatible(){
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  assert(sizeof(struct mbr_s)<sectorsize);
}

void load_mbr(){
  check_mbr_compatible();
  int sectorsize;
  _out(HDA_CMDREG, CMD_DSKINFO);
  sectorsize=_in(HDA_DATAREGS+4)<<8;
  sectorsize+=_in(HDA_DATAREGS+5);
  read_sector_n(0,0,(unsigned char *)(&mbr),sizeof(mbr));
  if(mbr.magic!=MAGIC){
    mbr.nb_volume = 0;
    mbr.magic = MAGIC;
  }
}

void save_mbr(){
  write_sector_n(0,0,(unsigned char *)(&mbr),sizeof(mbr));
}
