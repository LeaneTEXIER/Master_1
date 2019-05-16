#include <stdlib.h>
#include <stdio.h>
#include "../include/ifile.h"
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"

int main(int argc, char **argv) {
  if(argc!=4){
    printf("Usage: ./bin/vbloc_of_fbloc_ifile <inumber> <fbloc> <do_alloc> \n");
    exit(EXIT_FAILURE);
  }
  int inumber = atoi(argv[1]);
  int fbloc = atoi(argv[2]);
  int do_alloc = atoi(argv[3]);
  int volume;
  char * current_volume_str = getenv("CURRENT_VOLUME");
  if (!current_volume_str){
    printf("Il faut initialiser une valeur d'environnement pour le volume courant : CURRENT_VOLUME !");
    exit(EXIT_FAILURE);
  }
  volume = strtol(current_volume_str, NULL, 10);
  init();
  load_mbr();
  load_super(volume);
  int res = vbloc_of_fbloc(inumber, fbloc, do_alloc);
  printf("Numéro du bloc sur le volume qui correspond au %d-ième bloc de l’inoeud : %d\n", fbloc, res);
  exit(EXIT_SUCCESS);
}
