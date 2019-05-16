#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "ctx.h"
#include "hardware.h"
#include "hwconfig.h"

struct ctx_s* ctx_cur= NULL;

int create_ctx(int stack_size, funct_t f, void *args){
  struct ctx_s *ctx = malloc(sizeof(struct ctx_s));
  assert(ctx); //Permet de verifier que nous avons bien un espace pour la structure
  ctx -> f = f;
  ctx -> args = args;
  ctx -> stack = malloc(stack_size);
  ctx -> rsp_add = &ctx -> stack [stack_size-sizeof(void *)];
  ctx -> rbp_add = ctx -> rsp_add;
  ctx -> first_time = 1;
  irq_disable();
  if(!ctx_cur){
    ctx_cur = ctx;
    ctx -> ctx_next = ctx;
  }else{
    ctx -> ctx_next = ctx_cur -> ctx_next;
    ctx_cur -> ctx_next = ctx;
  }
  ctx -> magic = MAGIC;
  irq_enable();
  return ctx -> stack != NULL;
}

void start_ctx(){
  ctx_cur->first_time=0;
  irq_enable();
  ctx_cur->f(ctx_cur->args);
}

void finish_ctx(){
  irq_disable();
  free(ctx_cur->stack);
  free(ctx_cur);
  if(ctx_cur->ctx_next != ctx_cur){
    struct ctx_s *next_ctx = ctx_cur->ctx_next;
    struct ctx_s *tmp_ctx = ctx_cur -> ctx_next;
    while ((tmp_ctx -> ctx_next) != ctx_cur){
      tmp_ctx = tmp_ctx -> ctx_next;
    }
    tmp_ctx -> ctx_next = next_ctx;
    ctx_cur = next_ctx;
    asm("mov %0, %%esp" : : "r"(ctx_cur->rsp_add) :);
    asm("mov %0, %%ebp" : : "r"(ctx_cur->rbp_add) :);
    irq_enable();
  }
  else {
    printf("Fin - Toutes les contextes sont finis \n");
    exit(EXIT_SUCCESS);
  }
}

void switch_to_ctx(struct ctx_s *ctx){
  assert(ctx->magic==MAGIC);
  irq_disable();
  if (ctx_cur){
    asm("movl %%ebp,%0":"=r"(ctx_cur->rbp_add));
    asm("movl %%esp,%0":"=r"(ctx_cur->rsp_add));
  }
  ctx_cur=ctx;
  asm("movl %0, %%esp": : "r"(ctx_cur->rsp_add));
  asm("movl %0, %%ebp": : "r"(ctx_cur->rbp_add));
  if(ctx_cur->first_time){
    start_ctx();
    finish_ctx();
  }
  irq_enable();
}

void yield(){
  switch_to_ctx(ctx_cur->ctx_next);
}

static void
empty_it(void)
{
    return;
}

static void
timer_it() {
    _out(TIMER_ALARM,0xFFFFFFFE);
    yield();
}

void start_sched(){
  irq_disable();
  if(!init_hardware(INIFILENAME)) exit(1);
  for (unsigned int i=0; i<16; i++){
    IRQVECTOR[i] = empty_it;
  }
  _out(TIMER_PARAM, RESET+ALARM);
  _out(TIMER_ALARM,0xFFFFFFFE);
  IRQVECTOR[TIMER_IRQ] = timer_it;
  irq_enable();
  yield();
}

void irq_disable() {
  _mask(15);
}

void irq_enable(){
  _mask(0);
}
