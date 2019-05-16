#include "rect.hpp"
#include "CImg.h"

Rect::Rect(int x, int y, int a, int b) : Shape(x,y), aa(a), bb(b) {}

void Rect::draw(CImg<unsigned char> &img)
{
  cout << "Rect::draw() called" << endl;
  if(selected){
    img.draw_rectangle(xx-aa/2, yy-bb/2, xx+aa/2, yy+bb/2, red, 0.3);
  } else{
    img.draw_rectangle(xx-aa/2, yy-bb/2, xx+aa/2, yy+bb/2, red);
  }
}

void Rect::resize(int scale)
{
  cout << "Rect::resize() called" << endl;
  aa *= scale;
  bb *= scale;
  PR(xx); PR(yy); PR(aa); PR(bb);
}

void Rect::rotate(int degree)
{
  cout << "Rect::rotate() called" << endl;
  PR(xx); PR(yy); PR(aa); PR(bb);
}

bool Rect::is_inside(int x, int y){
  return ((xx-aa/2<=x) && (x<=xx+aa/2) && (yy-bb/2<=y) && (y<=yy+bb/2));
}
