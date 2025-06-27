public class Laptop {
    String brand, model;

    Processor processorObj;
    RandomAccessMemory ramObj;
    HardDisk hddObj;

    Laptop(Processor processorObj, RandomAccessMemory ramObj, HardDisk hddObj){
        this.processorObj = processorObj;
        this.ramObj = ramObj;
        this.hddObj = hddObj;
    }

    public void DisplayLaptopProperties(){
        System.out.println("The Laptop properties are:");
        System.out.println("Brand: "+brand);
        System.out.println("Model: "+model);
       processorObj.displayProperties();
       ramObj.displayProperties();
       hddObj.displayProperties();
    }

}
