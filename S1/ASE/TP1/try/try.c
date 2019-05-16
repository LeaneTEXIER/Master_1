#include <stdio.h>
#include <assert.h>
#include "try.h"


int try(struct ctx_s *pctx, func_t *f, int arg){
   pctx -> magic = MAGIC;
   asm("mov %%rsp,%0" : "=r"(pctx->rsp_add)::);
   asm("mov %%rbp,%0" : "=r"(pctx->rbp_add)::);
   return f(arg);
}



int throw(struct ctx_s *pctx, int r){
  static int val;
  assert(pctx->magic == MAGIC);
  val = r;
  asm("mov %0,%%rsp" : : "r"(pctx->rsp_add):);
  asm("mov %0,%%rbp" : : "r"(pctx->rbp_add):);
  return val;
}
