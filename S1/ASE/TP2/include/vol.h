#define SB_MAGIC 0xFACADE
#define FB_MAGIC 0x314265
#define BLOC_NULL 0

extern struct superbloc_s super_bloc;
extern int current_volume;

struct superbloc_s{
  int magic;
  char name[32];
  int serial;
  int first_inode;
  int first_free_bloc;
  int nb_free_bloc;
};

struct free_bloc_s{
  int magic;
  unsigned int next;
};

void read_bloc(unsigned int vol, unsigned int nbloc, unsigned char *buffer);
void read_bloc_n(unsigned int vol, unsigned int nbloc,unsigned char *buffer, unsigned int n);
void write_bloc(unsigned int vol, unsigned int nbloc, const unsigned char *buffer);
void write_bloc_n(unsigned int vol, unsigned int nbloc, const unsigned char *buffer, unsigned int n);
void format_vol(unsigned int vol);
void init_volume(char * name, int serial, int volume);
int load_super(unsigned int vol);
void save_super();
unsigned int new_bloc();
void free_bloc(unsigned int bloc);
