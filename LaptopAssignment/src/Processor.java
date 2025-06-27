public class Processor {
    String generation;
    float speedInGigHz;

    Processor(String generation, float speedInGigHz){
        this.generation = generation;
        this.speedInGigHz = speedInGigHz;
    }

    public void displayProperties(){
        System.out.println("Processor Generation: "+generation+" Speed in GigHz: "+speedInGigHz);

    }

}
