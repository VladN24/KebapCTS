package Models;

import enums.TipProteina;

public class Proteina extends Ingredient {
    private TipProteina tip;

    public Proteina(TipProteina tip) {
        super(tip.name());
        this.tip = tip;
    }

    public TipProteina getTip() {
        return tip;
    }
}
