#include <deque>

#include <metasim.hpp>
#include <particle.hpp>

using namespace MetaSim;

const std::string QueueDbgLevel("Queue DBG");

class Message{
  Tick _instant_arrivee;
  public :
      Message();
      Message(Tick t);
      void set_instant_arrivee(Tick t);
      Tick get_instant_arrivee();
};


/**
 *  The Node class represents a generic node in the system. A node can
 *  be a sink, a source, or a queue. It has a pure virtual method,
 *  <i>put()</i> that inserts a packet in the node. */
class Node : public Entity {
public:
    Node(const char *n) : Entity(n) {}

    virtual void put(Message m) = 0;

    virtual void newRun() {}
    virtual void endRun() {}
    virtual void print() {}
};


/**
 * This class models a sink. The put method does nothing else than
 * recording how many packet have been consumed since the beginning.  */
class Sink : public Node {
    int _consumed;
    Tick _time;
public:
    Sink(const char *n);
    virtual void put(Message m);
    virtual void newRun();
    virtual void endRun();

    void set_time(Tick t);
    Tick get_time();
};


/**
 * This class models a source. The put method does nothing. The source
 * produces packets that will be inserted into the destination node.
*/
class Source : public Node {
    std::unique_ptr<RandomVar> _at;
    Node* _dest;
public:
    GEvent<Source> _prodEvent;

    Source(Node* d, RandomVar* a, const char* n);

    void put(Message m);
    void produce(Event *);
    void newRun();
    void endRun();
};

/**
 * This class implements a generic queue with one server, with
 * randomly distributed service times. The service time is independent
 * form the packet. After servicing a packet, the queue node will
 * send the packet to the destination node, that can be another queue
 * or a sink. In this way we can specify simple networks of queues. */
class Queue: public Node {
    /// The destination node
    std::vector<Node*> _dest;

    /**
     *  The internal queue representation. See a description of the stl
     *  for more details on the deque (double ended queue) data
     *  structure.  This is a queue of integers: in this simple example,
     *  every packet has lenght 1. However, it is quite easy to define
     *  a sligtly different model in which the service time depends on
     *  the packet length...*/
    std::deque<Message> _q;

    /**
     * The service time random variable. It is possible to define a
     * general distribution! */
    std::unique_ptr<RandomVar> _st;

public:
    /**template <class RandomAccessIterator, class RandomAccessIterator2>
    Queue(RandomAccessIterator r1,RandomAccessIterator2 r2, RandomVar *st, const char* n){

    }
     *  This class models an event of packed served. When a packet is
     *  ready to be served, a:92n event of this type is <i>posted</i> in the
     *  future. When that time comes, the <i>doit()</i> method is
     *  invoked. */

    /// The event of served packet.
    GEvent<Queue> _servEvent;

    /**
     * Constructor for a queue.
     * @param d   pointer to the destination node
     * @param st  service time ranStack::~Stack()
{
    delete [] tab;
}dom variable
     * @param n   a simbolyc name for the queue.
     */
    template <class RandomAccessIterator1, class RandomAccessIterator2>
    Queue(RandomAccessIterator1 r1, RandomAccessIterator2 r2, RandomVar *st, const char* n):
        Node(n),
        _q(),
        _st(st->clone()),
        _dest(),
        _servEvent(this, &Queue::serve){
          for (auto ita = r1; ita != r2; ita++){
              _dest.push_back(*ita);
          }
        }

    Queue(Node* d, RandomVar* st, const char* n);

    void put(Message m);
    void serve(Event *e);
    inline int getSize() { return (int) _q.size(); }
    void newRun();
    void endRun();
};


/* ----------------------------------------------------------------------*/

/**
   This statistics measures the average lenght of the queue.
 */
class AvgQueueSizeStat : public StatMean {
    Queue &_queue;
public:
    AvgQueueSizeStat(Queue &q, const char *n) :
        StatMean(n),
        _queue(q) {}

    void probe(Event &e) {
        record(_queue.getSize() - 1);
    }
};


class AvgTimeStat : public StatMean {
    Sink &_sink;
public:
    AvgTimeStat(Sink &s, const char *n) :
        StatMean(n),
        _sink(s) {}

    void probe(Event &e) {
        record(_sink.get_time());
    }
};
