CXXFLAGS = -Wall -std=c++14 -O0 -g
LDFLAGS=
CPP=g++
LD=g++

.SUFFIXES:
.SUFFIXES: .o .cpp

EXECS = stack_main stack_test hanoi

SRCS_stack = Stack.cpp
OBJS_stack = ${SRCS_stack:.cpp=.o}

SRCS_main = main.cpp
OBJS_main = ${SRCS_main:.cpp=.o}

SRCS_hanoi = hanoi.cpp
OBJS_hanoi = ${SRCS_hanoi:.cpp=.o}

SRCS_test = testAffectation.cpp testComparaison.cpp testOperateurAddition.cpp testOperateurGlobalAddition.cpp testOperateurAffichage.cpp
OBJS_test = ${SRCS_test:.cpp=.o}

all: $(EXECS)

.cpp.o:
	$(CPP) $(CXXFLAGS) -c $<

stack_main: $(OBJS_stack) $(OBJS_main)
	$(LD) $(LDFLAGS) -o $@ $^

hanoi: $(OBJS_stack) $(OBJS_hanoi)
	$(LD) $(LDFLAGS) -o $@ $^

stack_test: $(OBJS_stack) $(OBJS_test)
	$(LD) $(LDFLAGS) -o $@ $^

clean:
	rm -rf *.o
	rm -rf $(EXECS)

cleanedit:
	rm -rf *~
