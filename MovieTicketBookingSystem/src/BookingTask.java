import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingTask implements  Runnable{

    int id;
    private static final Logger logger = Logger.getLogger(BookingTask.class.getName());

    BookingTask(int id){
        this.id = id;   }

    @Override
    public void run() {
        try{
            logger.info("Booking"+id+": Booking Received");
            Thread.sleep(2000);
            logger.info("Booking"+id+": Payment Processed");
            Thread.sleep(5000);
            logger.info("Booking"+id+": Ticket Confirmed");
        }
        catch (Exception ex){
            logger.log(Level.SEVERE,"Booking "+id+": failed"+ ex.getMessage());
        }

    }
}
