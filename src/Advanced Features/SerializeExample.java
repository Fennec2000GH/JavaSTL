
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

enum COLOR {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}
class Flower implements Serializable {
    // MEMBER VARIABLES
    private String species;
    private COLOR color;
    private double height;
    private double petal_width;
    private double petal_length;

    // MEMBER FUNCTIONS
    // CONSTRUCTORS
    public Flower(String species, COLOR color, double height, double petal_width, double petal_length) {
        this.species = species;
        this.color = color;
        this.height = height;
        this.petal_width = petal_width;
        this.petal_length = petal_length;
    }

    // GETTERS
    public String getSpecies() { return this.species; }
    public COLOR getColor() { return this.color; }
    public double getHeight() { return this.height; }
    public double getPetal_width() { return this.petal_width; }
    public double getPetal_length() { return this.petal_length; }

    // SETTERS
    public void setSpecies(String species) { this.species = species; }
    public void setColor(COLOR color) { this.color = color; }
    public void setHeight(double height) { this.height = height; }
    public void setPetal_width(double petal_width) { this.petal_width = petal_width; }
    public void setPetal_length(double petal_length) { this.petal_length = petal_length; }

}

enum GROWTH_STAGE {SEED, GERMINATE, BUD, BLOSSOM, DEAD}
class FlowerSapling extends Flower {
    // MEMBER VARIABLES
    private int daysSinceGermination;
    private GROWTH_STAGE growthStage;

    // MEMBER FUNCTIONS
    // CONSTRUCTORS
    public FlowerSapling(int daysSinceGermination, String species, COLOR color) {
        super(species, color, 0, 0, 0);
        this.daysSinceGermination = daysSinceGermination;
        this.setGrowthStage();
    }

    // ACCESSORS
    public int getDaysSinceGermination() { return this.daysSinceGermination; }
    public GROWTH_STAGE getGrowthStage() { return this.growthStage; }

    // MUTATORS
    public void setDaysSinceGermination(int daysSinceGermination) {
        // Error checking for valid arguments
        try {
            if (daysSinceGermination < 0) {
                throw new IllegalArgumentException("argument must be nonnegative");
            } if (daysSinceGermination < this.daysSinceGermination) {
                throw new IllegalArgumentException("argument must be greater than current argument value");
            }
        } catch (IllegalArgumentException error) { error.printStackTrace(); }
        this.daysSinceGermination = daysSinceGermination;
        this.setGrowthStage();
    }

    private void setGrowthStage() {
        int germination_age = this.daysSinceGermination;
        if (germination_age == 0) { this.growthStage = GROWTH_STAGE.SEED; }
        else if (germination_age <= 12) { this.growthStage = GROWTH_STAGE.GERMINATE; }
        else if (germination_age <= 20) { this.growthStage = GROWTH_STAGE.BUD; }
        else if (germination_age <= 32) { this.growthStage = GROWTH_STAGE.BLOSSOM; }
        else { this.growthStage = GROWTH_STAGE.DEAD; }
    }
}

class Bouquet implements Serializable {
    // MEMBER VARIABLES
    private String from;
    private String to;
    private HashSet<Flower> bouquet;

    // MEMBER VARIABLES
    // CONSTRUCTORS
    public Bouquet(String from, String to, Flower... flowers) {
        this.from = from;
        this.to = to;
        this.bouquet = new HashSet<Flower>(Arrays.asList(flowers));
    }

    // ACCESSORS
    public String getFrom() { return this.from; }
    public String getTo() { return this.to; }
    public int getSize() { return this.bouquet.size(); }
    public List<Flower> toList() { return List.copyOf(this.bouquet); }
}

public class SerializeExample {
    public static void main(String [] args) {
        Flower tulip = new Flower("tulip", COLOR.VIOLET, 17.8, 2.3, 6.6);

        // Serializing
        try {
            FileOutputStream fileOut = new FileOutputStream("./tulip.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tulip);
            out.flush();
            out.close();
            fileOut.close();
            System.out.println("Serializing Flower object");
        } catch (IOException error) { error.printStackTrace(); }

        // De-serializing
        try {
            FileInputStream fileIn = new FileInputStream("./tulip.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Flower tulip_new = (Flower) in.readObject();
            in.close();
            System.out.println("De-serializing Flower object.");
            System.out.println("Flower Attributes:");
            System.out.println("-----------------\n");
            System.out.printf("Species: %s%n", tulip_new.getSpecies());
            System.out.printf("Color: %s%n", tulip_new.getColor().toString());
            System.out.printf("Height: %f%n", tulip_new.getHeight());
            System.out.printf("Petal Width: %f%n", tulip_new.getPetal_width());
            System.out.printf("Petal Length: %f%n", tulip_new.getPetal_length());
        } catch (IOException | ClassNotFoundException error) { error.printStackTrace(); }

        // Serializing object inheriting from Serializable class
        try {
            System.out.println("\nSerializing FlowerSapling object");
            FileOutputStream fileOut = new FileOutputStream("./sapling.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            FlowerSapling flowerSapling = new FlowerSapling(20, "lotus", COLOR.GREEN);
            out.writeObject(flowerSapling);
            out.flush();
            out.close();
        } catch (IOException error) { error.printStackTrace(); }

        // De-serializing
        try {
            System.out.println("\nDe-serializing FlowerSapling object");
            FileInputStream fileIn = new FileInputStream("./sapling.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            FlowerSapling flowerSapling_new = (FlowerSapling) in.readObject();
            in.close();

            System.out.println("\nFlowerSapling Attributes:");
            System.out.println("---------------------------");
            System.out.printf("Days Since Germination: %d%n", flowerSapling_new.getDaysSinceGermination());
            System.out.printf("Growth Stage: %s%n", flowerSapling_new.getGrowthStage().toString());
        } catch (IOException | ClassNotFoundException error) { error.printStackTrace(); }

        // Serializing with another custom class object as member variable
        try {
            Bouquet bouquet = new Bouquet("Heimlicher Verehrer",
                                          "Zwischen_MeinenGesicht",
                                          new Flower("tulip", COLOR.VIOLET, 17.8, 2.3, 6.6),
                                          new Flower("rose", COLOR.RED, 14.6, 3.4, 6.8),
                                          new Flower("dandelion", COLOR.YELLOW, 12.6, 1.6, 3.6),
                                          new Flower("oleandor", COLOR.BLUE, 14.7, 2.3, 8.8));
            FileOutputStream fileOut = new FileOutputStream("./bouquet.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(bouquet);
            out.flush();
            out.close();
        } catch (IOException error) { error.printStackTrace(); }

        // De-serializing
        try {
            System.out.println("\nSerializing Bouquet object");
            FileInputStream fileIn = new FileInputStream("./bouquet.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Bouquet bouquet_new = (Bouquet) in.readObject();
            in.close();

            System.out.println("\nDe-serializing Bouquet object");
            System.out.println("Bouquet Species:");
            System.out.print("-------------------\n");
            bouquet_new.toList().stream().map(Flower::getSpecies).forEach(System.out::println);
        } catch (IOException | ClassNotFoundException error) { error.printStackTrace(); }

    }
}

