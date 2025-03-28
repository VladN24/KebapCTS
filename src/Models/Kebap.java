package Models;

import java.util.List;

public class Kebap {
    private Proteina proteina;
    private Carbohidrat carbohidrat;
    private List<Sos> sosuri;
    private Muratura muratura;
    private Invelis invelis;
    private Fibre fibre;
    private Healthy healthy;

    // Constructor privat pentru a fi accesat prin Builder
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

    // Metoda pentru a vizualiza ingredientele kebapului
    public void displayIngredients() {
        System.out.println("Proteina: " + proteina.getNume());
        System.out.println("Carbohidrat: " + carbohidrat.getNume());
        for (Sos sos : sosuri) {
            System.out.println("Sos: " + sos.getNume() + " (Valabilitate: " + sos.getOreValabilitate() + " ore)");
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
}
