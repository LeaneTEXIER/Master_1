#include <stdio.h>

void *idr_base;
void *idr_fin;


void dump_mem (int *deb, int *end){
  int *cur;
  int val;
  for(cur=deb; cur<end; cur++){
    val = *cur;
    printf("%p -> %08X\n", cur, val);
  }
}


void rec(int deepth){
  int l = 0xFACADE00;
  if (deepth>=1)   rec(deepth-1);
  else{
    asm("mov %%rsp, %0" : "=r" (idr_fin) : :);
    dump_mem(idr_fin, idr_base);
  }
}


int main(){
  asm("mov %%rsp, %0" : "=r" (idr_base) : :);
  rec(10);
  printf("Adresse fonction main : %p\n", main);
  printf("Adresse fonction de recurrence : %p\n", rec);
}
