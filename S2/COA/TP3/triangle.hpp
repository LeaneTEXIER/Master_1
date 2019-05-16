#ifndef __TRIANGLE_H__
#define __TRIANGLE_H__

#include "shape.hpp"
#include "CImg.h"

class Triangle : public Shape {
  int aa, bb;
 public:
  Triangle(int x, int y, int a, int b);

  void move(int xx, int yy) {};

  virtual void draw(CImg<unsigned char> &img);
  virtual void resize(int scale);
  virtual void rotate(int degree);
  virtual bool is_inside(int x, int y);
};

#endif
