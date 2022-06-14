package uz.pdp.websocketdemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IteratorExample {

    public static void main(String[] args) {
        List<B> a = new ArrayList<>();
        B instance = B.getInstance();

        HashSet<B> set = new HashSet<>();

        set.forEach(System.out::println);
        instance.setFirstName("islom");
        instance.setLastName("isoqov");
        set.add(instance);
        B instance1 = B.getInstance();
        instance1.setFirstName("Javoxir");
        instance1.setLastName("kkkkk");
        set.add(instance1);
        B instance2 = B.getInstance();
        instance2.setFirstName("Abror");
        instance2.setLastName("muxtor");
        set.add(instance2);

        for (B b : set) {
            System.out.println(b.getFirstName() + b.getLastName());
        }
    }

}

