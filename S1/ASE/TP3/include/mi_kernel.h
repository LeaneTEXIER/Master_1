struct tlb_entry_s {
  int rfu:8;
  int vpage:12;
  int ppage:8;
  int A_X:1;
  int A_W:1;
  int A_R:1;
  int used:1;
};

void mmuhandler();
