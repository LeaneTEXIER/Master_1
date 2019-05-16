#include <stdlib.h>
#include <assert.h>
#include "ctx.h"

static struct ctx_s *ctx_cur = NULL;

int init_ctx (struct ctx_s *pctx, int stack_size, funct_t f, void *args){
  pctx -> f = f;
  pctx -> args = args;
  pctx -> stack = malloc(stack_size);
  pctx -> rsp_add = &pctx -> stack [stack_size-sizeof(void *)];
  pctx -> rbp_add = pctx -> rsp_add;
  pctx -> magic = MAGIC;
  pctx -> first_time = 1;
  return pctx -> stack != NULL;
}


void switch_to_ctx(struct ctx_s *ctx){
  assert(ctx->magic == MAGIC);
  static void *main_rbp;
  static void *main_rsp;
  if(ctx_cur){
    asm("mov %%rbp, %0" : "=r" (ctx_cur->rbp_add) : :);
    asm("mov %%rsp, %0" : "=r" (ctx_cur->rsp_add) : :);
  }
  else{
    // Cela signifie que c'est la première fois qu'on rentre dans le switch, on mémorise alors les registres du main qui a appelé le switch
    asm("mov %%rbp,%0" : "=r"(main_rbp));
    asm("mov %%rsp,%0" : "=r"(main_rsp));
  }
  ctx_cur = ctx;
  asm("mov %0, %%rsp" : : "r"(ctx_cur->rsp_add) :);
  asm("mov %0, %%rbp" : : "r"(ctx_cur->rbp_add) :);
  if(ctx_cur-> first_time){
    ctx_cur-> first_time=0;
    ctx_cur -> f (ctx_cur -> args);
    // Fin de la fonction f
    // On retourne alors dans le main
    asm("mov %0,%%rsp": : "r"(main_rsp));
    asm("mov %0,%%rbp": : "r"(main_rbp));
  }
}

//////// Ancienne version - Switch à deux paramètres
// void switch_to_ctx(struct ctx_s *ctx_in, struct ctx_s *ctx_out){
//   assert(ctx_out->magic == MAGIC);
//   static struct ctx_s *ctx_target;
//   if(ctx_in){
//     asm("mov %%rsp, %0" : "=r" (ctx_in->rsp_add) : :);
//     asm("mov %%rbp, %0" : "=r" (ctx_in->rbp_add) : :);
//   }
//   ctx_target = ctx_out;
//   asm("mov %0, %%rsp" : : "r"(ctx_target->rsp_add) :);
//   asm("mov %0, %%rbp" : : "r"(ctx_target->rbp_add) :);
//   if(ctx_target -> first_time){
//     ctx_target -> first_time = 0;
//     ctx_target -> f (ctx_target -> args);
//     /// Fin de l'appel de la fonction f
//   }
// }
