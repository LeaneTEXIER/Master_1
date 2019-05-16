typedef void (funct_t)(void *);

struct ctx_s{
  void *rsp_add;
  void *rbp_add;
  funct_t *f;
  void *args;
  char *stack;
  int magic;
  int first_time;
};

#define MAGIC 0xCAFEBABE

int init_ctx (struct ctx_s *pctx, int stack_size, funct_t f, void *args);

void switch_to_ctx(struct ctx_s *ctx);
