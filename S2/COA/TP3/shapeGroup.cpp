#include "shapeGroup.hpp"
#include "CImg.h"

ShapeGroup::ShapeGroup(int x, int y) : Shape(x,y) {}

vector<Shape *> ShapeGroup::getShapes() {
  return shapes;
}

void ShapeGroup::draw(CImg<unsigned char> &img)
{
  cout << "ShapeGroup::draw() called" << endl;
  for (Shape *p : shapes){
    p->setSelected(selected);
    p->draw(img);
  }
}

void ShapeGroup::resize(int scale)
{
  cout << "ShapeGroup::resize() called" << endl;
  PR(xx); 
  PR(yy);
}

void ShapeGroup::rotate(int degree)
{
  cout << "ShapeGroup::rotate() called" << endl;
  PR(xx); PR(yy);
}

bool ShapeGroup::is_inside(int x, int y){
    for (Shape *p : shapes){
        if (p->is_inside(x,y)){
            return true;
        }
    }
    return false;
}

void ShapeGroup::addShape(Shape *s)
{
  cout << "ShapeGroup::addShape() called" << endl;
  shapes.push_back(s);
}
