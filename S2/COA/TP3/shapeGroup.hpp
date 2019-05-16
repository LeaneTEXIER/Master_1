#include <iostream>
#include "CImg.h"
#include "shape.hpp"
#include <vector>
using namespace cimg_library;
using namespace std;

class ShapeGroup : public Shape {
    vector<Shape *> shapes;

    public:
    ShapeGroup(int x, int y);

    vector<Shape *> getShapes();
    void move(int xx, int yy) {};
    void addShape(Shape *s);

    virtual void draw(CImg<unsigned char> &img);
    virtual void resize(int scale);
    virtual void rotate(int degree);
    virtual bool is_inside(int x, int y);
};