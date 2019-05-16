#ifndef __RECT_H__
#define __RECT_H__

#include "shape.hpp"

class Rect : public Shape {
  int aa, bb;
 public:
  Rect(int x, int y, int a, int b);
  virtual void draw(CImg<unsigned char> &img);
  virtual void resize(int scale);
  virtual void rotate(int degree);
  virtual bool is_inside(int x, int y);
};

#endif
