package pkg;
//interface MyInterface {
//        void myMethod(int x);
//}

interface Flyable {
    void fly(int x, int y, int z);
}
class Pigeon implements Flyable {
    private int x, y, z;
    @Override
    public void fly(int x, int y, int z) {
        printLocation();
        System.out.println("날아감");
        this.x = x;
        this.y = y;
        this.z = z;
        printLocation();

    }
    public void printLocation(){
        System.out.println("현재위치: {" +x +", "+y +", " +z + "} ");
    }
}


public class Interface {
    public static void main(String[] args) {
        Flyable pigeon = new Pigeon();
        pigeon.fly(1, 3, 3);
    }
}
