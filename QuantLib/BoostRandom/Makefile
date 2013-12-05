CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		test_rng.o

LIBS =		-lQuantLib -lboost_timer -lboost_random -lboost_system

TARGET =	test_rng

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
