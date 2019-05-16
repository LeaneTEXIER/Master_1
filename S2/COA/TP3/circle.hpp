#ifndef __CIRCLE_H__
#define __CIRCLE_H__

#include "shape.hpp"
#include "CImg.h"

class Circle : public Shape {
  int rr;
 public:
  Circle(int x, int y, int r);
  virtual void draw(CImg<unsigned char> &img);
  virtual void resize(int scale);
  virtual void rotate(int degree);
  virtual bool is_inside(int x, int y);
};

#endif
