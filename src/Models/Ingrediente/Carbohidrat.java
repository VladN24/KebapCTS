package Models.Ingrediente;

import enums.TipCarbohidrat;

public class Carbohidrat extends Ingredient {
    private TipCarbohidrat tip;

    public Carbohidrat(TipCarbohidrat tip) {
        super(tip.name());
        this.tip = tip;
    }

}
