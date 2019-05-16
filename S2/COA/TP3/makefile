CXXFLAGS = -std=c++14 -MMD
LIBS = -lpthread -lX11


CPP=g++
LD=g++

.SUFFIXES:
.SUFFIXES: .o .cpp

EXECS = example_cimg main

SRCS_example_cimg = example_cimg.cpp
OBJS_example_cimg = ${SRCS_example_cimg:.cpp=.o}
DEP_example_cimg = ${OBJS_example_cimg:.o=.d}

SRCS_main = main.cpp shape.cpp rect.cpp circle.cpp triangle.cpp shapeGroup.cpp
OBJS_main = ${SRCS_main:.cpp=.o}
DEP_main = ${OBJS_main:.o=.d}


all: $(EXECS)

.cpp.o:
	$(CPP) $(CXXFLAGS) -c $<

-include ${DEP_example_cimg}

example_cimg: $(OBJS_example_cimg) $(OBJS_example_cimg)
	$(LD) $(LDFLAGS) -o $@ $^ $(LIBS)

main: $(OBJS_main) $(OBJS_main)
	$(LD) $(LDFLAGS) -o $@ $^ $(LIBS)


clean:
	rm -rf *.o
	rm -rf $(EXECS)

cleanedit:
	rm -rf *~
