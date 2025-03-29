package Models;

import Models.Ingrediente.*;

import java.util.List;
import java.util.stream.Collectors;

public class Kebap {
    private Proteina proteina;
    private Carbohidrat carbohidrat;
    private List<Sos> sosuri;
    private Muratura muratura;
    private Invelis invelis;
    private Fibre fibre;
    private Healthy healthy;

    public Kebap(Proteina proteina, Carbohidrat carbohidrat, List<Sos> sosuri, Muratura muratura,
                  Invelis invelis, Fibre fibre, Healthy healthy) {
        this.proteina = proteina;
        this.carbohidrat = carbohidrat;
        this.sosuri = sosuri;
        this.muratura = muratura;
        this.invelis = invelis;
        this.fibre = fibre;
        this.healthy = healthy;
    }

    public void afiseazaIngrediente() {
        System.out.println("Proteina: " + proteina.getNume());
        System.out.println("Carbohidrat: " + carbohidrat.getNume());
        for (Sos sos : sosuri) {
            System.out.println("Sos: " + sos.getNume());
            if (sos instanceof SosFermentabil fermentabil) {
                System.out.println(sos.getNume() + " e valabil " + fermentabil.getOreValabilitate() + "h");
            }
        }
        if (muratura != null) {
            System.out.println("Muratura: " + muratura.getNume());
        }
        if (invelis != null) {
            System.out.println("Invelis: " + invelis.getNume());
        }
        if (fibre != null) {
            System.out.println("Fibre: " + fibre.getNume());
        }
        if (healthy != null) {
            System.out.println("Healthy: " + healthy.getNume());
        }
    }

    @Override
    public String toString() {
        return "Kebap{" +
                "proteina=" + proteina.getNume() +
                ", carbohidrat=" + carbohidrat.getNume() +
                ", sosuri=" + sosuri +
                ", invelis=" + (invelis != null ? invelis.getNume() : "N/A") +
                ", fibra=" + (fibre != null ? fibre.getNume() : "N/A") +
                ", healthy=" + (healthy != null ? healthy.getNume() : "N/A") +
                ", sosuri=" + getNumeSosuri() +
                '}';
    }

    private String getNumeSosuri() {
    return sosuri.stream()
            .map(Sos::getNume)
            .collect(Collectors.joining(", "));
}

    public Proteina getProteina() {
        return proteina;
    }


    public Carbohidrat getCarbohidrat() {
        return carbohidrat;
    }


    public List<Sos> getSosuri() {
        return sosuri;
    }


    public Muratura getMuratura() {
        return muratura;
    }


    public Invelis getInvelis() {
        return invelis;
    }


    public Fibre getFibre() {
        return fibre;
    }


    public Healthy getHealthy() {
        return healthy;
    }


    public void setProteina(Proteina proteina) {
        this.proteina = proteina;
    }

    public void setCarbohidrat(Carbohidrat carbohidrat) {
        this.carbohidrat = carbohidrat;
    }

    public void setSosuri(List<Sos> sosuri) {
        this.sosuri = sosuri;
    }

    public void setMuratura(Muratura muratura) {
        this.muratura = muratura;
    }

    public void setInvelis(Invelis invelis) {
        this.invelis = invelis;
    }

    public void setFibre(Fibre fibre) {
        this.fibre = fibre;
    }

    public void setHealthy(Healthy healthy) {
        this.healthy = healthy;
    }
}
