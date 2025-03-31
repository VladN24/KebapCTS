package Models.Ingrediente;

public class SosFermentabil extends Sos {
    private int oreValabilitate;

    public SosFermentabil(String nume, int oreValabilitate) {
        super(nume);
        this.oreValabilitate = oreValabilitate;
    }

    public int getOreValabilitate() {
        return oreValabilitate;
    }

    @Override
    public String toString() {
        return getNume() + " (valabil " + oreValabilitate + "h)";
    }
}