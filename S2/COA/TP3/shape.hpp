#ifndef __SHAPE_H__
#define __SHAPE_H__

#include <iostream>
#include "CImg.h"
using namespace cimg_library;
using namespace std;

#define TRACE(x) {cout << "executing " #x << endl; x;}
#define PR(x) cout << "    " #x << " = " << x << endl;

const unsigned char
    red[]   = { 255,0,0 },
    green[] = { 0,255,0 },
    blue [] = { 0,0,255 },
    black[] = { 0,0,0 };

class Shape {
 protected:
  int xx,yy,selected;
 public:
  Shape(int x, int y);
  virtual ~Shape();

  void setSelected(int sel);
  void move(int x, int y);
  bool is_selected();
  void select();
  int getXX();
  int getYY();

  virtual void draw(CImg<unsigned char> &img) = 0;
  virtual void resize(int scale) = 0;
  virtual void rotate(int degree) = 0;
  virtual bool is_inside(int x, int y) = 0;
};

#endif
