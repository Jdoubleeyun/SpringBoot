package Quiz;

public class Parent extends Human implements Walkable, Runnable {
    public Parent(String name, int age) {
        super(name, age, 3);
    }

    @Override
    public void walk(int x, int y) {
        printWhoami();
        System.out.println("walk speed: " + speed);
        this.x = x;
        this.y = y;
        System.out.println("walked to "+ getLocation());
    }

    @Override
    public void run(int x, int y) {
        printWhoami();
        System.out.println("run speed: "+ (speed + 2));
        this.x = x;
        this.y = y;
        System.out.println("ran to" + getLocation());
    }
}
