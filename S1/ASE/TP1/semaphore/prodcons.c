#include "ctx.h"
#include "sem.h"
#include "prodcons.h"
#include <stdio.h>
#include <stdlib.h>

struct sem_s mutex, vide, plein;

void produire_objet(objet_t *obj) {
  for(int j = 0; j<*obj*2; j++){}
  *obj = ++ *obj;
  irq_disable();
  printf("Produit objet %i\n", *obj);
  irq_enable();
}

void mettre_objet(objet_t obj){
  for(int j = 0; j<obj; j++){}
  irq_disable();
  printf("Met objet %i\n", obj);
  irq_enable();
}

void producteur (void *args)
{
  objet_t objet = 0;

  for(int i = 0; i < C ; i++){
    produire_objet(&objet);           /* produire l'objet suivant */
    sem_down(&vide);                  /* dec. nb places libres */
    sem_down(&mutex);                 /* entree en section critique */
    mettre_objet(objet);              /* mettre l'objet dans le tampon */
    sem_up(&mutex);                   /* sortie de section critique */
    sem_up(&plein);                   /* inc. nb place occupees */
  }
}

void retirer_objet(objet_t *obj) {
  for(int j = 0; j<10; j++){}
  *obj = ++ *obj;
  irq_disable();
  printf("Retire objet %i\n", *obj);
  irq_enable();
}

void utiliser_objet(objet_t obj){
  for(int j = 0; j<1000; j++){}
  irq_disable();
  printf("Utilise objet %i\n", obj);
  irq_enable();
}

void consommateur (void *args)
{
  objet_t objet = 0;
  for(int i = 0; i < C ; i++){
    sem_down(&plein);                 /* dec. nb emplacements occupes */
    sem_down(&mutex);                 /* entree section critique */
    retirer_objet (&objet);           /* retire un objet du tampon */
    sem_up(&mutex);                   /* sortie de la section critique */
    sem_up(&vide);                    /* inc. nb emplacements libres */
    utiliser_objet(objet);            /* utiliser l'objet */
  }
}

int main(){
  sem_init(&mutex, 1);                /* controle d'acces au tampon */
  sem_init(&vide, N);                 /* nb de places libres */
  sem_init(&plein, 0);                /* nb de places occupees */

  create_ctx(16384, producteur, NULL);
  create_ctx(16384, consommateur, NULL);

  start_sched();

  exit(EXIT_SUCCESS);
}
