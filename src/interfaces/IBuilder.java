package interfaces;

import Models.Kebap;
import enums.*;

public interface IBuilder {
    IBuilder adaugaProteina(TipProteina tip);
    IBuilder adaugaCarbohidrat(TipCarbohidrat tip);
    IBuilder adaugaMuratura(TipMuratura tip);
    IBuilder adaugaInvelis(TipInvelis tip);
    IBuilder adaugaFibra(TipFibre tip);
    IBuilder adaugaHealthy(TipHealthy tip);
    IBuilder adaugaSos(String nume);
    IBuilder adaugaSosFermentabil(String nume, int oreValabilitate);
    Kebap build();
    int getNumarSosuri();
}
