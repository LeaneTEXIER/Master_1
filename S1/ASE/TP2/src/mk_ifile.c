#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "../include/ifile.h"
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"

int main(int argc, char **argv) {
  if(argc!=2){
    printf("Usage: ./bin/mk_ifile <type_ifile> avec type_ifile = DIR pour repertoire ou FILE pour fichier\n");
    exit(EXIT_FAILURE);
  }
  char *type = argv[1];
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
  if(strcmp(type, "DIR")==0){
    int res = create_ifile(DIRECTORY_T);
    printf("Numéro de l'ifile créé : %d\n", res);
  }
  else if(strcmp(type, "FILE")==0){
    int res = create_ifile(FILE_T);
    printf("Numéro de l'ifile créé : %d\n", res);
  }
  else{
    printf("Usage: ./bin/mk_ifile <type_ifile> avec type_ifile = DIR pour repertoire ou FILE pour fichier\n");
    exit(EXIT_FAILURE);
  }
  exit(EXIT_SUCCESS);
}
