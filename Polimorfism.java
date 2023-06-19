abstract class Car {
    public abstract void createCar();
    public String color;
}

class Ford extends Car {
    public void createCar() {
        System.out.println("Create a Ford");
        color = "Grey";
        System.out.println(color);
    }
}

class Toyota extends Car {
    public void createCar() {
        System.out.println("Create a Toyota");
        color = "Black";
        System.out.println(color);
    }
}

public class Polimorfism {
    public static void main(String[] args) {
        Car ford = new Ford();
        Car toyota = new Toyota();
        ford.createCar();
        toyota.createCar();
    }
}