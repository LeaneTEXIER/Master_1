#include <stdlib.h>
#include <stdio.h>
#include "../include/hwconfig.h"
#include "../include/hardware.h"


static void mmuhandler(){
  printf("tentative d'accès illégal à l'adresse %d\n", _in(MMU_FAULT_ADDR));
  exit(EXIT_FAILURE);
}

int main(int argc, char **argv){
  char *ptr;

  if(init_hardware(INIFILENAME) == 0) {
	   fprintf(stderr, "Error in hardware initialization.\n");
     exit(EXIT_FAILURE);
  }

  IRQVECTOR[MMU_IRQ] = mmuhandler;
  _mask(1);

  ptr = (char *)0;
  *ptr = 'c';

  exit(EXIT_SUCCESS);
}
