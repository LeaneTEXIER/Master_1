#include <stdio.h>
#include <stdlib.h>
#include "../include/mbr.h"
#include "../include/driver.h"

void dvol(){
  int nb_volume = mbr.nb_volume;
  printf("Il y a %d partition(s).\n", nb_volume);
  for (int i=0;i<nb_volume;i++){
    struct vol_desc_s v = mbr.vol[i];
    printf("Le volume %d commence en cylindre %d et secteur %d. Sa taille est de %d bloc(s). Il est de type %d.\n", i, v.first_cylinder, v.first_sector, v.nb_sector, v.type);
  }
}

int main(int argc, char **argv){
  init();
  load_mbr();
  dvol();
  exit(EXIT_SUCCESS);
}
