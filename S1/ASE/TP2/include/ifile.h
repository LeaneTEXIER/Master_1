#define SECTOR_SIZE 512
#define INODE_MAGIC 0xAFEECAFE
#define NB_DIRECT (SECTOR_SIZE/sizeof(int)-5)
#define NB_INDIRECT (SECTOR_SIZE/sizeof(int))

enum file_type_e{FILE_T, DIRECTORY_T};

struct inode_s{
  int magic;
  int file_size;
  enum file_type_e file_type;
  int direct[NB_DIRECT];
  int indirect;
  int indirect2;
};

void read_inode(unsigned int inumber, struct inode_s *inode);
void write_inode(unsigned int inumber, struct inode_s *inode);
unsigned int create_inode(enum file_type_e type);
void delete_indirect(int indirect_nb);
int delete_inode(unsigned int inumber);
int vb_of_fb_indirect(unsigned int inumber, unsigned int fbloc, unsigned int do_alloc);
unsigned int vbloc_of_fbloc(unsigned int inumber, unsigned int fbloc, unsigned int do_alloc);

struct file_desc_t{
  int inumber;
  int offset;
  int file_size;
  char buf[SECTOR_SIZE];
  int dirty;
};

unsigned int create_ifile(enum file_type_e type);
int delete_ifile(unsigned int inumber);
int open_ifile(struct file_desc_t *fd, unsigned int inumber);
void close_ifile(struct file_desc_t *fd);
void flush_ifile(struct file_desc_t *fd);
void seek_ifile(struct file_desc_t *fd, int r_offset);
void seek2_ifile(struct file_desc_t *fd, int a_offset);
int read_ifile(struct file_desc_t *fd, void *buf, unsigned int nbyte);
int write_ifile(struct file_desc_t *fd, const void *buf, unsigned int nbyte);
