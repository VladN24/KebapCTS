package Models;

import Models.Ingrediente.*;
import enums.*;
import exceptions.LipsaCarbohidratException;
import exceptions.LispaProteinaException;
import exceptions.LimitaSosDepasitaException;
import Interfaces.IBuilder;

import java.util.ArrayList;
import java.util.List;

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

    private void valideazaProteina() {
        if (kebap.getProteina() == null) {
            throw new LispaProteinaException("Kebap-ul trebuie să aibă o sursă de proteină.");
        }
    }

    private void valideazaCarbohidrat() {
        if (kebap.getCarbohidrat() == null) {
            throw new LipsaCarbohidratException("Kebap-ul trebuie să aibă o sursă de carbohidrați.");
        }
    }

    private void valideazaCompozitie() {
        valideazaProteina();
        valideazaCarbohidrat();
    }

    public Kebap build() {
        valideazaCompozitie();
        kebap.setSosuri(sosuriTemp);
        return kebap;
    }

}