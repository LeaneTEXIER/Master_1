#define nb_philos 5 /* nombre de philosophes à l table */
#define N_PHILO_FOIS 10 /* nombre de fois que les philosophes vont manger */

enum etat_philo {PENSE, MANGE, VEUT_MANGER};

struct philosophe_s{
  struct sem_s *semaphore;
  enum etat_philo etat;
};

void possible_manger(int i);

void manger(int i);

void penser(int i);

void philosophe(void *arg);
