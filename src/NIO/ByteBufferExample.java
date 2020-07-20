
import java.nio.*;

public class ByteBufferExample {
    public static void main(String[] args) {
        // Constructing ByteBuffer
        {
            System.out.println("Constructing ByteBuffer");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb = ByteBuffer.allocateDirect(20);
            bb = ByteBuffer.allocate(20);
            ByteBuffer aligned = bb.alignedSlice(4);
            for (int i = 0; i < 4; i++) { System.out.print(bb.alignmentOffset(i, 4)); }
        }

        // Converting to other buffer types
        {
            System.out.println("\nConverting to other buffer typesonstructing ByteBuffer");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb = ByteBuffer.allocate(40);
            bb.putDouble(1234.5678);
            System.out.println(bb.getClass());
            CharBuffer cb = bb.asCharBuffer();
            System.out.println(cb.getClass());
            DoubleBuffer db = bb.asDoubleBuffer();
            System.out.println(db.getClass());
            FloatBuffer fb = bb.asFloatBuffer();
            System.out.println(fb.getClass());
            IntBuffer ib = bb.asIntBuffer();
            System.out.println(ib.getClass());
            LongBuffer lb = bb.asLongBuffer();
            System.out.println(lb.getClass());
            ByteBuffer readonly = bb.asReadOnlyBuffer();
            System.out.println(readonly.getClass());
            ShortBuffer sb = bb.asShortBuffer();
            System.out.println(sb.getClass());
        }

        // Setting and getting values
        {
            System.out.println("\nSetting and getting values");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb = ByteBuffer.allocate(40);
            bb.put((byte)1); // index 0
            bb.putChar('2'); // index 1
            bb.putDouble(3.333); // index 3
            bb.putFloat(4); // index 11
            bb.putInt(5); // index 15
            bb.putLong(6); // index 19
            bb.putShort((short)7); // index 27
            System.out.printf("byte = %s%n", bb.get(0));
            System.out.printf("char = %s%n", bb.getChar(1));
            System.out.printf("double = %f%n", bb.getDouble(3));
            System.out.printf("float = %f%n", bb.getFloat(11));
            System.out.printf("int = %d%n", bb.getInt(15));
            System.out.printf("long = %d%n", bb.getLong(19));
            System.out.printf("short = %d%n", bb.getShort(27));
        }

        // Bulk put
        {
            System.out.println("\nBulk put");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb = ByteBuffer.allocate(80);
            String test = "Ich liebe der Creepypasta Fangemeinde";
            System.out.printf("string length = %d%n", test.length());
            bb.put(test.getBytes());
            for (int i = 0; i < 20; i ++) { System.out.print(String.valueOf(bb.getChar(i * 2))); }
            bb.put("ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(), 0, 12);
            for (int i = 0; i < 20; i ++) { System.out.print(String.valueOf(bb.getChar(i * 2))); }
        }

        // Direct buffer
        {
            System.out.println("\nDirect buffer");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb_direct = ByteBuffer.allocateDirect(10);
            bb_direct.put(new byte[]{1, 2, 3, 4, 5, 6});
            for (int i = 0; i < 10; i++) { System.out.println(bb_direct.get(i)); }
            bb_direct.clear();
            System.gc();
        }

        // Other functions
        {
            System.out.println("\nOther functions");
            for (int i = 0; i < 40; i++) { System.out.print("-"); }
            System.out.println();
            ByteBuffer bb = ByteBuffer.allocate(40);
            bb.put("some string".getBytes());
            byte[] bb_array = bb.array();
            System.out.print("array = ");
            for (byte b : bb_array) { System.out.print(b + ", "); }
            bb.position(5);
            bb.compact();
            System.out.print("bb = ");
            for (int i = 0; i < 10; i++) { System.out.print(bb.get(i) + ", "); }
            ByteBuffer bb_duplicate  = bb.duplicate();
            System.out.printf("compareTo = %d%n", bb.compareTo(bb_duplicate));
            System.out.printf("equals = %s%n", bb.equals(bb_duplicate));
            System.out.printf("limit = %d%n", bb.limit());
            bb.limit(10);
            System.out.print("bb = ");
            for (int i = 0; i < 10; i++) { System.out.print(bb.get(i) + ", "); }
            System.out.println();
            bb.position(0);
            bb.mark();
            ByteBuffer bb_copy = ByteBuffer.allocate(bb.limit());
            bb_copy.put(bb);
            bb.position(2);
            bb.put("abc".getBytes());
            System.out.print("bb = ");
            for (int i = 0; i < 10; i++) { System.out.print(bb.get(i) + ", "); }
            System.out.println();
            System.out.print("bb_copy = ");
            for (int i = 0; i < 10; i++) { System.out.print(bb_copy.get(i) + ", "); }
            System.out.println();
            System.out.printf("mismatch (index) = %d%n", bb.mismatch(bb_duplicate));
            System.out.printf("order = %s%n", bb.order());
            bb.order(ByteOrder.BIG_ENDIAN);
            bb.rewind();
            System.out.printf("hasRemaining = %s%n", bb.hasRemaining());
            System.out.printf("remaining = %d%n", bb.remaining());
            System.out.printf("hasArray = %s%n", bb.hasArray());
            ByteBuffer bb_wrapped = ByteBuffer.wrap("wrapped text".getBytes());
            System.out.print("bb_wrapped = ");
            System.out.println(bb_wrapped.toString());
            ByteBuffer bb_sliced = bb.slice(0, 6);
            System.out.println();
            System.out.print("bb_sliced = ");
            System.out.println(bb_sliced.toString());
            bb = ByteBuffer.allocateDirect(40);
            System.out.printf("isDirect = %s%n", bb.isDirect());
            bb = bb.asReadOnlyBuffer();
            System.out.printf("isReadOnly = %s%n", bb.isReadOnly());
            bb.clear();
            System.gc();
        }
    }
}
