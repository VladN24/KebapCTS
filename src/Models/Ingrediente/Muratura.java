package Models.Ingrediente;

import enums.TipMuratura;

public class Muratura extends Ingredient {
    private TipMuratura tip;

    public Muratura(TipMuratura tip) {
        super(tip.name());
        this.tip = tip;
    }

    public TipMuratura getTip() {
        return tip;
    }
}
