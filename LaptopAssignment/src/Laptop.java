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
        System.out.println("Processor generation is  "+processorObj.generation);
        System.out.println("Processor Speed is "+processorObj.speedInGigHz);
        System.out.println("RAM size is" +ramObj.size);
        System.out.println("RAM type is "+ramObj.type);
        System.out.println("HDD type is "+ hddObj.type);
        System.out.println("HDD size is "+ hddObj.size);
    }

}
