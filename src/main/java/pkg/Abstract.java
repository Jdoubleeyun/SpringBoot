package pkg;

abstract class Bird {
    private int x,y,z;

    void fly(int x, int y, int z) {
        printLocation();
        System.out.println("이동합니다. ");
        this.x = x;
        this.y = y;
        this.z = z;
        if(flyable(z)){
            this.z = z;
        } else {
            System.out.println("그 높이로는 날 수 없습니다. ");
        }
        printLocation();
    }
    abstract boolean flyable(int z);

    public void printLocation(){
        System.out.println("현재위치: {" +x +", "+y +", " +z + "} ");
    }
}

class Pigeon extends Bird {

    @Override
    boolean flyable(int z) {
        return z < 10000;
    }
}
class Peacock extends Bird {

    @Override
    boolean flyable(int z) {
        return false;
    }
}

public class Abstract {
    public static void main(String[] args) {
        Bird Pigeon  = new Pigeon();
        Bird Peacock = new Peacock();

        System.out.println("비둘기");
        Pigeon.fly( 1, 2, 3);

    }
}