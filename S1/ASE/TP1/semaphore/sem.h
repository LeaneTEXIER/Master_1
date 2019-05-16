#define MAGIC_SEM 0xB0CAD0

struct sem_s{
  int count;
  struct ctx_s *lock_list;
  int magic;
};

void sem_init(struct sem_s *sem, unsigned int val);
void sem_down(struct sem_s *sem);
void sem_up(struct sem_s *sem);
