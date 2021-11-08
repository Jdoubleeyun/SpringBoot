package Quiz;

public class Child extends Human implements Walkable, Runnable, Swimmable{
    public Child(String name, int age) {
        super(name, age, 5);
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

    @Override
    public void Swim(int x, int y) {
        printWhoami();
        System.out.println("swim speed: "+ (speed + 1));
        this.x = x;
        this.y = y;
        System.out.println("swum to" + getLocation());
    }
}
