package Models.Ingrediente;

import interfaces.ISos;

public class Sos extends Ingredient implements ISos {
    public Sos(String nume) {
        super(nume.toLowerCase());
    }

    public   boolean esteFermentabil(){
        return false;
    };

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return getNume();
    }
}
