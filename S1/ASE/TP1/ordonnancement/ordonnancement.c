#include <stdio.h>
#include <stdlib.h>
#include "ctx.h"
#include "hardware.h"
#include "hwconfig.h"

#define COUNT 1000000

void pang(void *args)
{
  for (int j=0; j<10; j++){
    for(int i=0; i<COUNT; i++){};
    printf("!\n");
  }
  printf("Fin du pang\n");
}

void ping(void *args)
{
  for (int j=0; j<10; j++){
    for(int i=0; i<2*COUNT; i++){};
    printf("A\n");
    for(int i=0; i<COUNT; i++){};
    printf("B\n");
    for(int i=0; i<COUNT; i++){};
    printf("C\n");
  }
  printf("Fin du ping\n");
}

void pong(void *args)
{
  for (int j=0; j<2; j++){
    for(int i=0; i<COUNT; i++){};
    printf("1\n");
    for(int i=0; i<COUNT; i++){};
    printf("2\n");
  }
  create_ctx(16384, pang, NULL);
  for (int j=0; j<3; j++){
    for(int i=0; i<COUNT; i++){};
    printf("1\n");
    for(int i=0; i<COUNT; i++){};
    printf("2\n");
  }
  printf("Fin du pong\n");
}

int main(){
  create_ctx(16384, ping, NULL);
  create_ctx(16384, pong, NULL);

  start_sched();

  exit(EXIT_SUCCESS);
}
