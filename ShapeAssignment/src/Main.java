import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Circle circleObj = new Circle(10);
        Rectangle rectObj = new Rectangle(10,20);
        Triangle triangleObj = new Triangle(5,6,7);
        ArrayList<Shape> shapeList = new ArrayList<Shape>();
        shapeList.add(circleObj);
        shapeList.add(rectObj);
        shapeList.add(triangleObj);

        Rectangle rectObj2 = new Rectangle();
        rectObj2.setDimension(30,30);
        Rectangle rectObj3 = new Rectangle();
        rectObj3.setDimension(20);

        shapeList.add(rectObj2);
        shapeList.add(rectObj3);

        for(Shape shapeObj: shapeList) {
            shapeObj.calculateArea();
            System.out.println("The Area of " + shapeObj.getClass() + " " + shapeObj.area);
            double perimeter = shapeObj.calculatePerimeter();
            System.out.println("The perimeter of " + shapeObj.getClass() + " " + shapeObj.perimeter);
        }
    }
}