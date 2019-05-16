#include <stdio.h>
#include <stdlib.h>
#include "../include/hwconfig.h"
#include "../include/hardware.h"
#include "../include/mi_kernel.h"
#include "../include/mi_syscall.h"
#include "../include/mi_user.h"


static int current_process;


static int ppage_of_vpage(int process, int vpage){
  if(vpage>=128){
    return -1;
  }
  return 2+2*vpage+process;
}


void mmuhandler() {
	struct tlb_entry_s e;

	int fault_addr = _in(MMU_FAULT_ADDR);

  if(fault_addr<(int)virtual_memory && fault_addr>(int)virtual_memory + 127*PAGE_SIZE){
    printf("My Segmentation fault\n");
    exit(EXIT_FAILURE);
  }
	e.vpage = (fault_addr >> 12) & 0xFFF;
	e.ppage = ppage_of_vpage(current_process, e.vpage);
  _out(TLB_ADD_ENTRY, *(int *)&e);
}


static void switch_to_process0(void){
    current_process = 0;
    _out(MMU_CMD, MMU_RESET);
}


static void switch_to_process1(void){
    current_process = 1;
    _out(MMU_CMD, MMU_RESET);
}


int main(int argc, char **argv){

    if(init_hardware(INIFILENAME) == 0) {
       fprintf(stderr, "Error in hardware initialization.\n");
       exit(EXIT_FAILURE);
    }
    IRQVECTOR[MMU_IRQ] = mmuhandler;

    IRQVECTOR[SYSCALL_SWTCH_0] = switch_to_process0;
    IRQVECTOR[SYSCALL_SWTCH_1] = switch_to_process1;
    _mask(0x1001);

    init();

    exit(EXIT_SUCCESS);
}
