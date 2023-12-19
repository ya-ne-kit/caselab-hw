public class ArrayListOverflowException extends Exception {

    private String localizedMessage;

    public ArrayListOverflowException() {
        super("ArrayList is full. Cannot add more than 10 elements.");
    }

    public ArrayListOverflowException(String localizedMessage) {
        this.localizedMessage = localizedMessage.isBlank() ?
                "ArrayList заполнен. Заполнение более чем 10 элементами запрещено." :
                localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println("Additional information: ArrayList cannot hold more than 10 elements.");
    }
}
