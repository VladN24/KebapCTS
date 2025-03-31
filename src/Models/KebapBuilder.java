package Models;

import Models.Ingrediente.*;
import enums.*;
import exceptions.LipsaCarbohidratException;
import exceptions.LipsaProteinaException;
import exceptions.LimitaSosDepasitaException;
import interfaces.IBuilder;

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

    public KebapBuilder adaugaSos(String nume) {
        valideazaAdaugareSos();
        sosuriTemp.add(new Sos(nume));
        return this;
    }

    public KebapBuilder adaugaSosFermentabil(String nume, int oreValabilitate) {
        valideazaAdaugareSos();
        sosuriTemp.add(new SosFermentabil(nume, oreValabilitate));
        return this;
    }

    private void valideazaAdaugareSos() {
        if (sosuriTemp.size() >= 3) {
            throw new LimitaSosDepasitaException();
        }
        if (kebap.getProteina() == null) {
            throw new LipsaProteinaException("Nu poți adăuga sosuri fără proteină.");
        }
    }


    private void valideazaProteina() {
        if (kebap.getProteina() == null) {
            throw new LipsaProteinaException("Kebap-ul trebuie să aibă o sursă de proteină.");
        }
    }

    private void valideazaCarbohidrat() {
        if (kebap.getCarbohidrat() == null) {
            throw new LipsaCarbohidratException();
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

    public int getNumarSosuri() {
        return sosuriTemp.size();
    }
}