#include "ctx.h"
#include "sem.h"
#include "philosophe.h"
#include <stdio.h>
#include <stdlib.h>

struct philosophe_s philosophes_a_table[nb_philos];
struct sem_s sem[nb_philos];
struct sem_s mutex;
int arg[nb_philos];

void possible_manger(int i){ // fonction qui teste si le philosophe i peut manger
  if(philosophes_a_table[i].etat==VEUT_MANGER && philosophes_a_table[(i+1)%nb_philos].etat!=MANGE && philosophes_a_table[(i-1)%nb_philos].etat!=MANGE){
    philosophes_a_table[i].etat = MANGE;
    sem_up(philosophes_a_table[i].semaphore);
  }
}

void manger(int i){
  int duree_repas = rand()%100000;
  irq_disable();
  printf("Je suis le philosophe %i et je commence à manger\n", i);
  irq_enable();
  for (int m = 0; m<duree_repas; m++){}
  irq_disable();
  printf("Je suis le philosophe %i et j'ai fini de manger\n", i);
  irq_enable();
}

void penser(int i){
  int duree_pensee = rand()%1000000;
  irq_disable();
  printf("Je suis le philosophe %i et je commence à penser\n", i);
  irq_enable();
  for (int m = 0; m<duree_pensee; m++){}
  irq_disable();
  printf("Je suis le philosophe %i et j'ai fini de penser\n", i);
  irq_enable();
}

void philosophe(void *arg){
  int *argument = arg;
  int i = *argument;
  for(int k = 0; k < N_PHILO_FOIS; k++){
    penser(i);
    sem_down(&mutex); //protection car changement de valeur dans le tableau
    philosophes_a_table[i].etat = VEUT_MANGER; //car a fini de penser
    possible_manger(i);
    sem_up(&mutex);
    sem_down(philosophes_a_table[i].semaphore);
    manger(i);
    sem_down(&mutex); //protection car changement de valeur dans le tableau
    philosophes_a_table[i].etat = PENSE; // car a fini de manger
    possible_manger((i+1)%nb_philos);
    possible_manger((i-1)%nb_philos);
    sem_up(&mutex);
  }
}

int main(){
  // Initialisation du mutex
  sem_init(&mutex, 1);

  // Initialisation des philosophes (semaphores et états) et création des contextes associés
  for (int i = nb_philos-1; i >= 0; i--){
    sem_init(sem+i, 0);
    philosophes_a_table[i].etat = PENSE;
    philosophes_a_table[i].semaphore = sem+i;
    arg[i] = i;
    create_ctx(16384, philosophe, &arg[i]);
  }

  start_sched();

}
