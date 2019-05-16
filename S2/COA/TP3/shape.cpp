#include "shape.hpp"
#include "CImg.h"
#include <iostream>

Shape::Shape(int x,int y) : xx(x), yy(y), selected(false) {}

void Shape::setSelected(int sel){
  selected = sel;
}

void Shape::move(int x, int y)
{
  xx += x; yy += y;
  cout << "Shape::move called:\n";
  PR(xx); PR(yy);
}

 Shape::~Shape() { }

 bool Shape::is_selected(){
   return selected;
 }

 void Shape::select(){
   selected = !selected;
 }

 int Shape::getXX(){
   return xx;
 }

 int Shape::getYY(){
   return yy;
 }
