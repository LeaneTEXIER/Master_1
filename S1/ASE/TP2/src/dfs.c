#include <stdlib.h>
#include <stdio.h>
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"

void dfs(int volume){
  load_super(volume);
  printf("Volume courant : volume numéro %d\n", volume);
  printf("Nom du volume : %s\n", super_bloc.name);
  printf("Numéro de série : %d\n", super_bloc.serial);
  printf("Premier inoeud : %d\n", super_bloc.first_inode);
  printf("Premier bloc libre : %d\n", super_bloc.first_free_bloc);
  printf("Nombre de blocs libres : %d\n", super_bloc.nb_free_bloc);
  float occ = ((float)mbr.vol[volume].nb_sector-(float)super_bloc.nb_free_bloc)/(float)mbr.vol[volume].nb_sector*100;
  printf("Taux d'occupations (en poucentage) : %.2f\n", occ);
}

int main(int argc, char **argv){
  int volume;
  char * current_volume_str = getenv("CURRENT_VOLUME");
  if (! current_volume_str){
    printf("Il faut initialiser une valeur d'environnement pour le volume courant : CURRENT_VOLUME !");
    exit(EXIT_FAILURE);
  }
  volume = strtol(current_volume_str, NULL, 10);
  init();
  load_mbr();
  dfs(volume);
  exit(EXIT_SUCCESS);
}
