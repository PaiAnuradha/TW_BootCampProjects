public class Triangle extends Shape{
    double side1, side2, side3;
    Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    @Override
    double calculateArea() {
        double semiPerimeter = (double)calculatePerimeter()/2;
        area  = Math.sqrt(semiPerimeter*(semiPerimeter-side1)*(semiPerimeter-side2)*(semiPerimeter-side3));
        return area;
    }

    @Override
    double calculatePerimeter() {
        perimeter = side1 + side2 + side3;
        return perimeter;
    }
}
