
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;

public class AttributedStringExample {
    public static void main(String[] args) {
        String test = "globe";
        AttributedString as = new AttributedString(test);
        as.addAttribute(TextAttribute.WIDTH, 10);
        System.out.println(test);
        System.out.println(as.toString());

    }
}
