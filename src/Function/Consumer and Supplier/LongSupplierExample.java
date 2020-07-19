
import java.util.function.LongSupplier;
import java.util.Random;

public class LongSupplierExample {
    public static void main(String[] args) {
        // getAsLong
        LongSupplier millisLong = () -> Long.valueOf(System.currentTimeMillis());
        LongSupplier nanoLong = () -> Long.valueOf(System.nanoTime());
        System.out.printf("millisLong.getAsLong() = %d%n", millisLong.getAsLong());
        System.out.printf("nanoLong.getAsLong() = %d%n", nanoLong.getAsLong());
    }
}
