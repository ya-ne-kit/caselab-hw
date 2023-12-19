import java.util.*;

public class Test {
    private Collection<Integer> collection;
    private final List<Results> hotResults = new ArrayList<>();
    private final List<Results> coldResults = new ArrayList<>();

    public void start(String collectionType) {
        collection = switch (collectionType) {
            case "AL" -> new ArrayList<>();
            case "LL" -> new LinkedList<>();
            case "TS" -> new TreeSet<>();
            case "HS" -> new HashSet<>();
            default -> throw new IllegalStateException("Unexpected value: " + collectionType);
        };
        processingTest(false);
        processingTest(true);
    }

    private void processingTest(boolean withHeating) {
        long beforeAdding = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            collection.add(i * i);
        }
        long afterAdding = System.nanoTime();
        if (withHeating) heating();
        long beforeAsking = System.nanoTime();
        boolean asking = collection.contains(17 * 17 * 17);
        long afterAsking = System.nanoTime();
        if (withHeating) heating();
        long beforeDeleting = System.nanoTime();
        for (int i = 10000; i < 20000; i++) {
            collection.remove(i);
        }
        long afterDeleting = System.nanoTime();
        Results res = new Results(afterAdding - beforeAdding, afterAsking - beforeAsking, afterDeleting - beforeDeleting);
        if (withHeating) {
            hotResults.add(res);
        } else {
            coldResults.add(res);
        }
    }

    private void heating() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            collection.contains(random.nextInt() * 1000);
        }
    }

    public void getResult() {
        System.out.println("COLD TESTS:");
        System.out.println("-------------------");
        for (int i = 1; i < coldResults.size() + 1; i++) {
            System.out.printf("Test %d:\n", i);
            System.out.printf("Adding: %d\n", coldResults.get(i - 1).getAdding());
            System.out.printf("Asking: %d\n", coldResults.get(i - 1).getAsking());
            System.out.printf("Removing: %d\n", coldResults.get(i - 1).getRemoving());
            System.out.println("-------------------");
        }
        System.out.println("HOT TEST:");
        for (int i = 1; i < hotResults.size() + 1; i++) {
            System.out.printf("Test %d:\n", i);
            System.out.printf("Adding: %d\n", hotResults.get(i - 1).getAdding());
            System.out.printf("Asking: %d\n", hotResults.get(i - 1).getAsking());
            System.out.printf("Removing: %d\n", hotResults.get(i - 1).getRemoving());
            System.out.println("-------------------");
        }
    }

    public void getAverageResult() {
        System.out.println("COLD TESTS:");
        System.out.println("-------------------");
        Results cold = new Results();
        for (int i = 1; i < coldResults.size() + 1; i++) {
            cold.setAdding(cold.getAdding() + coldResults.get(i - 1).getAdding());
            cold.setAsking(cold.getAsking() + coldResults.get(i - 1).getAsking());
            cold.setRemoving(cold.getRemoving() + coldResults.get(i - 1).getRemoving());
        }
        System.out.printf("Adding: %d\n", cold.getAdding() / coldResults.size());
        System.out.printf("Asking: %d\n", cold.getAsking() / coldResults.size());
        System.out.printf("Removing: %d\n", cold.getRemoving() / coldResults.size());
        System.out.println("-------------------");
        System.out.println("HOT TEST:");
        System.out.println("-------------------");
        Results hot = new Results();
        for (int i = 1; i < hotResults.size() + 1; i++) {
            hot.setAdding(hot.getAdding() + hotResults.get(i - 1).getAdding());
            hot.setAsking(hot.getAsking() + hotResults.get(i - 1).getAsking());
            hot.setRemoving(hot.getRemoving() + hotResults.get(i - 1).getRemoving());
        }
        System.out.printf("Adding: %d\n", hot.getAdding() / hotResults.size());
        System.out.printf("Asking: %d\n", hot.getAsking() / hotResults.size());
        System.out.printf("Removing: %d\n", hot.getRemoving() / hotResults.size());
        System.out.println("-------------------");
    }
}
