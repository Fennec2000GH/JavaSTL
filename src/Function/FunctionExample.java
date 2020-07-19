
package Function;
import java.util.TreeMap;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // apply
        Function<Integer, Integer> negate = num -> -num;
        System.out.printf("negate(1) = %s%n", negate.apply(1));

        // andThen
        Function<Integer, Integer> timesTen = num -> num * 10;
        System.out.printf("negate.andThen(timesTen).apply(10) = %s%n", negate.andThen(timesTen).apply(10));

        // compose
        Function<Integer, Integer> negateTimesTen = timesTen.compose(negate);
        System.out.printf("negateTimesTen.apply(10) = %s%n", negateTimesTen.apply(10));

        // identity
        Function<Boolean, Boolean> id = Function.identity();
        System.out.printf("id.apply(True) = %s%n", id.apply(true));
    }
}
