package interfaces;

import Models.Ingrediente.*;

import java.util.List;

public interface IKebap {
    Proteina getProteina();
    Carbohidrat getCarbohidrat();
    List<Sos> getSosuri();
    Muratura getMuratura();
    Invelis getInvelis();
    Fibre getFibre();
    Healthy getHealthy();

    void setProteina(Proteina proteina);
    void setCarbohidrat(Carbohidrat carbohidrat);
    void setSosuri(List<Sos> sosuri);
    void setMuratura(Muratura muratura);
    void setInvelis(Invelis invelis);
    void setFibre(Fibre fibre);
    void setHealthy(Healthy healthy);
}
