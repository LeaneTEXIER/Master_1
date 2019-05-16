#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/driver.h"
#include "../include/hwconfig.h"
#include "../include/config_hardware.h"
#include "../include/hardware.h"

//Nous supposons que le volume a été préalablement créé
int main(int argc, char **argv){
  int volume, serial, bloc;
  srand(time(NULL));
  init();
  load_mbr();
  char * current_volume_str = getenv("CURRENT_VOLUME");
  if (! current_volume_str){
    printf("Il faut initialiser une valeur d'environnement pour le volume courant : CURRENT_VOLUME !");
    exit(EXIT_FAILURE);
  }
  volume = strtol(current_volume_str, NULL, 10);
  //Init volume
  serial = rand();
  init_volume("Je teste", serial, volume);
  //Charge super vloc du volume
  load_super(volume);
  // Faire appel à la fonction new_bloc() jusqu’à ce qu’elle retourne une erreur
  printf("Appel à la fonction new_bloc jusqu'a ce qu'elle retourne une erreur\n");
  while((bloc = new_bloc())!=BLOC_NULL){
    printf("Nouveau bloc alloué : bloc numéro %d\n", bloc);
  }
  // Vérifier que le disque est plein
  if (super_bloc.nb_free_bloc==0 && super_bloc.first_free_bloc==BLOC_NULL){
    printf("Disque plein : Tout les blocs ont bien été alloués !\n");
  }
  else{
    printf("Erreur ! Le disque n'est pas plein !\n");
    exit(EXIT_FAILURE);
  }
  // Itèrer un nombre aléatoire de fois sur la libération d’un bloc free_bloc()
  int rand_i = rand()%(mbr.vol[volume].nb_sector-1);
  printf("On va libérer %d blocs\n", rand_i);
  for(int i=1; i<=rand_i; i++){
    free_bloc(i);
    printf("Bloc numéro %d libéré \n", i);
  }
  // Afficher le statut du disque (taille libre)
  printf("Nombre de blocs libres : %d\n", super_bloc.nb_free_bloc);
  // Allouer des blocs tant que le disque est non plein et retourne le nombre de blocs ayant pu être alloués
  printf("Allocation des blocs tant que le disque est non plein\n");
  int i = 0;
  while(super_bloc.nb_free_bloc>0 && super_bloc.first_free_bloc!=BLOC_NULL){
    i++;
    bloc = new_bloc();
    printf("Nouveau bloc alloué : bloc numéro %d\n", bloc);
  }
  printf("Nombre de blocs ayant pu être alloué %d\n", i);
  exit(EXIT_SUCCESS);
}
