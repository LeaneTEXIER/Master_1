CXXFLAGS = -Wall -std=c++14 -MMD
LDFLAGS=
CPP=g++
LD=g++

.SUFFIXES:
.SUFFIXES: .o .cpp

EXECS = main test

SRCS_main = main.cpp graph.cpp
OBJS_main = ${SRCS_main:.cpp=.o}

SRCS_test = test_Q1_add.cpp test_Q1_get.cpp test_Q1_paths.cpp test_Q1_remove_search_node.cpp test_Q1_copy.cpp graph.cpp test_Q2_add.cpp test_Q2_get.cpp test_Q2_paths.cpp test_Q2_remove_search_node.cpp test_Q2_copy.cpp
OBJS_test = ${SRCS_test:.cpp=.o}

ALL_SRCS = ${SRCS_main}${SRCS_test}

ALL_DEPS = ${ALL_SRCS:.cpp=.d}

all: $(EXECS)

-include $(ALL_DEPS)

.cpp.o:
	$(CPP) $(CXXFLAGS) -c $<

main: $(OBJS_main)
	$(LD) -o $@ $^ $(LDFLAGS)

test: $(OBJS_test)
	$(LD) -o $@ $^ $(LDFLAGS)


clean:
	rm -rf *.o
	rm -rf *.d
	rm -rf $(EXECS)

cleanedit:
	rm -rf *~
