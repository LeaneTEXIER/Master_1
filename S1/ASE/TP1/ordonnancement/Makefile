CC	= gcc
FLAGS = -m32 -Wall -pedantic -g

ROOTDIR=/home/enseign/ASE
INCS    = -I$(ROOTDIR)/include
LIBS    = -L$(ROOTDIR)/lib -lhardware


ordonnancement : ordonnancement.o ctx.o ctx.h hwconfig.h
	$(CC) ${FLAGS} -o ordonnancement ordonnancement.o ctx.o $(LIBS)
ordonnancement.o: ordonnancement.c
	$(CC) ${FLAGS} $(INCS) -c ordonnancement.c -o ordonnancement.o

ctx.o: ctx.c
	$(CC) ${FLAGS} $(INCS) -c ctx.c -o ctx.o


clean:
	rm -f *.o ordonnancement
