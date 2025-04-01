package Models;

import Models.Ingrediente.*;
import enums.*;
import interfaces.IBuilder;

import java.util.ArrayList;
import java.util.List;

import static Models.Validator.valideazaAdaugareSos;
import static Models.Validator.valideazaKebapFinalizat;

public class KebapBuilder implements IBuilder {
    private Kebap kebap; //= new Kebap();
    private List<Sos> sosuriTemp = new ArrayList<>();

    public KebapBuilder() {
        this.kebap = new Kebap(null, null, new ArrayList<>(), null, null, null, null);
    }


    public KebapBuilder adaugaProteina(TipProteina tip) {
        this.kebap.setProteina(new Proteina(tip));
        return this;
    }

    public KebapBuilder adaugaCarbohidrat(TipCarbohidrat tip) {
        this.kebap.setCarbohidrat(new Carbohidrat(tip));
        return this;
    }

    public KebapBuilder adaugaMuratura(TipMuratura tip) {
        this.kebap.setMuratura(new Muratura(tip));
        return this;
    }

    public KebapBuilder adaugaInvelis(TipInvelis tip) {
        this.kebap.setInvelis(new Invelis(tip));
        return this;
    }

    public KebapBuilder adaugaFibra(TipFibre tip) {
        this.kebap.setFibre(new Fibre(tip));
        return this;
    }

    public KebapBuilder adaugaHealthy(TipHealthy tip) {
        this.kebap.setHealthy(new Healthy(tip));
        return this;
    }

    public KebapBuilder adaugaSos(String nume) {
        valideazaAdaugareSos(sosuriTemp,kebap);
        sosuriTemp.add(new Sos(nume));
        return this;
    }

    public KebapBuilder adaugaSosFermentabil(String nume, int oreValabilitate) {
        valideazaAdaugareSos(sosuriTemp,kebap);
        sosuriTemp.add(new SosFermentabil(nume, oreValabilitate));
        return this;
    }



    public Kebap build() {
        valideazaKebapFinalizat(kebap);
        kebap.setSosuri(sosuriTemp);
        return kebap;
    }

    public int getNumarSosuri() {
        return sosuriTemp.size();
    }
}