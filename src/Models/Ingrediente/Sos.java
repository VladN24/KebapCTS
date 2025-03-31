package Models.Ingrediente;

public class Sos extends Ingredient {
    public Sos(String nume) {
        super(nume.toLowerCase());
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return getNume();
    }
}
