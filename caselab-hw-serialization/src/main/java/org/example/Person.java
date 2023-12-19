package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;
    private final String name;
    private final Integer age;
    private transient String formOfActivity;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setActivity() {
        if (age >= 65) {
            formOfActivity = "на пенсии";
        } else if (age >= 24) {
            formOfActivity = "работает";
        } else if (age >= 18) {
            formOfActivity = "учится в институте";
        } else if (age >= 7) {
            formOfActivity = "учится в школе";
        } else if (age >= 3) {
            formOfActivity = "ходит в детский сад";
        } else {
            formOfActivity = "сидит дома";
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setActivity();
    }

    @Override
    public String toString() {
        return "Person{Имя: " + name +
                "; Возраст: " + age +
                "; Деятельность: " + formOfActivity + "}";
    }
}