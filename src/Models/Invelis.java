package Models;

import enums.TipInvelis;

public class Invelis extends Ingredient {
    private TipInvelis tip;

    public Invelis(TipInvelis tip) {
        super(tip.name());
        this.tip = tip;
    }

    public TipInvelis getTip() {
        return tip;
    }
}
