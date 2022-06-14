package uz.pdp.websocketdemo;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class B {
    static B obj = new B();
    String lastName;
    String firstName;
    int age;

    private B(){}

    static B getInstance(){
        return obj;
    }
}
