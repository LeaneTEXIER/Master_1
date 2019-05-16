#include "circle.hpp"
#include "CImg.h"
#include <math.h>

Circle::Circle(int x, int y, int r) : Shape(x,y), rr(r) {}

void Circle::draw(CImg<unsigned char> &img)
{
  cout << "Circle::draw() called" << endl;
  if(selected){
    img.draw_circle(xx, yy, rr, green, 0.3);
  } else{
    img.draw_circle(xx, yy, rr, green);
  }
}

void Circle::resize(int scale)
{
  cout << "Circle::resize() called" << endl;
  rr *= scale;
  PR(xx); PR(yy); PR(rr);
}

void Circle::rotate(int degree)
{
  cout << "Circle::rotate() called" << endl;
  PR(xx); PR(yy); PR(rr);
}

bool Circle::is_inside(int x, int y){
  return sqrt(pow(x-xx, 2)+pow(yy-y, 2))<=rr;
}
