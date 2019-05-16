extern struct ctx_s* ctx_cur;

#define MAGIC 0xCAFEBABE
#define RESET 128
#define ALARM 64

typedef void (funct_t)(void*);

struct ctx_s{
  void *rsp_add;
  void *rbp_add;
  funct_t *f;
  void *args;
  char *stack;
  int magic;
  int first_time;
  struct ctx_s *ctx_next;
};

int create_ctx(int stack_size, funct_t f, void *args);

void start_ctx();

void finish_ctx();

void switch_to_ctx(struct ctx_s *ctx);

void yield();

void start_sched();

void irq_disable();

void irq_enable();
