package uz.pdp.websocketdemo;

public interface BaseInterface {


    default void printHello(String name){
        System.out.println("Hello "+name);
    }
}
