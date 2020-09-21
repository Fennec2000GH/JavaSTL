
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// "C:\Program Files\Java\jdk-14\lib\*"
//public class RegexExamples {
//    public static void main(String[] args) {
//        RegexTests rt = new RegexTests();
//        rt.sample();
//    }
//
//}


class RegexExamples {

    public static void main(String[] args) {

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This is a parameterized test for the {@link Regex#EMAIL} regex. The
     * {@link ParameterizedTest} annotation defines this method as a
     * parameterized test, and {@link MethodSource} tells JUnit to look for the
     * static method {@link #testEmailRegexExtra()}.
     * <p>
     * For personal preference, I include a test name as the first parameter
     * which describes what that test should be testing - this is visible in
     * IntelliJ when running the tests.
     */

    @ParameterizedTest
    @MethodSource
    public void testEmailRegex(String test, String input, boolean success) {
        test(input, Regex.EMAIL, success);
    }

    /**
     * This is the factory method providing test cases for the parameterized
     * test above - note that it is static, takes no arguments, and has the same
     * name as the test. The {@link Arguments} object contains the arguments for
     * each test to be passed to the function above
     */
    public static Stream<Arguments> testEmailRegex() {
        return Stream.of(
                Arguments.of("Uppercase single letter on both sides of @", "A@A.com", true),
                Arguments.of("Lowercase single letter on both sides of @", "a@a.com", true),
                Arguments.of("Period immediately after @", "username@.net", true),
                Arguments.of("Digits only as username and domain", "123@456.net", true),
                Arguments.of("Alphanumeric custom", "Aa0@Zz9.com", true),
                Arguments.of("Symbols only", "._+@-.com", true),
                Arguments.of("Period only before domain type", ".@.net", true),
                Arguments.of("Mixture of alphanumeric and symbols", ".@A-Za-z0-9-.gov", true),
                Arguments.of("Alphanumeric", "thelegend27@gmail.com", true),
                Arguments.of("UF Domain", "otherdomain@ufl.edu", true),
                Arguments.of("Symbols", "symbols#$%@gmail.com", true),
                Arguments.of("UF Domain", "otherdomain@ufl.edu", true),
                Arguments.of("Missing Domain Dot", "missingdot@gmailcom", false),
                Arguments.of("Empty", "", false),
                Arguments.of("Space", " ", false),
                Arguments.of("Missing @ symbol", "noAtSymbol.edu", false),
                Arguments.of("No username", "@noFront.com", false),
                Arguments.of("Missing period", "username@noPeriodDotCom", false),
                Arguments.of("Domain type is digit", "AZaz09@yahoo.1", false),
                Arguments.of("Missing domain type", "AZaz09@skynet.", false),
                Arguments.of("Domain type too long", "AZaz09@gmail.DomainTypeTooLong", false),
                Arguments.of("Symbols", "symbols#$%@gmail.com", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testFileNamesRegex(String test, String input, boolean success) {
        //this one is different as we're also testing the file name capture
        Matcher matcher = test(input, Regex.FILE_NAMES, success);
        if (success) {
            Assertions.assertEquals(input.substring(0, input.indexOf(".")), matcher.group("name"));
        }
    }

    public static Stream<Arguments> testFileNamesRegex() {
        return Stream.of(
                Arguments.of("Space as base name", " .java", true),
                Arguments.of("_ as base name", "_.java", true),
                Arguments.of("Empty names in between base name and extension", "base......java", true),
                Arguments.of("Symbols only", "!@#$%^&()[]{}.java", true),
                Arguments.of("Uppercase letters only", "AZ.java", true),
                Arguments.of("Lowercase letters only", "az.java", true),
                Arguments.of("Digits only", "09.java", true),
                Arguments.of("Alphanumeric only", "AZaz09.java", true),
                Arguments.of("Multiple single letter names", "a.b.c.java", true),
                Arguments.of("Alphanumeric but only lowercase only", "letters.and.digits.123.java", true),
                Arguments.of("Space as base name", " .class", true),
                Arguments.of("_ as base name", "_.class", true),
                Arguments.of("Empty names in between base name and extension", "base......class", true),
                Arguments.of("Symbols only", "!@#$%^&()[]{}.class", true),
                Arguments.of("Uppercase letters only", "AZ.class", true),
                Arguments.of("Lowercase letters only", "az.class", true),
                Arguments.of("Digits only", "09.class", true),
                Arguments.of("Alphanumeric only", "AZaz09.class", true),
                Arguments.of("Multiple single letter names", "a.b.c.class", true),
                Arguments.of("Alphanumeric but only lowercase only", "letters.and.digits.123.java", true),
                Arguments.of("Java File", "Regex.tar.java", true),
                Arguments.of("Java Class", "RegexTests.class", true),
                Arguments.of("Empty", "", false),
                Arguments.of("Space", " ", false),
                Arguments.of("Period only", ".", false),
                Arguments.of("Ends with period", "ends.with.period.", false),
                Arguments.of("Wrong extension", "filename.wrongExtension", false),
                Arguments.of("Wrong extension (.gz)", "base.tar.gz", false),
                Arguments.of("Only uppercase letters in extension", "CAPS.JAVA", false),
                Arguments.of("Only uppercase letters in extension", "CAPS.CLASS", false),
                Arguments.of("Capitalized extension", "TitleCase.Java", false),
                Arguments.of("Capitalized extension", "TitleCase.Class", false),
                Arguments.of("Randomized capitalization", "RaNdoMcapITaLizaTIoN.JavA", false),
                Arguments.of("Randomized capitalization", "RaNdoMcapITaLizaTIoN.ClaSS", false),
                Arguments.of("Using illegal characters", "IllegalCharacters.\\/:*?\"<>|.java", false),
                Arguments.of("Using illegal characters", "IllegalCharacters.\\/:*?\"<>|.class", false),
                Arguments.of("Directory", "directory", false),
                Arguments.of("Python File", "scrippy.py", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testEvenStringsRegex(String test, String input, boolean success) { test(input, Regex.EVEN_STRINGS, success); }

    public static Stream<Arguments> testEvenStringsRegex() {
        return Stream.of(
                Arguments.of("10 characters", "0123456789", true),
                Arguments.of("18 characters", "ateteen characters", true),
                Arguments.of("14 characters", "abcdefghijklmn", true),
                Arguments.of("16 characters", ",./?'[]!@#$%^&*+", true),
                Arguments.of("20 characters", "20 .................", true),
                Arguments.of("16 characters", "16 qwertyuiopasd", true),
                Arguments.of("14 characters", "14 ABCDEFGHIJK", true),
                Arguments.of("14 Characters", "thishas14chars", true),
                Arguments.of("10 Characters", "i<3pancakes!", true),
                Arguments.of("0 characters", "", false),
                Arguments.of("1  character", " ", false),
                Arguments.of("11 characters", "01234567890", false),
                Arguments.of("4 characters", "four", false),
                Arguments.of("15 characters", "15 qwertyuiopas", false),
                Arguments.of("45 characters", "This line is way too long past 20 characters.", false),
                Arguments.of("6 characters", "6chars", false),
                Arguments.of("13 characters", "i<3pancakes!!", false),
                Arguments.of("6 Characters", "6chars", false),
                Arguments.of("15 Characters", "i<3pancakes!!", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testIntegerListRegex(String test, String input, boolean success) { test(input, Regex.INTEGER_LIST, success); }

    public static Stream<Arguments> testIntegerListRegex() {
        return Stream.of(
                Arguments.of("Empty List", "[]", true),
                Arguments.of("Single Element", "[1]", true),
                Arguments.of("Two elements with space after comma", "[1, 2]", true),
                Arguments.of("Two elements without space after comma", "[1,2]", true),
                Arguments.of("Multiple Elements", "[1,2,3]", true),
                Arguments.of("Multiple elements each with space after comma", "[1, 2, 3, 4, 5, 6, 7, 8, 9]", true),
                Arguments.of("Multiple elements each without space after comma", "[1,2,3,4,5,6,7,8,9]", true),
                Arguments.of("Multiple elements each alternating with space after comma", "[1, 2,3, 4,5, 6,7, 8,9]", true),
                Arguments.of("Positive integers with at least 2 digits", "[12, 345, 6789]", true),
                Arguments.of("Ones only", "[1,1,1,1,1,1,1,1,1,1,1,1,1]", true),
                Arguments.of("Single very large element", "[123456789]", true),
                Arguments.of("Empty", "", false),
                Arguments.of("Space", " ", false),
                Arguments.of("Single negative element", "-1", false),
                Arguments.of("1", false),
                Arguments.of("Negative elements missing brackets", "-1 -2 -3", false),
                Arguments.of("Missing left bracket", "1, 2, 3]", false),
                Arguments.of("Missing right bracket", "[1, 2, 3", false),
                Arguments.of("Negative elements missing commas", "[-1 -2 -3]", false),
                Arguments.of("Single zero", "[0]", false),
                Arguments.of("Multiple elements including zero", "[0, 1, 2, 3]", false),
                Arguments.of("Single element with trailing comma", "[1,]", false),
                Arguments.of("More thna one space after comma", "[1,     2]", false),
                Arguments.of("Comma before any element", "[,1,2]", false),
                Arguments.of("Letters are elements", "[contains, letters]", false),
                Arguments.of("Illegal symbols are elements", "[1, 2, 3, !, @, #, $, %, ^, &, *, (, ), -, +]", false),
                Arguments.of("Missing Brackets", "1,2,3", false),
                Arguments.of("Missing Commas", "[1 2 3]", false),
                Arguments.of("Trailing Comma", "[1,2,3,]", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testIdentifierRegex(String test, String input, boolean success) {
        test(input, Regex.IDENTIFIER, success);
    }

    public static Stream<Arguments> testIdentifiersRegex() {
        return Stream.of(
                Arguments.of("Single symbol", "+", true),
                Arguments.of("Multiple symbols", "-*/.:!?<>=", true),
                Arguments.of("Digits with symbol", "+0123456789", true),
                Arguments.of("Symbols and letters", "Symbols? Mixed. With: Letters+", true),
                Arguments.of("Letters and digits", "abc123", true),
                Arguments.of("Single uppercase letter", "A", true),
                Arguments.of("Single lowercase letter", "a", true),
                Arguments.of("Camelcase", "getName", true),
                Arguments.of("Letters and symbols", "is-empty?", true),
                Arguments.of("Some symbols", "<=>", true),
                Arguments.of("Empty space", "", false),
                Arguments.of("Space", " ", false),
                Arguments.of("Single period", ".", false),
                Arguments.of("Starts with period", ".starts with period", false),
                Arguments.of("Illegal symbols", "illegal symbols: @#$%^&()[]{},", false),
                Arguments.of("Contains emoji", "⚠ warning: no emoji allowed", false),
                Arguments.of("Starts with digits", "42=life", false),
                Arguments.of("Contains illegal commas", "why,are,there,commas,", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testNumbers(String test, String input, boolean success) {
        test(input, Regex.NUMBER, success);
    }

    public static Stream<Arguments> testNumbersRegex() {
        return Stream.of(
                Arguments.of("Positive zero", "+0", true),
                Arguments.of("Negative zero", "-0", true),
                Arguments.of("Decimal zero", "0.0000", true),
                Arguments.of("Positive decimal zero", "+0.0000", true),
                Arguments.of("Negative decimal zero", "-0.0000", true),
                Arguments.of("Positive integer", "1", true),
                Arguments.of("Positive float", "+1.0", true),
                Arguments.of("Large positive integer", "1234567890", true),
                Arguments.of("Large positive float", "12345.67890", true),
                Arguments.of("Negative integer", "-1", true),
                Arguments.of("Negative float", "-1.0", true),
                Arguments.of("Leading zeros float", "007.000", true),
                Arguments.of("Empty", "", false),
                Arguments.of("Space", " ", false),
                Arguments.of("Single minus sign", "-", false),
                Arguments.of("Single plus sign", "+", false),
                Arguments.of("Minus plus", "-+", false),
                Arguments.of("Plus minus", "+-", false),
                Arguments.of("Single period", ".", false),
                Arguments.of("Double negative integer", "--1", false),
                Arguments.of("Double positive integer", "++1", false),
                Arguments.of("Multiple periods", "1..0", false),
                Arguments.of("Multiple periods with digits in between", "123.456.789", false),
                Arguments.of("Missing digit left of decimal point", ".123", false),
                Arguments.of("Positive float with missing digit left of decimal point", "+.123", false),
                Arguments.of("Negative float with missing digit left of decimal point", "-.123", false),
                Arguments.of("Float missing digits right of decimal point", "1.", false),
                Arguments.of("Positive float missing digits right of decimal point", "+1.", false),
                Arguments.of("Negative float missing digits right of decimal point", "-1.", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testStrings(String test, String input, boolean success) {
        test(input, Regex.STRING, success);
    }

    public static Stream<Arguments> testStringsRegex() {
        return Stream.of(
                Arguments.of("Empty quotes", "\"\"", true),
                Arguments.of("Space with quotes", "\" \"", true),
                Arguments.of("All possible escape chars within quotes", "\"\\b\\n\\r\\t\\'\\\\\"\"", true),
                Arguments.of("Digits only within quotes", "\"1234567890\"", true),
                Arguments.of("Symbols within quotes", "\"!@#$%^&*()_+[]\"", true),
                Arguments.of("Letters only within quotes", "\"abc\"", true),
                Arguments.of("Readable words within quotes", "\"Hello,\\nWorld!\"", true),
                Arguments.of("No right quotation mark", "\"missing closing quotation mark", false),
                Arguments.of("No left quotation mark", "missing opening quotation mark\"", false),
                Arguments.of("Only invalid escape chars", "\"\\a\\b\\c\\d\\e\\f\\g\"", false),
                Arguments.of("Invalid escape chars using digits only", "\"\\1\\2\\3\\4\\5\\6\\7\\8\\9\\0\"", false),
                Arguments.of("Invalid escape chars using symbols only", "\"Bad symbols: \\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\"", false),
                Arguments.of("Missing both quotation marks", "unterminated", false),
                Arguments.of("Invalid escape char with letter", "invalid\\escape", false)
        );
    }

    /**
     * Asserts that the input matches the given pattern and returns the matcher
     * for additional assertions.
     */
    public Matcher test(String input, Pattern pattern, boolean success) {
        Matcher matcher = pattern.matcher(input);
        Assertions.assertSame(success, matcher.matches());
        return matcher;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

class Regex {
    public static final Pattern
            EMAIL = Pattern.compile("[A-Za-z0-9._-]+@[A-Za-z0-9-]*\\.[a-z]{2,3}"),
            FILE_NAMES = Pattern.compile("(?<name>[^.\\/:*?\"<>|]+)([^\\/:*?\"<>|])*\\.(java|class)"),
            EVEN_STRINGS = Pattern.compile("(..){5,10}"),
            INTEGER_LIST = Pattern.compile("\\[([1-9]+,\\s?)*[1-9]+\\]|(\\[\\])"),
            IDENTIFIER = Pattern.compile("[-+*/:!?<>=A-Za-z_](\\w|[-+*/\\.:!?<>=])*"),
            NUMBER = Pattern.compile("[-+]?\\d+(\\.\\d+)?"),
            STRING = Pattern.compile("\"(\\\\([bnrt\'\"]|\\\\)|[^\\\\])*\"");
}

