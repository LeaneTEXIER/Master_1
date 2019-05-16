#include "triangle.hpp"
#include "CImg.h"

Triangle::Triangle(int x, int y, int a, int b) : Shape(x,y), aa(a), bb(b) {}

void Triangle::draw(CImg<unsigned char> &img)
{
  cout << "Triangle::draw() called" << endl;
  if(selected){
    img.draw_triangle(xx, yy-bb, xx-aa, yy+bb/2, xx+aa, yy+bb/2, blue, 0.3);
  } else {
    img.draw_triangle(xx, yy-bb, xx-aa, yy+bb/2, xx+aa, yy+bb/2, blue);
  }
}

void Triangle::resize(int scale)
{
  cout << "Triangle::resize() called" << endl;
  aa *= scale;
  bb *= scale;
  PR(xx); PR(yy); PR(aa); PR(bb);
}

void Triangle::rotate(int degree)
{
  cout << "Triangle::rotate() called" << endl;
  PR(xx); PR(yy); PR(aa); PR(bb);
}

bool Triangle::is_inside(int x, int y){
  int a1 = xx;
  int a2 = yy-bb;
  int b1 = xx-aa;
  int b2 = yy+bb/2;
  int c1 = xx+aa;
  int c2 = yy+bb/2;

  float z1,z2,z3;
  z1 = a1 * (b2 - y) + b1 * (y - a2) + x * (a2 - b2);
  z2 = b1 * (c2 - y) + c1 * (y - b2) + x * (b2 - c2);
  z3 = c1 * (a2 - y) + a1 * (y - c2) + x * (c2 - a2);

  return ((z1 > 0 && z2 > 0 && z3 > 0) || (z1 < 0 && z2 < 0 && z3 < 0));
}
