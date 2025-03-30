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
    private static final String SOS_FILE = "sosuri.txt";

    public static void main(String[] args) {
        boolean running = true;

        listaSosuri.add(new Sos(TipSos.MAIONEZA));
        listaSosuri.add(new Sos(TipSos.KETCHUP));
        listaSosuri.add(new Sos(TipSos.USTUROI));
        listaSosuri.add(new Sos(TipSos.TAHINI));
        listaSosuri.add(new SosFermentabil(TipSos.SAMURAI, 8));
        listaSosuri.add(new SosFermentabil(TipSos.TZATZIKI, 12));

        KebapBuilder builder = new KebapBuilder();
        try {
            builder.adaugaProteina(TipProteina.PUI)
                    .adaugaCarbohidrat(TipCarbohidrat.OREZ)
                    .adaugaInvelis(TipInvelis.LIPIE)
                    .adaugaMuratura(TipMuratura.CASTRAVETI)
                    .adaugaFibra(TipFibre.VARZA)
                    .adaugaHealthy(TipHealthy.RIDICHE)
                    .adaugaSos(TipSos.USTUROI)
                    .adaugaSos(TipSos.MAIONEZA)
                    .adaugaSosFermentabil(TipSos.SAMURAI,8);

            Kebap kebapImplicit = builder.build();
            listaKebapuri.add(kebapImplicit);
            System.out.println("Kebap implicit creat și adăugat în listă.");
        } catch (LipsaProteinaException | LipsaCarbohidratException e) {
            System.out.println("Eroare la crearea kebap-ului implicit: " + e.getMessage());
        }

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
                case 7 -> serializeazaSosuri();
                case 8 -> deserializareSosuri();
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
            throw new LipsaProteinaException("Kebap-ul trebuie să aibă o sursă de proteină.");
        } else {
            builder.adaugaProteina(tipProteina);
        }

        System.out.println("Alege carbohidrat:");
        TipCarbohidrat tipCarbohidrat = selectEnumOption(TipCarbohidrat.class);
        if (tipCarbohidrat == TipCarbohidrat.FARA) {
            throw new LipsaCarbohidratException();
        } else {
            builder.adaugaCarbohidrat(tipCarbohidrat);
        }

        System.out.println("Alege înveliș:");
        TipInvelis tipInvelis = selectEnumOption(TipInvelis.class);

        if (tipInvelis == TipInvelis.LIPIE) {
            System.out.println("Ești sigur că vrei LIPIE ca înveliș?");
            System.out.println("1. Da");
            System.out.println("2. Vreau SALATA");
            System.out.println("3. Vreau FARA");

            int confirmare = Integer.parseInt(scanner.nextLine());
            switch (confirmare) {
                case 1 -> builder.adaugaInvelis(TipInvelis.LIPIE);
                case 2 -> builder.adaugaInvelis(TipInvelis.SALATA);
                case 3 -> builder.adaugaInvelis(TipInvelis.FARA);
                default -> System.out.println("Opțiune invalidă.");
            }
        } else {
            builder.adaugaInvelis(tipInvelis);
        }

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

    private static void serializeazaSosuri() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SOS_FILE))) {
            for (Sos sos : listaSosuri) {
                if (sos instanceof SosFermentabil sf) {
                    writer.write(sf.getTip() + "," + sf.getOreValabilitate());
                } else {
                    writer.write(sos.getTip().toString());
                }
                writer.newLine();
            }
            System.out.println("Sosuri salvate în fișier.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializareSosuri() {
        listaSosuri.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(SOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                TipSos tip = TipSos.valueOf(parts[0]);
                if (parts.length == 2) {
                    int ore = Integer.parseInt(parts[1]);
                    listaSosuri.add(new SosFermentabil(tip, ore));
                } else {
                    listaSosuri.add(new Sos(tip));
                }
            }
            System.out.println("Sosuri încărcate din fișier.");
            System.out.println(listaSosuri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
