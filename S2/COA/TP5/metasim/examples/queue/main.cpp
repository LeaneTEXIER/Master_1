#include <queue.hpp>

using namespace std;

int main()
{
    int interarrival, servicetime;
    double lambda, mu;

    cout << "Enter average interval between arrivals: ";
    cin >> interarrival;
    cout << "Enter average service time: ";
    cin >> servicetime;

    if (interarrival < servicetime) {
        cout << "Unstable !!" << endl;
        exit(0);
    }

    int nruns;
    cout << "Number of runs: ";
    cin >> nruns;

    if (nruns <3 or nruns > 30) {
        cout << "Number of runs between [3, 30]" << endl;
        exit(0);
    }

    lambda = 1.0 / interarrival;
    mu = 1.0 / servicetime;

    double rho = lambda / mu;

    RandomVar::init(12345);

    ExponentialVar st(0.005);
    ExponentialVar mu1(0.006);
    ExponentialVar mu2(0.0075);
    ExponentialVar mu3(0.0075);
    ExponentialVar mu4(0.006);

    Sink sink("sink");
    Sink sink2("sink2");

    Queue que2(&sink, &mu2, "M/M/1 queue2");
    Queue que4(&sink2, &mu4, "M/M/1 queue4");
    Queue que3(&que4, &mu3, "M/M/1 queue3");
    std::vector<Node*> vec;
    vec.push_back(&que2);
    vec.push_back(&que3);
    Queue que1(begin(vec), end(vec), &mu1, "M/M/1 queue1");
    Source source(&que1, &st, "source");

    AvgQueueSizeStat avgSizeStat(que1, "avg_queue_size");
    attach_stat(avgSizeStat, source._prodEvent);

    AvgTimeStat avgTimeStat1(sink, "avg_time_branch1");
    attach_stat(avgTimeStat1, source._prodEvent);

    AvgTimeStat avgTimeStat2(sink2, "avg_time_branch2");
    attach_stat(avgTimeStat2, source._prodEvent);

    Tick rl = interarrival * 50000;

    BaseStat::setTransitory(rl / 10);

    SIMUL.run(rl, nruns);
    cout << "The average queue length is "
         << avgSizeStat.getMean() << endl;
    cout << "with a 95% confidence interval of "
         << avgSizeStat.getConfInterval(BaseStat::C95) << endl;

    cout << "Theoretical: " << rho / (1 - rho) << endl;

    cout << "The average time in branch1 is "
         << avgTimeStat1.getMean() << endl;
    cout << "The average time in branch2 is "
         << avgTimeStat2.getMean() << endl;
}
