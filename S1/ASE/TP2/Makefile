CC	= gcc
FLAGS = -Wall -pedantic -m32 -g

ROOTDIR=/home/enseign/ASE
INCS    = -I$(ROOTDIR)/include
LIBS    = -L$(ROOTDIR)/lib -lhardware

all: frmt dumps mkvol dvol rmvol init_vol dfs mk_ifile rm_ifile vbloc_of_fbloc_ifile

vbloc_of_fbloc_ifile : vbloc_of_fbloc_ifile.o ifile.o driver.o vol.o mbr.o tools.o
	$(CC) ${FLAGS} -o bin/vbloc_of_fbloc_ifile bin/vbloc_of_fbloc_ifile.o bin/ifile.o bin/driver.o bin/vol.o bin/mbr.o bin/tools.o ${LIBS}
vbloc_of_fbloc_ifile.o : src/vbloc_of_fbloc_ifile.c
	$(CC) ${FLAGS} $(INCS) -c src/vbloc_of_fbloc_ifile.c -o bin/vbloc_of_fbloc_ifile.o

mk_ifile : mk_ifile.o ifile.o driver.o vol.o mbr.o tools.o
	$(CC) ${FLAGS} -o bin/mk_ifile bin/mk_ifile.o bin/ifile.o bin/driver.o bin/vol.o bin/mbr.o bin/tools.o ${LIBS}
mk_ifile.o : src/mk_ifile.c
	$(CC) ${FLAGS} $(INCS) -c src/mk_ifile.c -o bin/mk_ifile.o

rm_ifile : rm_ifile.o ifile.o driver.o vol.o mbr.o tools.o
	$(CC) ${FLAGS} -o bin/rm_ifile bin/rm_ifile.o bin/ifile.o bin/driver.o bin/vol.o bin/mbr.o bin/tools.o ${LIBS}
rm_ifile.o : src/rm_ifile.c
	$(CC) ${FLAGS} $(INCS) -c src/rm_ifile.c -o bin/rm_ifile.o

ifile.o : src/ifile.c
	$(CC) ${FLAGS} $(INCS) -c src/ifile.c -o bin/ifile.o

tools.o : src/tools.c
	$(CC) ${FLAGS} $(INCS) -c src/tools.c -o bin/tools.o

dfs : dfs.o vol.o driver.o mbr.o
	$(CC) ${FLAGS} -o bin/dfs bin/dfs.o bin/vol.o bin/driver.o bin/mbr.o ${LIBS}
dfs.o : src/dfs.c
	$(CC) ${FLAGS} $(INCS) -c src/dfs.c -o bin/dfs.o

init_vol : init_vol.o vol.o mbr.o driver.o
	$(CC) ${FLAGS} -o bin/init_vol bin/init_vol.o bin/vol.o bin/mbr.o bin/driver.o ${LIBS}
init_vol.o : src/init_vol.c
	$(CC) ${FLAGS} $(INCS) -c src/init_vol.c -o bin/init_vol.o

vol.o : src/vol.c
	$(CC) ${FLAGS} $(INCS) -c src/vol.c -o bin/vol.o

rmvol : rmvol.o driver.o mbr.o
	$(CC) ${FLAGS} -o bin/rmvol bin/rmvol.o bin/driver.o bin/mbr.o ${LIBS}
rmvol.o: src/rmvol.c
	$(CC) ${FLAGS} $(INCS) -c src/rmvol.c -o bin/rmvol.o

dvol : dvol.o driver.o mbr.o
	$(CC) ${FLAGS} -o bin/dvol bin/dvol.o bin/driver.o bin/mbr.o ${LIBS}
dvol.o: src/dvol.c
	$(CC) ${FLAGS} $(INCS) -c src/dvol.c -o bin/dvol.o

mkvol : mkvol.o driver.o mbr.o
	$(CC) ${FLAGS} -o bin/mkvol bin/mkvol.o bin/driver.o bin/mbr.o ${LIBS}
mkvol.o: src/mkvol.c
	$(CC) ${FLAGS} $(INCS) -c src/mkvol.c -o bin/mkvol.o

mbr.o : src/mbr.c
	$(CC) ${FLAGS} $(INCS) -c src/mbr.c -o bin/mbr.o

frmt : frmt.o driver.o
	$(CC) ${FLAGS} -o bin/frmt bin/frmt.o bin/driver.o ${LIBS}
frmt.o: src/frmt.c
	$(CC) ${FLAGS} $(INCS) -c src/frmt.c -o bin/frmt.o

dumps : dumps.o driver.o
	$(CC) ${FLAGS} -o bin/dumps bin/dumps.o bin/driver.o ${LIBS}
dumps.o: src/dumps.c
	$(CC) ${FLAGS} $(INCS) -c src/dumps.c -o bin/dumps.o

driver.o: src/driver.c
	$(CC) ${FLAGS} $(INCS) -c src/driver.c -o bin/driver.o



test : test_vol


test_vol : test_vol.o vol.o mbr.o driver.o
	$(CC) ${FLAGS} -o bin/test_vol bin/test_vol.o bin/vol.o bin/mbr.o bin/driver.o ${LIBS}
test_vol.o : src/test_vol.c
	$(CC) ${FLAGS} $(INCS) -c src/test_vol.c -o bin/test_vol.o



clean:
	rm -f bin/*.o

realclean:
	rm -f bin/* etc/vdiskA.bin
