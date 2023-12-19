import java.util.ArrayList;

public class SmallList<T> extends ArrayList<T> {

    public boolean add(T o) {
        if (size() == 10) try {
            throw new ArrayListOverflowException();
        } catch (ArrayListOverflowException e) {
            throw new RuntimeException(e);
        }
        return super.add(o);
    }
}
