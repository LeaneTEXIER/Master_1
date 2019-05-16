#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"

int main(int argc, char **argv){
  if (argc!=2){
    printf("Usage: ./bin/init_vol <nom_du_volume> \n");
    exit(EXIT_FAILURE);
  }
  char *name = argv[1];
  if(strlen(name)>32){
    printf("Le nom du volume doit faire au maximum 32 caractères ! \n");
    exit(EXIT_FAILURE);
  }
  int volume;
  char * current_volume_str = getenv("CURRENT_VOLUME");
  if (! current_volume_str){
    printf("Il faut initialiser une valeur d'environnement pour le volume courant : CURRENT_VOLUME !");
    exit(EXIT_FAILURE);
  }
  volume = strtol(current_volume_str, NULL, 10);
  init();
  load_mbr();
  srand(time(NULL));
  int serial = rand();
  init_volume(name, serial, volume);
  printf("Volume initialisé.\n");
  exit(EXIT_SUCCESS);
}
