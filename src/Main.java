import Models.*;
import Models.Ingrediente.*;
import enums.*;
import exceptions.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final List<Kebap> listaKebapuri = new ArrayList<>();
    private static final List<Sos> listaSosuri = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Creează Kebap");
            System.out.println("2. Șterge Kebap");
            System.out.println("3. Listează Kebapuri");
            System.out.println("4. Filtrează Kebapuri după proteină");
            System.out.println("5. Creează Sos");
            System.out.println("6. Șterge Sos");
            System.out.println("7. Serializează Sosuri în fișier");
            System.out.println("8. Deserializare Sosuri din fișier");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");

            int opt = Integer.parseInt(scanner.nextLine());
            switch (opt) {
                case 1 -> creeazaKebap();
                case 2 -> stergeKebap();
                case 3 -> listeazaKebapuri();
                case 4 -> filtreazaDupaProteina();
                case 5 -> creeazaSos();
                case 6 -> stergeSos();
                //case 7 -> serializeazaSosuri();
                //case 8 -> deserializareSosuri();
                case 0 -> running = false;
                default -> System.out.println("Opțiune invalidă.");
            }
        }
    }

    private static <T extends Enum<T>> T selectEnumOption(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        return values[index];
    }

    private static void creeazaKebap() {
        KebapBuilder builder = new KebapBuilder();

        System.out.println("Alege proteina:");
        TipProteina tipProteina = selectEnumOption(TipProteina.class);
        if (tipProteina == TipProteina.FARA) {
            throw new LispaProteinaException("Kebap-ul trebuie să aibă o sursă de proteină.");
        } else {
            builder.adaugaProteina(tipProteina);
        }

        System.out.println("Alege carbohidrat:");
        TipCarbohidrat tipCarbohidrat = selectEnumOption(TipCarbohidrat.class);
        if (tipCarbohidrat == TipCarbohidrat.FARA) {
            throw new LipsaCarbohidratException("Kebap-ul trebuie să aibă o sursă de carbohidrați.");
        } else {
            builder.adaugaCarbohidrat(tipCarbohidrat);
        }

        System.out.println("Alege înveliș:");
        builder.adaugaInvelis(selectEnumOption(TipInvelis.class));

        System.out.println("Alege murătură:");
        builder.adaugaMuratura(selectEnumOption(TipMuratura.class));

        System.out.println("Alege fibre:");
        builder.adaugaFibra(selectEnumOption(TipFibre.class));

        System.out.println("Alege healthy:");
        builder.adaugaHealthy(selectEnumOption(TipHealthy.class));

        System.out.print("Câte sosuri vrei să adaugi (max 3): ");
        int nrSosuri = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < nrSosuri; i++) {
            System.out.println("Alege sosul " + (i + 1) + ":");
            TipSos tip = selectEnumOption(TipSos.class);
            Optional<Sos> sosOptional = listaSosuri.stream().filter(s -> s.getTip() == tip).findFirst();
            if (sosOptional.isPresent()) {
                Sos sos = sosOptional.get();
                if (sos instanceof SosFermentabil sf) {
                    builder.adaugaSosFermentabil(sf.getTip(), sf.getOreValabilitate());
                } else {
                    builder.adaugaSos(sos.getTip());
                }
            } else {
                System.out.println("Sosul nu există în lista de sosuri create.");
            }
        }

        Kebap k = builder.build();
        listaKebapuri.add(k);
        System.out.println("Kebap creat cu succes!");
    }

    private static void stergeKebap() {
        listeazaKebapuri();
        System.out.print("Indice kebap de șters: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < listaKebapuri.size()) {
            listaKebapuri.remove(index);
            System.out.println("Kebap șters.");
        } else {
            System.out.println("Index invalid.");
        }
    }

    private static void listeazaKebapuri() {
        for (int i = 0; i < listaKebapuri.size(); i++) {
            System.out.println(i + ". " + listaKebapuri.get(i));
        }
    }

    private static void filtreazaDupaProteina() {
        System.out.println("Alege proteina pentru filtrare:");
        TipProteina tip = selectEnumOption(TipProteina.class);
        List<Kebap> filtrate = listaKebapuri.stream()
                .filter(k -> k.getProteina().getTip() == tip)
                .collect(Collectors.toList());

        filtrate.forEach(System.out::println);
    }

    private static void creeazaSos() {
        System.out.println("Alege tipul de sos:");
        TipSos tip = selectEnumOption(TipSos.class);
        if (tip == TipSos.SAMURAI || tip == TipSos.TZATZIKI) {
            System.out.print("Valabilitate (ore): ");
            int ore = Integer.parseInt(scanner.nextLine());
            listaSosuri.add(new SosFermentabil(tip, ore));
        } else {
            listaSosuri.add(new Sos(tip));
        }
        System.out.println("Sos adăugat.");
    }

    private static void stergeSos() {
        for (int i = 0; i < listaSosuri.size(); i++) {
            System.out.println(i + ". " + listaSosuri.get(i).getNume());
        }
        System.out.print("Indice sos de șters: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < listaSosuri.size()) {
            listaSosuri.remove(index);
            System.out.println("Sos șters.");
        } else {
            System.out.println("Index invalid.");
        }
    }

}
