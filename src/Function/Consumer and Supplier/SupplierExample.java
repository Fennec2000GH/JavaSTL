
import java.util.function.Supplier;
import java.util.Random;
import java.util.stream.Stream;

class Person {
    // MEMBER VARIABLES
    private String firstName;
    private String lastName;
    private double height;
    private double weight;

    // MEMBER FUNCTIONS
    // CONSTRUCTORS
    public Person(String firstName, String lastName, double height, double weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }

    // ACCESSORS
    public String getFullName() { return this.firstName + " " + this.lastName; }
    public double getHeight() { return this.height; }
    public double getWeight() { return this.weight; }

    // MUTATORS
    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setHeight(double height) {
        try { if (height < 0) { throw new IllegalArgumentException("Height must be positive."); } }
        catch (IllegalArgumentException error) { error.printStackTrace(); }
        this.height = height;
    }

    public void setWeight(double weight) {
        try { if (weight < 0) { throw new IllegalArgumentException("Weight must be positive."); } }
        catch (IllegalArgumentException error) { error.printStackTrace(); }
        this.weight = weight;
    }
}

public class SupplierExample {
    public static void main(String[] args) {
        // get
        String[] firstNameArr = {"Alfred", "Benny", "Catherine", "Denise", "Ethan", "Freddy"};
        String[] lastNameArr = {"Garrett", "Helena", "Isaac", "Jack", "Katty", "Leo"};
        Supplier<Person> supplier = () -> {
            Random rand = new Random(System.currentTimeMillis());
            String firstName = firstNameArr[rand.nextInt(firstNameArr.length)];
            String lastName = lastNameArr[rand.nextInt(lastNameArr.length)];
            double height = Stream.of(rand.nextDouble()).mapToDouble(n -> n / Math.pow(10, Math.log10(n))).findFirst().getAsDouble();
            double weight = rand.nextDouble();
            return new Person(firstName, lastName, height, weight);
        };

        Person tempPerson;
        for (int i = 0; i < 10; i++) {
            tempPerson = supplier.get();
            System.out.println(tempPerson.getFullName());
        }
    }
}

