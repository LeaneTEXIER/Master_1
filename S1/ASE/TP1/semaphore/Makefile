CC	= gcc
FLAGS = -m32 -g

ROOTDIR=/home/enseign/ASE
INCS    = -I$(ROOTDIR)/include
LIBS    = -L$(ROOTDIR)/lib -lhardware

all : philosophe prodcons

philosophe : philosophe.o ctx.o sem.o ctx.h philosophe.h sem.h hwconfig.h
	$(CC) ${FLAGS} -o philosophe philosophe.o ctx.o sem.o philosophe.h ctx.h sem.h hwconfig.h $(LIBS)

philosophe.o: philosophe.c
	$(CC) ${FLAGS} $(INCS) -c philosophe.c -o philosophe.o

prodcons : prodcons.o ctx.o sem.o prodcons.h ctx.h sem.h hwconfig.h
	$(CC) ${FLAGS} -o prodcons prodcons.o ctx.o sem.o prodcons.h ctx.h sem.h hwconfig.h $(LIBS)

prodcons.o: prodcons.c
	$(CC) ${FLAGS} $(INCS) -c prodcons.c -o prodcons.o

ctx.o: ctx.c
	$(CC) ${FLAGS} $(INCS) -c ctx.c -o ctx.o

sem.o: sem.c
	$(CC) ${FLAGS} $(INCS) -c sem.c -o sem.o

clean:
	rm -f *.o prodcons philosophe

