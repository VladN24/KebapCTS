package Models.Ingrediente;

import interfaces.ISos;

public class SosFermentabil extends Sos implements ISos {
    private int oreValabilitate;

   public boolean esteFermentabil(){
        return true;
    };

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