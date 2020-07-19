
import java.util.function.BooleanSupplier;
import java.util.Random;

class TrueSupplier implements BooleanSupplier {
    @Override
    public boolean getAsBoolean() { return true; }
}

class FalseSupplier implements BooleanSupplier {
    @Override
    public boolean getAsBoolean() { return false; }
}

class RandomBoolean implements BooleanSupplier {
    @Override
    public boolean getAsBoolean() {
        Random rand = new Random(System.currentTimeMillis());
        int randint = rand.nextInt();
        return randint % 2 == 0;
    }
}

public class BooleanSupplierExample {
    public static void main(String[] args) {
        // getAsBoolean
        TrueSupplier ts = new TrueSupplier();
        FalseSupplier fs = new FalseSupplier();
        RandomBoolean rb = new RandomBoolean();

        for (int i = 0; i < 20; i++) { System.out.printf("%s %s %s%n", ts.getAsBoolean(), fs.getAsBoolean(), rb.getAsBoolean()); }
    }
}
