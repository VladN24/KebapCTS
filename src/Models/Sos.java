package Models;

import enums.TipSos;

public class Sos extends Ingredient {
    private TipSos tip;

    public Sos(TipSos tip) {
        super(tip.name());
        this.tip = tip;
    }

    public TipSos getTip() {
        return tip;
    }

    //public Integer getOreValabilitate() {
    //return oreValabilitate;
    //}
}
