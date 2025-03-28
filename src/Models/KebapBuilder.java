package Models;

import Models.Ingrediente.*;
import enums.*;
import exceptions.LispaProteinaException;
import exceptions.LimitaSosDepasitaException;
import interfaces.IBuilder;

import java.util.ArrayList;
import java.util.List;

public class KebapBuilder implements IBuilder {
    private Kebap kebap; //= new Kebap();
    private List<Sos> sosuriTemp = new ArrayList<>();

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

    public KebapBuilder adaugaSos(TipSos tip) {
        if (sosuriTemp.size() >= 3) {
            throw new LimitaSosDepasitaException("Nu poți adăuga mai mult de 3 sosuri.");
        }
        if (kebap.getProteina() == null) {
            throw new LispaProteinaException("Nu poți adăuga sosuri fără proteină.");
        }
        sosuriTemp.add(new Sos(tip));
        return this;
    }

    public KebapBuilder adaugaSosFermentabil(TipSos tip, int oreValabilitate) {
        if (sosuriTemp.size() >= 3) {
            throw new LimitaSosDepasitaException("Nu poți adăuga mai mult de 3 sosuri.");
        }
        if (kebap.getProteina() == null) {
            throw new LispaProteinaException("Nu poți adăuga sosuri fără proteină.");
        }
        sosuriTemp.add(new SosFermentabil(tip, oreValabilitate));
        return this;
    }

//    public Kebap build() {
//        if (kebap.getProteina() == null || kebap.getCarbohidrat() == null) {
//            throw new IllegalStateException("Kebap-ul trebuie să aibă proteină și carbohidrat.");
//        }
//        kebap.setSosuri(sosuriTemp);
//        return kebap;
//    }

    public Kebap build() {
        if (kebap.toString().contains("proteina=null") || kebap.toString().contains("carbohidrat=null")) {
            throw new IllegalStateException("Kebap-ul trebuie să aibă proteină și carbohidrat.");
        }
        kebap.setSosuri(sosuriTemp);
        return kebap;
    }
}