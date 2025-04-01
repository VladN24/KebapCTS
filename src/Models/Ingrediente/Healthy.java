package Models.Ingrediente;

import enums.TipHealthy;

public class Healthy extends Ingredient {
    private TipHealthy tip;

    public Healthy(TipHealthy tip) {
        super(tip.name());
        this.tip = tip;
    }

}
