package Models.Ingrediente;

import enums.TipSos;

public class SosFermentabil extends Sos {
    private int oreValabilitate;

    public SosFermentabil(TipSos tip, Integer oreValabilitate) {
        super(tip);
        this.oreValabilitate = oreValabilitate;
    }

    public int getOreValabilitate() {
        return oreValabilitate;
    }
}
