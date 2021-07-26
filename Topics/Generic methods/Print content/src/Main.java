// do not remove imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class ArrayUtils {
    // define info method here
    static <T> String info(T[] t) {
        StringBuilder result = new StringBuilder("[");

        if (t.length != 0) {
            result.append(t[0]);
            for (int i = 1; i < t.length; i++) {
                result.append(", ");
                result.append(t[i]);
            }
        }
        result.append("]");

        return result.toString();
    }
}