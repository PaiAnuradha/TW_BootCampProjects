public class HardDisk {
    int size;
    String type;

    HardDisk(int size, String type) {
        this.size = size;
        this.type = type;
    }

    public void displayProperties(){
        System.out.println(" HardDisk Size: "+size+" Type: "+type);
    }
}
