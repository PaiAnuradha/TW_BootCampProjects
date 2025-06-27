public class RandomAccessMemory {
    int size;
    String type;

    RandomAccessMemory(int size, String type) {
        this.size = size;
        this.type = type;
    }

    public void displayProperties(){
        System.out.println("RAM Size: "+size+ "type: "+type);
    }
}
