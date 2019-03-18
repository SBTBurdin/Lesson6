package ru.sbt.lsn6;
 
public class Application {
    public static void main(String[] args) {
        BeanA from = new BeanA("Object From", 0);
        BeanB to = new BeanB("Second object", 1, false);

        println("Before", from, to);
        BeanUtils.assign(from, to);
        println("After", from, to);
    }

    private static void println(Object... args) {
        for (Object o : args) {
            System.out.println(o.toString());
        }
    }
}
