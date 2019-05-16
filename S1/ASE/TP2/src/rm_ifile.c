#include <stdlib.h>
#include <stdio.h>
#include "../include/ifile.h"
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"

int main(int argc, char **argv) {
  if(argc!=2){
    printf("Usage: ./bin/rm_ifile <numero_ifile_a_supprimer>\n");
    exit(EXIT_FAILURE);
  }
  int inumber = atoi(argv[1]);
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
  delete_ifile(inumber);
  printf("Ifile %d supprimé avec succès \n", inumber);
  exit(EXIT_SUCCESS);
}
