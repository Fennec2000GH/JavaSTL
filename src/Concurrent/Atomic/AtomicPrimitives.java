
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicPrimitives {
    public static void main(String[] args) {
        // Boolean
        {
            AtomicBoolean atBoolean = new AtomicBoolean();
            System.out.printf("Default initialized boolean value = %b\n", atBoolean.get());
            atBoolean = new AtomicBoolean(true);
            System.out.printf("Initial boolean value now = %b\n", atBoolean.get());

            atBoolean.compareAndExchange(true, false);
            System.out.printf("Boolean value after compareandExchange = %b\n", atBoolean.get());

            System.out.printf("Opaque boolean value = %b\n", atBoolean.getOpaque());
            System.out.printf("Plain boolean value = %b\n", atBoolean.getPlain());

            boolean oldValue = atBoolean.getAndSet(true);
            System.out.printf("Old value when using getAndSet = %b\n", oldValue);

            atBoolean.lazySet(false);
            System.out.printf("Boolean value after lazySet = %b\n", atBoolean);

            atBoolean.set(true);
            System.out.printf("Boolean value after set = %b\n", atBoolean);

            atBoolean.weakCompareAndSetVolatile(true, false);
            System.out.printf("Boolean value after weakCompareAndSetVolatile = %b\n", atBoolean);
        }

        // Integer
        {
            AtomicInteger atInteger = new AtomicInteger();
            System.out.printf("Default initialized integer value = %d\n", atInteger.get());
            atInteger = new AtomicInteger(18);
            System.out.printf("Integer value with custom initial value = %d\n", atInteger.get());

            int newInteger = atInteger.addAndGet(2);  // 20
            System.out.printf("Integer after adding 2 = %d\n", newInteger);
            System.out.printf("Get first then add 4, so current value = %d\n", atInteger.getAndAdd(4));
            System.out.printf("Integer now = %d\n", atInteger.getAndIncrement());  // 24
            System.out.printf("Integer after incrementing = %d\n", atInteger.getAcquire());  // 25
            System.out.printf("Decrementing integer first before getting = %d\n", atInteger.decrementAndGet());  // 24

            atInteger.set(32);
            System.out.printf("Integer after getPlain = %d\n", atInteger.getPlain());
            System.out.printf("Integer after getOpaque = %d\n", atInteger.getOpaque());

            atInteger.lazySet(42);  // 42
            System.out.printf("Integer value after lazySet = %d\n", atInteger.get());

            atInteger.weakCompareAndSetVolatile(42, 20);  // 20
            System.out.printf("intValue = %d\n", atInteger.intValue());
            System.out.printf("longValue = %d\n", atInteger.longValue());
            System.out.printf("floatValue = %f\n", atInteger.floatValue());
            System.out.printf("doubleValue = %f\n", atInteger.doubleValue());

            atInteger.accumulateAndGet(10, (int a, int b) -> a + b);  // 30
            System.out.printf("Integer after accumulating by ten using accumulator function = %d\n", atInteger.get());

        }

        // Long
        {
            AtomicLong atLong = new AtomicLong();
            System.out.printf("Default initialized long value = %d\n", atLong.get());
            atLong = new AtomicLong(18);
            System.out.printf("Long value with custom initial value = %d\n", atLong.get());

            long newLong = atLong.addAndGet(2L);  // 20
            System.out.printf("Long after adding 2 = %d\n", newLong);
            System.out.printf("Get first then add 4, so current value = %d\n", atLong.getAndAdd(4));
            System.out.printf("Long now = %d\n", atLong.getAndIncrement());  // 24
            System.out.printf("Long after incrementing = %d\n", atLong.getAcquire());  // 25
            System.out.printf("Decrementing long first before getting = %d\n", atLong.decrementAndGet());  // 24

            atLong.set(32);
            System.out.printf("Long after getPlain = %d\n", atLong.getPlain());
            System.out.printf("Long after getOpaque = %d\n", atLong.getOpaque());

            atLong.lazySet(42);  // 42
            System.out.printf("Long value after lazySet = %d\n", atLong.get());

            atLong.weakCompareAndSetVolatile(42, 20);  // 20
            System.out.printf("intValue = %d\n", atLong.intValue());
            System.out.printf("longValue = %d\n", atLong.longValue());
            System.out.printf("floatValue = %f\n", atLong.floatValue());

            System.out.printf("doubleValue = %f\n", atLong.doubleValue());

            atLong.accumulateAndGet(2, (long base, long exp) -> (long)Math.pow((double)base, (double)exp));  // 400
            System.out.printf("Long after accumulating by ten using accumulator function = %d\n", atLong.get());

        }
    }
}
