#define NBVOL 8
#define MAGIC 0x55AA

enum vol_type_e{BASE,ANNEXE,OTHER};

struct vol_desc_s{
  int first_sector;
  int first_cylinder;
  int nb_sector;
  enum vol_type_e type;
};

struct mbr_s{
  int magic;
  int nb_volume;
  struct vol_desc_s vol[NBVOL];
};

extern struct mbr_s mbr;

void check_mbr_compatible();

void load_mbr();

void save_mbr();
