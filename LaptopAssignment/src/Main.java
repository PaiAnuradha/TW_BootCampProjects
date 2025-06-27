import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Processor[] processors = {
                new Processor("i3",3),
                new Processor("i5",4)
        };

        RandomAccessMemory[] RAM = {
          new RandomAccessMemory(4,"read only"),
          new RandomAccessMemory(16, "read-write")
        };

        HardDisk[] hdd = {
          new HardDisk(100, "ssd"),
                new HardDisk(120,"magnetic")
        };

        ArrayList<Laptop> laptops= new ArrayList<>();
        for(int i =0; i< processors.length; i++){

            for (int j = 0; j < RAM.length; j++) {
                for (int k=0;k<hdd.length; k++){
                    Laptop lp = new Laptop(processors[i],RAM[j],hdd[k]);
                    lp.brand = "Dell";
                    lp.model = "inspiron";
                    laptops.add(lp);
                }

            }
        }

        System.out.println("Different Laptop present with the vendor and its configuration:");
        //Display properties
        for(Laptop lp: laptops){
            System.out.println();
            System.out.println("Brand: "+lp.brand);
            System.out.println("Model: "+lp.model);
            lp.processorObj.displayProperties();
            lp.ramObj.displayProperties();
            lp.hddObj.displayProperties();
        }

    }
}