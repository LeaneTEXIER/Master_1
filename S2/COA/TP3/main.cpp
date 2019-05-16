#include "CImg.h"
using namespace cimg_library;

#include "rect.hpp"
#include "triangle.hpp"
#include "circle.hpp"
#include "shapeGroup.hpp"
#include <iostream>
#include <sstream>
#include <iomanip>
#include <vector>
using namespace std;

int main()
{

    int width = 700;
    int height = 400;

    CImgDisplay main_disp(width, height, "Main Display", 0);
    int x = 0, y = 0;

    std::vector<Shape *> shapes;

    while (!main_disp.is_closed() && !main_disp.is_keyESC() && !main_disp.is_keyQ()) {
      main_disp.wait();           // wait for a mouse or keyboard event

      x = main_disp.mouse_x();    // get the x position of the mouse, relative to the window
      y = main_disp.mouse_y();    // get the x position of the mouse, relative to the window

      bool click = main_disp.button(); // true if the mouse has been clicked
      bool keyT = main_disp.is_keyT();
      bool keyR = main_disp.is_keyR();
      bool keyC = main_disp.is_keyC();
      bool keyD = main_disp.is_keyD();
      bool keyM = main_disp.is_keyM();
      bool keyG = main_disp.is_keyG();
      bool keyU = main_disp.is_keyU();

      CImg<unsigned char> img(main_disp.width(),main_disp.height(), 1, 3, 0);

      if (click){
        for (Shape *p : shapes){
          if(p->is_inside(x,y)){
            p->select();
          }
        }
      }
      else if (keyT){
        Shape *t = new Triangle(width/2, height/2, 75, 75);
        shapes.push_back(t);
      }
      else if (keyR){
        Shape *r = new Rect(width/2, height/2, 150, 100);
        shapes.push_back(r);
      }
      else if (keyC){
        Shape *c = new Circle(width/2, height/2, 75);
        shapes.push_back(c);
      }
      else if (keyD){
        int i = 0;
        while (i < shapes.size()){
          if(shapes[i]->is_selected()){
            shapes.erase(shapes.begin() + i);
          } else{
            i++;
          }
        }
      }
      else if (keyM){
        for (Shape *p : shapes){
          if(p->is_selected()){
            ShapeGroup *sg = dynamic_cast<ShapeGroup*>(p);
            if(sg){
              vector<Shape *> sGroup = sg->getShapes();
              int xmove = x-sGroup[0]->getXX();
              int ymove = y-sGroup[0]->getYY();
                cout << xmove << endl;
                  cout << ymove << endl;
              for(Shape *pf : sGroup){
                cout << "shapeF" << endl;
                pf->move(xmove, ymove);
              }
            } else{
              p->move(x-p->getXX(), y-p->getYY());
            }
          }
        }
      }
      else if (keyG){
        int i = 0;
        ShapeGroup *sg = new ShapeGroup(0,0);
        while (i < shapes.size()){
          if(shapes[i]->is_selected()){
            ShapeGroup *isSg = dynamic_cast<ShapeGroup*>(shapes[i]);
            if(isSg){
              for(Shape *pf : isSg->getShapes()){
                sg->addShape(pf);
              }
            } else{
              sg->addShape(shapes[i]);
            }
            shapes.erase(shapes.begin() + i);
          } else{
            i++;
          }
        }
        shapes.push_back(sg);
      }
      else if (keyU){
        int i = 0;
        while (i < shapes.size()){
          ShapeGroup *sg = dynamic_cast<ShapeGroup*>(shapes[i]);
          if(shapes[i]->is_selected() && sg){
            for(Shape *p : sg->getShapes()){
              shapes.push_back(p);
            }
            shapes.erase(shapes.begin() + i);
          } else{
            i++;
          }
        }
      }
      for (Shape *p : shapes) p->draw(img);

      img.display(main_disp);
      main_disp.wait(50);           // wait for 20 milliseconds
  }
  cout << "End!!" << endl;

}
