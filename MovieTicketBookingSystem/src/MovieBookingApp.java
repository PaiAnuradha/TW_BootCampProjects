import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieBookingApp {
    private static final Logger logger = Logger.getLogger(MovieBookingApp.class.getName());
    public static void main(String[] args) {

       final int threadPoolSize = 3;
       final int numOfBookings = 5;

        try( ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize)){
            for(int i = 1; i< numOfBookings; i++){
               executor.submit(new BookingTask(i));
           }
           executor.shutdown();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Booking Failed: " + e.getMessage(), e);
        }
    }
}