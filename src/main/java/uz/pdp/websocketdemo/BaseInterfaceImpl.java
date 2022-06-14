package uz.pdp.websocketdemo;

public class BaseInterfaceImpl implements BaseInterface {
    @Override
    public void printHello(String name) {
        System.out.println("salom "+name+" brat");
    }
}
