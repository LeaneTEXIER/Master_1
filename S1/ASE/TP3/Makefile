CC	= gcc
FLAGS = -Wall -pedantic -m32 -g

ROOTDIR=/home/enseign/ASE
INCS    = -I$(ROOTDIR)/include
LIBS    = -L$(ROOTDIR)/lib -lhardware

all : mmuhandler modes mi_kernel

mi_kernel : mi_user.o mi_kernel.o
	${CC} ${FLAGS} -o bin/mi_kernel bin/mi_user.o bin/mi_kernel.o ${LIBS}

mi_kernel.o : src/mi_kernel.c
	$(CC) ${FLAGS} $(INCS) -c src/mi_kernel.c -o bin/mi_kernel.o

mi_user.o : src/mi_user.c
	$(CC) ${FLAGS} $(INCS) -c src/mi_user.c -o bin/mi_user.o

modes : modes.o
	$(CC) ${FLAGS} -o bin/modes bin/modes.o ${LIBS}

modes.o : src/modes.c
	$(CC) ${FLAGS} $(INCS) -c src/modes.c -o bin/modes.o

mmuhandler : mmuhandler.o
	$(CC) ${FLAGS} -o bin/mmuhandler bin/mmuhandler.o ${LIBS}

mmuhandler.o : src/mmuhandler.c
	$(CC) ${FLAGS} $(INCS) -c src/mmuhandler.c -o bin/mmuhandler.o

clean:
	rm -f bin/*.o

realclean:
	rm -f bin/*
