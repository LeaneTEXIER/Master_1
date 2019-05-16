void empty_it();

void init();

void my_seek(unsigned int cylinder, unsigned int sector);

void read_sector_n(unsigned int cylinder, unsigned int sector, unsigned char * buffer, int n);

void read_sector(unsigned int cylinder, unsigned int sector, unsigned char * buffer);

void write_sector_n(unsigned int cylinder, unsigned int sector, const unsigned char * buffer, int n);

void write_sector(unsigned int cylinder, unsigned int sector, const unsigned char * buffer);

void format_sector(unsigned int cylinder, unsigned int sector, unsigned int nsector, unsigned int value);
