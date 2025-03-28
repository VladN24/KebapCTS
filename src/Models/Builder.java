package Models;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private Proteina proteina;
    private Carbohidrat carbohidrat;
    private List<Sos> sosuri = new ArrayList<>();
    private Muratura muratura;
    private Invelis invelis;
    private Fibre fibre;
    private Healthy healthy;

    public Builder setProteina(Proteina proteina) {
        if (this.proteina != null) {
            throw new IllegalArgumentException("Poti adauga doar un singur tip de proteina.");
        }
        this.proteina = proteina;
        return this;
    }

    public Builder setCarbohidrat(Carbohidrat carbohidrat) {
        if (this.carbohidrat != null) {
            throw new IllegalArgumentException("Poti adauga doar un singur tip de carbohidrat.");
        }
        this.carbohidrat = carbohidrat;
        return this;
    }

    public Builder addSos(Sos sos) {
        if (sosuri.size() >= 3) {
            throw new IllegalArgumentException("Poti adauga maxim 3 sosuri.");
        }
        sosuri.add(sos);
        return this;
    }

    public Builder setMuratura(Muratura muratura) {
        if (this.muratura != null) {
            throw new IllegalArgumentException("Poti adauga doar un singur tip de muratura.");
        }
        this.muratura = muratura;
        return this;
    }

    public Builder setInvelis(Invelis invelis) {
        this.invelis = invelis;
        return this;
    }

    public Builder setFibre(Fibre fibre) {
        if (this.fibre != null) {
            throw new IllegalArgumentException("Poti adauga doar un singur tip de fibre.");
        }
        this.fibre = fibre;
        return this;
    }

    public Builder setHealthy(Healthy healthy) {
        if (this.healthy != null) {
            throw new IllegalArgumentException("Poti adauga doar un singur tip de ingredient healthy.");
        }
        this.healthy = healthy;
        return this;
    }

    public Kebap build() {
        if (proteina == null || carbohidrat == null) {
            throw new IllegalStateException("Proteina si Carbohidratul sunt obligatorii.");
        }
        return new Kebap(proteina, carbohidrat, sosuri, muratura, invelis, fibre, healthy);
    }
}
