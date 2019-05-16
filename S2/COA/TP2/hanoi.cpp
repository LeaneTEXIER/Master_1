#include "Stack.h"
#include <iostream>

using namespace std;

void hanoi(int n, Stack &d, Stack &a, Stack &i){
  if(n!=0){
    hanoi(n-1, d, i, a);
    a+=d.top();
    d.pop();
    std::cout << "d: " << d << std::endl;
    std::cout << "a: " << a << std::endl;
    std::cout << "i: " << i << '\n'<< std::endl;
    hanoi(n-1, i, a, d);
  }
}


int main(int argc, char *argv[])
{
  if (argc != 2){
        printf("Usage : ./hanoi <NB DISQUES>\n");
        exit(EXIT_FAILURE);
  }

  int n = atoi(argv[1]);
  Stack d, a, inter;
  for (int i=n; i>=1; i--){
    d+=i;
  }

  std::cout << "d: " << d << std::endl;
  std::cout << "a: " << a << std::endl;
  std::cout << "i: " << inter << '\n'<< std::endl;

  hanoi(n, d, a, inter);
}
