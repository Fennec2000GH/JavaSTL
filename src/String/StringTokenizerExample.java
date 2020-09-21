
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringTokenizerExample {
    public static void main(String[] args) {

        // Stores the tokens
        ArrayList<String> tokens = new ArrayList<>();

        // Default delimiter is  any of '\t\n\r\f'
        StringTokenizer stoken = new StringTokenizer("int number = 20;");
        while(stoken.hasMoreTokens()) { tokens.add(stoken.nextToken().toString()); }
        for (String token : tokens) { System.out.print(token + "\t"); }
        System.out.println();

        // Custom delimiter (in this case '.')
        stoken = new StringTokenizer("boolean.boolVariable.=.true;", ".");
        tokens.clear();
        while(stoken.hasMoreTokens()) { tokens.add(stoken.nextToken()); }
        for (String token : tokens) { System.out.print(token + "\t"); }
        System.out.println();

        // Also count delimiter as token
        stoken = new StringTokenizer("ArrayList<Double>@li@=@new@ArrayList<Double>();", "@", true);
        tokens.clear();
        while(stoken.hasMoreElements()) { tokens.add(stoken.nextElement().toString()); }
        for (String token : tokens) { System.out.print(token + "\t"); }
        System.out.println();

        // Other functions
        String str = "String text = \"This sentence has text inside.\"";
        stoken = new StringTokenizer(str);
        System.out.println(str);
        System.out.printf("Number of tokens: %d\n", stoken.countTokens());
        System.out.printf("First token delimited by 'e': %s", stoken.nextToken("e"));
    }
}

