CC	= gcc

all: display_stack try_mul

display_stack: display_stack.o
	$(CC) -o display_stack display_stack.o
display_stack.o: display_stack.c
	$(CC) -c display_stack.c

try_mul: try_mul.o try.o try.h
	$(CC) -o try_mul try_mul.o try.o try.h
try_mul.o: try_mul.c try.c
	$(CC) -c try_mul.c try.c

try.o: try.c
	$(CC) -c try.c


clean:
	rm -f *.o display_stack try_mul
