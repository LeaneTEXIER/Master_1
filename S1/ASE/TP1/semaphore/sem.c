#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "ctx.h"
#include "sem.h"

void sem_init(struct sem_s *sem, unsigned int val){
  sem -> count = val;
  sem -> lock_list = NULL;
  sem -> magic = MAGIC_SEM;
}

void sem_up(struct sem_s *sem){
  assert(sem->magic == MAGIC_SEM);
  irq_disable();
  sem->count++;
  if(sem->count<=0){
    struct ctx_s *lock_ctx = sem->lock_list;
    sem->lock_list = sem->lock_list->ctx_next;
    struct ctx_s *next_ctx = ctx_cur->ctx_next;
    ctx_cur->ctx_next = lock_ctx;
    lock_ctx -> ctx_next = next_ctx;
  }
  irq_enable();
}

void sem_down(struct sem_s *sem){
  assert(sem->magic == MAGIC_SEM);
  irq_disable();
  sem->count--;
  if(sem->count<0){
    struct ctx_s *next_ctx = ctx_cur->ctx_next;
    struct ctx_s *tmp_ctx = ctx_cur -> ctx_next;
    while ((tmp_ctx -> ctx_next) != ctx_cur){
      tmp_ctx = tmp_ctx -> ctx_next;
    }
    tmp_ctx -> ctx_next = next_ctx;
    struct ctx_s *lock_ctx = sem->lock_list;
    sem -> lock_list = ctx_cur;
    sem -> lock_list -> ctx_next = lock_ctx;
    irq_enable();
    switch_to_ctx(next_ctx);
   }
  irq_enable();
}
