package Models;

import enums.TipSos;

public class Sos extends Ingredient {
    private TipSos tip;
    private Integer oreValabilitate;

    public Sos(TipSos tip, Integer oreValabilitate) {
        super(tip.name());
        this.tip = tip;
        this.oreValabilitate = oreValabilitate;
    }

    public TipSos getTip() {
        return tip;
    }

    public Integer getOreValabilitate() {
        return oreValabilitate;
    }
}
