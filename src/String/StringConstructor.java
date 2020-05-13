package String;

import java.lang.String;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;

//Java.lang.String
@RunWith(JUnitPlatform.class)
//@SelectPackages({DefaultConstructorTest.class, ConstructorTest1.class})
@SuiteDisplayName("String Unit Tests")
public class StringConstructor {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DefaultConstructorTest.class, ConstructorTest1.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
        }

        System.out.println(result.wasSuccessful());
    }
}

@DisplayName("Testing constructor String()")
@Tag("Constructor")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnitPlatform.class)
class DefaultConstructorTest {
    String str;

    @BeforeEach
    public void setUp() {
        str = new String();

    }

    @Test
    public void test() {
        assertEquals(str, "");
        assertEquals(str.length(), 0);
    }

    @AfterEach
    public void tearDown() { str = ""; }

}


@DisplayName("Testing constructor String(byte[] bytes)")
@Tag("Constructor")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnitPlatform.class)
class ConstructorTest1 {
    String str;

    @BeforeEach
    public void setUp() {
        byte[] arg = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
        str = new String(arg);
    }

    @Test
    public void test() {
        assertEquals(str, "Hellow, World!");
        assertEquals(str.length(), arg.length);
    }

    @AfterEach
    public void tearDown() { str = ""; }

}



