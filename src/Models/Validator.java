package Models;
import Models.Ingrediente.Sos;
import exceptions.LimitaSosDepasitaException;
import exceptions.LipsaProteinaException;
import exceptions.LipsaCarbohidratException;

import java.util.List;
public class Validator {

    public static void valideazaAdaugareSos(List<Sos> sosuri, Kebap kebap) {
        if (sosuri.size() >= 3) {
            throw new LimitaSosDepasitaException();
        }
        if (kebap.getProteina() == null) {
            throw new LipsaProteinaException("Nu poți adăuga sosuri fără să fi adăugat mai întâi proteina.");
        }
    }
    public static void valideazaKebapFinalizat(Kebap kebap) {
        if (kebap.getProteina() == null) {
            throw new LipsaProteinaException("Kebap-ul trebuie să conțină o proteină.");
        }
        if (kebap.getCarbohidrat() == null) {
            throw new LipsaCarbohidratException();
        }
    }
}
