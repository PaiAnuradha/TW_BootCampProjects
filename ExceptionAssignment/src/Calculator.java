public class Calculator {
    double calDouble(int num){
        if(num == 0 ){
            throw new MyArithException("Zero is not allowed!!!");
        }
        if(num < 0){
            throw new MyArithException("Negative not allowed!!!");
        }
        return (double)num;
    }
}
