
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionExample {
    public static void main(String[] args) {
        // apply
        BiFunction<Double, Double, Double> power = (base, exponent) -> Math.pow(base, exponent);
        System.out.printf("power(2.0, 12.0) = %f%n", power.apply(2.0, 12.0));

        // andThen
        Function<Number, Integer> even = num -> num.intValue() % 2;
        System.out.printf("power.andThen(even).apply(2.0, 12.0) = %d%n", power.andThen(even).apply(2.0, 12.0));

    }
}
