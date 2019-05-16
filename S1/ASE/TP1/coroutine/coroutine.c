#include <stdio.h>
#include <stdlib.h>
#include "ctx.h"

struct ctx_s ctx_ping;
struct ctx_s ctx_pong;

void ping (void * arg){
  for (int i = 0; i<10; i++){
    printf("A");
    switch_to_ctx(&ctx_pong);
    printf("B");
    switch_to_ctx(&ctx_pong);
    printf("C");
    switch_to_ctx(&ctx_pong);
  }
}

void pong (void * arg){
  for (int i = 0; i<10; i++){
    printf("1");
    switch_to_ctx(&ctx_ping);
    printf("2");
    switch_to_ctx(&ctx_ping);
  }
}

int main (){
  init_ctx(&ctx_ping, 16384, ping, NULL);
  init_ctx(&ctx_pong, 16384, pong, NULL);
  switch_to_ctx(&ctx_ping);
  printf("\n");
  printf("Fin\n");
  exit(EXIT_SUCCESS);
}
