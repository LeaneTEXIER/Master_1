struct ctx_s{
  void *rsp_add;
  void *rbp_add;
  int magic;
};

#define MAGIC 0xDEADBEEF

typedef int (func_t)(int);

int try(struct ctx_s *pctx, func_t *f, int arg);

int throw(struct ctx_s *pctx, int r);
