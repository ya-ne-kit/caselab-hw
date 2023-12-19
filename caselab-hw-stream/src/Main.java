import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3);
        Set<String> langs = new HashSet<>();
        developerStream.filter(developer -> {
            HashSet<String> tmp = new HashSet<>(developer.getLanguages());
            tmp.retainAll(langs);
            return tmp.isEmpty();
        }).forEach(developer -> {
            System.out.println(developer.getName());
            langs.addAll(developer.getLanguages());
        });
    }
}