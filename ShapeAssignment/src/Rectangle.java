public class Rectangle extends Shape{
    double length, width;
    Rectangle(){
        //DO NOTHING
    }
    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }
    @Override
    double calculateArea() {
        area = length * width;
        return area;
    }

    @Override
    double calculatePerimeter() {
        perimeter = 2*(length+width);
        return perimeter;
    }

    public void setDimension(double length, double width){
        this.length = length;
        this.width = width;
    }

    public void setDimension(double length){
        this.length = length;
        this.width = length;
    }
}
