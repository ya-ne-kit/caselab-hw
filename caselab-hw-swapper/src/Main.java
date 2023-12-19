import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Исходная HashMap: " + map);

        Map<Integer, String> swappedMap = swap(map);

        System.out.println("Результат: " + swappedMap);
    }

    public static <K, V> Map<V, K> swap(Map<K, V> map) {
        Map<V, K> swappedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }

        return swappedMap;
    }
}
