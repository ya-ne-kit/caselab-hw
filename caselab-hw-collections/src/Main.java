public class Main {
    public static void main(String[] args) {
        Test testAL = new Test();
        for (int i = 0; i < 5; i++) {
            testAL.start("AL");
        }
        System.out.println("ArrayList test: \n---------------------------");
        testAL.getAverageResult();


        Test testLL = new Test();
        for (int i = 0; i < 5; i++) {
            testLL.start("LL");
        }
        System.out.println("LinkedList test: \n---------------------------");
        testLL.getAverageResult();


        Test testTS = new Test();
        for (int i = 0; i < 5; i++) {
            testTS.start("TS");
        }
        System.out.println("TreeSet test: \n---------------------------");
        testTS.getAverageResult();


        Test testHS = new Test();
        for (int i = 0; i < 5; i++) {
            testHS.start("HS");
        }
        System.out.println("HashSet test: \n---------------------------");
        testHS.getAverageResult();
    }
}