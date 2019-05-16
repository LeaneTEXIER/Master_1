CC	= gcc
FLAGS = -pedantic -Wall

coroutine: coroutine.o ctx.o ctx.h
	$(CC) ${FLAGS} -o coroutine coroutine.o ctx.o ctx.h
coroutine.o: coroutine.c
	$(CC) ${FLAGS} -c coroutine.c

ctx.o: ctx.c
	$(CC) ${FLAGS} -c ctx.c


clean:
	rm -f *.o coroutine
