package Models;

public abstract class Ingredient {
    protected String nume;

    public Ingredient(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
}
