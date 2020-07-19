
import java.util.function.IntSupplier;
import java.util.Random;

public class IntSupplierExample {
    public static void main(String[] args) {
        // getAsInt
        int base = 1;
        IntSupplier timesTwo = () -> base * 2;
        IntSupplier randomEven = () -> {
            Random rand = new Random(System.currentTimeMillis());
            int randint = rand.nextInt();
            return randint % 2 == 0 ? randint : randint + 1;
        };
        System.out.printf("timesTwo.getAsInt() = %d%n", timesTwo.getAsInt());
        System.out.printf("randomEven.getAsInt() = %d%n", randomEven.getAsInt());
    }
}
