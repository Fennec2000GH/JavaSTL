
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.DigestException;
import java.io.UnsupportedEncodingException;

public class MessageDigestExample {
    public static void main(String[] args) {
        // ACCESSORS
        try {
            System.out.println("ACCESSORS");
            MessageDigest md2 = MessageDigest.getInstance("MD2");
            System.out.printf("algorithm: %s%n", md2.getAlgorithm());
            System.out.printf("provider: %s%n", md2.getProvider());
            md2.update("test string".getBytes("UTF-8"));
            System.out.printf("digest: %s%n", md2.digest());
            System.out.printf("digest length: %d%n", md2.getDigestLength());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException error) {
            error.getStackTrace();
        }

        // DIGEST
        try {
            System.out.println("\nDIGEST");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("test string".getBytes("UTF-8"));
            System.out.printf("digest: %s%n", md5.digest());
            System.out.printf("digest: %s%n", md5.digest("tyrannosaurus".getBytes("UTF-8")));
            System.out.printf("digest: %s%n", md5.digest("tyrannosaurus".getBytes("UTF-8"), 4, 4));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | DigestException error) {
            error.getStackTrace();
        }

        // UPDATE
        try {
            System.out.println("\nUPDATE");
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update("test string".getBytes("UTF-8"));
            System.out.printf("digest: %s%n", sha256.digest());
            sha256.update((byte)'c');
            System.out.printf("digest: %s%n", sha256.digest());
            ByteBuffer buffer = ByteBuffer.allocate(60);
            buffer.put((byte)256);
            buffer.put("test string".getBytes("UTF-8"));
            buffer.putShort(Short.valueOf("64"));
            buffer.putInt(1200);
            buffer.putLong(1234567890);
            buffer.putDouble(3.141592653);
            buffer.putChar('c');
            sha256.update(buffer);
            System.out.printf("digest: %s%n", sha256.digest());
            sha256.update("test string".getBytes("UTF-8"), 0, "test string".length());
            System.out.printf("digest: %s%n", sha256.digest());
            System.out.printf("digest: %s%n", sha256.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException error) {
            error.getStackTrace();
        }

        // OTHER FUNCTIONS
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] a = "some string".getBytes("UTF-8");
            byte[] b = "some string".getBytes("UTF-8");
            System.out.printf("are byte arrays equal? %b%n", sha512.isEqual(a, b));
            System.out.printf("string version of sha512: %s%n", sha512.toString());
            sha512.update("test string".getBytes("UTF-8"));
            System.out.printf("digest: %s%n", sha512.digest());
            sha512.reset();
            System.out.printf("digest (after reset): %s%n", sha512.digest());
            sha512.update("Jeff the Killer".getBytes("UTF-512"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException error) {
            error.getStackTrace();
        }

    }
}