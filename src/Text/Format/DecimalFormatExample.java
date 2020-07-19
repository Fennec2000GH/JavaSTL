
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Currency;

public class DecimalFormatExample {
    public static void main(String[] args) {
        double test;
        // Constructors
        {
            System.out.println("Constructors");
            for (int i = 0; i < 20; i++) { System.out.print('-'); }
            System.out.println();
            test = 120.420;
            DecimalFormat df = new DecimalFormat();
            df.format(test);
            System.out.println(df.format(test));
            df = new DecimalFormat("0.#");
            System.out.println(df.format(test));
            df = new DecimalFormat("###.###", DecimalFormatSymbols.getInstance(Locale.GERMANY));
            System.out.println(df.format(test));
        }

        // Formatting from patterns
        {
            System.out.println("Formatting from patterns");
            for (int i = 0; i < 20; i++) { System.out.print('-'); }
            System.out.println();
            test = 123456.789;
            DecimalFormat df = new DecimalFormat("###.###");
            System.out.println(df.format(test));
            df.applyPattern("###, ###.#");
            System.out.println(df.format(test));
            df.applyPattern("E");
            System.out.println(df.format(test));
            df.applyPattern("00000000000");
            System.out.println(df.format(test));
        }

        // Parsing
        {
            System.out.println("Parsing");
            for (int i = 0; i < 20; i++) { System.out.print('-'); }
            System.out.println();
            String test_str = "144.256";
            DecimalFormat df = new DecimalFormat("###.000");
            double d = df.parse(test_str, new ParsePosition(0)).doubleValue();
            System.out.printf("d = %f%n", d);
            System.out.printf("isParseBigDecimal = %s%n", df.isParseBigDecimal());
        }

        // Other functions
        {
            System.out.println("Other functions");
            for (int i = 0; i < 20; i++) { System.out.print('-'); }
            System.out.println();
            test = 999.1;
            DecimalFormat df = new DecimalFormat("000.000");
            System.out.printf("Currency = %s%n", df.getCurrency());
            System.out.printf("DecimalFormat Symbols = %s%n", df.getDecimalFormatSymbols());
            System.out.printf("Grouping size = %d%n", df.getGroupingSize());
            System.out.printf("Max Fraction Digits = %d%n", df.getMaximumFractionDigits());
            System.out.printf("Max Integer Digits = %d%n", df.getMaximumIntegerDigits());
            System.out.printf("Min Fraction Digits = %d%n", df.getMinimumFractionDigits());
            System.out.printf("Min Integer Digits = %d%n", df.getMinimumIntegerDigits());
            System.out.printf("Multiplier = %d%n", df.getMultiplier());
            System.out.printf("Negative prefix = %s%n", df.getNegativePrefix());
            System.out.printf("Negative suffix = %s%n", df.getNegativeSuffix());
            System.out.printf("Positive prefix = %s%n", df.getPositivePrefix());
            System.out.printf("Positive suffix = %s%n", df.getPositiveSuffix());
            System.out.printf("Rounding mode = %s%n", df.getRoundingMode());
        }

    }
}
