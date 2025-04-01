package interfaces;

import Models.Ingrediente.*;

import java.util.List;

public interface IKebap {
    Proteina getProteina();
    Carbohidrat getCarbohidrat();

    void setProteina(Proteina proteina);
    void setCarbohidrat(Carbohidrat carbohidrat);
    void setSosuri(List<Sos> sosuri);
    void setMuratura(Muratura muratura);
    void setInvelis(Invelis invelis);
    void setFibre(Fibre fibre);
    void setHealthy(Healthy healthy);
}
