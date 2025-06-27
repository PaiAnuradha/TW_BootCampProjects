public class Circle extends Shape{
    double radius;
    Circle(int radius){
        this.radius = radius;
    }
    @Override
    double calculateArea() {
        area = Math.PI * radius * radius;
        return area;
    }

    @Override
    double calculatePerimeter() {
        perimeter = 2*Math.PI*radius;
        return perimeter;
    }
}