#include <iostream>
#include "queue.hpp"

using namespace std;


Message::Message()
{
    _instant_arrivee = 0;
}

Message::Message(Tick t)
{
    _instant_arrivee = t;
}

void Message::set_instant_arrivee(Tick t)
{
    _instant_arrivee = t;
}

Tick Message::get_instant_arrivee()
{
    return _instant_arrivee;
}



Sink::Sink(const char *n) : Node(n), _consumed(0)
{
    Tick _time;

}

void Sink::set_time(Tick t){
    _time = t;
}

Tick Sink::get_time(){
    return _time;
}

void Sink::put(Message m)
{
    _consumed ++;
    set_time(SIMUL.getTime()-m.get_instant_arrivee());
}

void Sink::newRun()
{
    _consumed = 0;
}

void Sink::endRun()
{
}


Source::Source(Node* d, RandomVar* a, const char* n) :
    Node(n),
    _at(a->clone()),
    _dest(d),
    _prodEvent(this, &Source::produce)
{}

void Source::put(Message m) {}

void Source::produce(Event *e)
{
    DBGENTER(QueueDbgLevel);

    Tick next = Tick::round(_at->get());
    Message m = Message();
    m.set_instant_arrivee(SIMUL.getTime());
    _dest->put(m);
    _prodEvent.post(SIMUL.getTime() + next);

    DBGPRINT_2("Next arrival at ", (SIMUL.getTime() + next));
}

void Source::newRun()
{
    _prodEvent.post(Tick(_at->get()));
}

void Source::endRun()
{}


Queue::Queue(Node* d, RandomVar* st, const char* n) :
    Node(n),
    _dest(),
    _q(),
    _st(st->clone()),
    _servEvent(this, &Queue::serve){
        _dest.push_back(d);
    }

void Queue::put(Message m)
{
    DBGENTER(QueueDbgLevel);

    //_q.push_back(1);

    _q.push_back(m);


    DBGPRINT_2("Queue size is now ", _q.size());

    if (_q.size() == 1) {
        Tick completion = SIMUL.getTime() + Tick(_st->get());
        _servEvent.post(completion);
        DBGPRINT_2("Completion at ", completion);
    }
}

void Queue::serve(Event *e)
{
    DBGENTER(QueueDbgLevel);

    Message m = _q.front();
    _q.pop_front();

    DBGPRINT_2("Queue size: ", _q.size());

    if (_q.size() != 0) {
        Tick next = SIMUL.getTime() + Tick(_st->get());
        _servEvent.post(next);
        DBGPRINT_2("Now serving next packet, completion at ", next);
    }
    for (Node *i : _dest) i->put(m);
}

void Queue::newRun()
{
    _q.clear();
}



void Queue::endRun()
{}
