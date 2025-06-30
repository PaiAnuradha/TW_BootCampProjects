import java.util.Scanner;

public class MyCalcApp {
    public static void main(String[] args){
        try{
            int num;
            Calculator calcObj = new Calculator();
            System.out.println("Enter the number");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
            System.out.println("The double number " + num + " is :"+ calcObj.calDouble(num));
        }
        catch(MyArithException ex){
            System.out.print("Arithmetic Exception: ");
            System.out.println(ex.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
