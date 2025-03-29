package Models.Ingrediente;

import enums.TipFibre;

public class Fibre extends Ingredient {
    private TipFibre tip;

    public Fibre(TipFibre tip) {
        super(tip.name());
        this.tip = tip;
    }

    public TipFibre getTip() {
        return tip;
    }
}
