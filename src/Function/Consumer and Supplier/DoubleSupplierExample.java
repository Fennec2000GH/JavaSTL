
import java.util.function.DoubleSupplier;
import java.util.Random;

public class DoubleSupplierExample {
    public static void main(String[] args) {
        // getAsDouble
        DoubleSupplier zero = () -> 0.0;
        DoubleSupplier random = () -> {
            Random rand = new Random(System.currentTimeMillis());
            double randDouble = rand.nextDouble();
            return randDouble;
        };
        System.out.printf("zero.getAsDouble() = %f%n", zero.getAsDouble());
        System.out.printf("random.getAsDouble() = %f%n", random.getAsDouble());

    }
}
