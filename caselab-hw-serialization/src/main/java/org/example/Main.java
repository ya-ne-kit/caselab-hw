package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Я родился", 0));
        persons.add(new Person("Ребенок", 3));
        persons.add(new Person("Детсадовец", 6));
        persons.add(new Person("Первоклассник", 7));
        persons.add(new Person("Школьник", 17));
        persons.add(new Person("Уже не школьник", 18));
        persons.add(new Person("Работяга", 64));
        persons.add(new Person("Помнит перфокарты", 65));

        FileOutputStream fos = new FileOutputStream("persons.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(persons);
        oos.close();

        FileInputStream fis = new FileInputStream("persons.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Person> deserializedPersons = (List<Person>) ois.readObject();
        ois.close();

        deserializedPersons.forEach(System.out::println);
    }
}