#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../include/hwconfig.h"
#include "../include/hardware.h"
#include "../include/mi_user.h"
#include "../include/mi_syscall.h"

static int sum(void *ptr){
    int i;
    int sum = 0;

    for(i = 0; i < PAGE_SIZE * N/2 ; i++)
        sum += ((char*)ptr)[i];
    return sum;
}


void init() {
	void *ptr;
	int res;

	ptr = virtual_memory;

	_int(SYSCALL_SWTCH_0);
	memset(ptr, 1, PAGE_SIZE * N/2);
	_int(SYSCALL_SWTCH_1);
	memset(ptr, 3, PAGE_SIZE * N/2);

	_int(SYSCALL_SWTCH_0);
	res = sum(ptr);
	printf("Résultat du processus 0 : %d\n", res);
	_int(SYSCALL_SWTCH_1);
	res = sum(ptr);
	printf("Résultat du processus 1 : %d\n", res);
}
