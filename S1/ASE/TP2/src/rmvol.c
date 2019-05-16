#include <stdio.h>
#include <stdlib.h>
#include "../include/mbr.h"
#include "../include/driver.h"

void rmvol(int num_volume){
  if (num_volume < 0 || num_volume >= mbr.nb_volume){
    printf("Le numéro de volume donné ne correspond à aucun volume.\n");
    exit(EXIT_FAILURE);
  }
  for(int i=num_volume; i<mbr.nb_volume-1; i++){
    mbr.vol[i] = mbr.vol[i+1];
  }
  mbr.nb_volume--;
  save_mbr();
}

int main(int argc, char **argv){
  if(argc != 2) {
    printf("Usage: ./bin/rmvol <numero_volume_a_supprimer> \n");
    exit(EXIT_FAILURE);
  }
  int num_volume = atoi(argv[1]);

  init();
  load_mbr();
  rmvol(num_volume);
  printf("Volume %d supprimé avec succès \n", num_volume);
  exit(EXIT_SUCCESS);

}
