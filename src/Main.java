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

        listaSosuri.add(new Sos("Maioneza"));
        listaSosuri.add(new Sos("Ketchup"));
        listaSosuri.add(new Sos("Usturoi"));
        listaSosuri.add(new Sos("Tahini"));
        listaSosuri.add(new SosFermentabil("Samurai", 8));
        listaSosuri.add(new SosFermentabil("Tzatziki", 12));

        KebapBuilder builder = new KebapBuilder();
        try {
            builder.adaugaProteina(TipProteina.PUI)
                    .adaugaCarbohidrat(TipCarbohidrat.OREZ)
                    .adaugaInvelis(TipInvelis.LIPIE)
                    .adaugaMuratura(TipMuratura.CASTRAVETI)
                    .adaugaFibra(TipFibre.VARZA)
                    .adaugaHealthy(TipHealthy.RIDICHE)
                    .adaugaSos("Usturoi")
                    .adaugaSos("Maioneza")
                    .adaugaSosFermentabil("Samurai",8);

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

        TipProteina tipProteina = null;
        boolean proteinaValida = false;
        while (!proteinaValida) {
            try {
                System.out.println("Alege proteina:");
                tipProteina = selectEnumOption(TipProteina.class);
                if (tipProteina == TipProteina.FARA) {
                    throw new LipsaProteinaException("Kebap-ul trebuie să aibă o sursă de proteină.");
                }
                builder.adaugaProteina(tipProteina);
                proteinaValida = true;
            } catch (LipsaProteinaException e) {
                System.out.println(e.getMessage());
            }
        }

        TipCarbohidrat tipCarbohidrat = null;
        boolean carboValida = false;
        while (!carboValida) {
            try {
                System.out.println("Alege carbohidrat:");
                tipCarbohidrat = selectEnumOption(TipCarbohidrat.class);
                if (tipCarbohidrat == TipCarbohidrat.FARA) {
                    throw new LipsaCarbohidratException();
                }
                builder.adaugaCarbohidrat(tipCarbohidrat);
                carboValida = true;
            } catch (LipsaCarbohidratException e) {
            System.out.println(e.getMessage());
            }
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

        boolean continuaAdaugare = true;
        while (continuaAdaugare) {

            System.out.println("Doriți să adăugați un sos?");
            System.out.println("1. Da");
            System.out.println("2. Nu");
            System.out.print("Alege opțiunea: ");
            String raspuns = scanner.nextLine();

            if (raspuns.equals("1")) {
                if (builder.getNumarSosuri() >= 3) {
                    System.out.println("Ai atins numărul maxim de 3 sosuri.");
                    break;
                }
                listeazaSosuri();
                System.out.print("Introduceți numele sosului: ");
                String nume = scanner.nextLine().trim().toLowerCase();

                Optional<Sos> sosOptional = listaSosuri.stream()
                        .filter(s -> s.getNume().toLowerCase().equals(nume))
                        .findFirst();

                if (sosOptional.isPresent()) {
                    Sos sos = sosOptional.get();
                    try {
                        if (sos.esteFermentabil()) {
                            SosFermentabil sf = (SosFermentabil) sos;
                            builder.adaugaSosFermentabil(sf.getNume(), sf.getOreValabilitate());
                        } else {
                            builder.adaugaSos(sos.getNume());
                        }
                    } catch (LimitaSosDepasitaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                } else {
                    System.out.println("Sosul nu există în lista de sosuri create.");
                }

            } else if (raspuns.equals("2")) {
                continuaAdaugare = false;
            } else {
                System.out.println("Opțiune invalidă. Te rog alege 1 sau 2.");
            }
        }
        Kebap kebap = builder.build();
        listaKebapuri.add(kebap);
        System.out.println("Kebap creat cu succes!");
        System.out.println(kebap);
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
                .toList();

        filtrate.forEach(System.out::println);
    }

    private static void listeazaSosuri() {
    if (listaSosuri.isEmpty()) {
        System.out.println("Nu există sosuri în listă.");
        return;
    }
    System.out.println("\nLista sosurilor disponibile:");
    for (int i = 0; i < listaSosuri.size(); i++) {
        Sos sos = listaSosuri.get(i);
        if (sos instanceof SosFermentabil sf) {
            System.out.println((i + 1) + ". " + sf.getNume() + " (fermentabil - " + sf.getOreValabilitate() + "h)");
        } else {
            System.out.println((i + 1) + ". " + sos.getNume());
        }
    }
}

    private static void creeazaSos() {
        System.out.print("Introduceți numele sosului: ");
        String numeSos = scanner.nextLine().trim();

        boolean exista = listaSosuri.stream()
                .anyMatch(s -> s.getNume().equalsIgnoreCase(numeSos));

        if (exista) {
            System.out.println("Sosul \"" + numeSos + "\" există deja în listă.");
            return;
        }

        System.out.print("Este sosul fermentabil? (1. Da / 2. Nu): ");
        String raspuns = scanner.nextLine().trim();
        boolean sosAdaugat = false;
        if (raspuns.equals("1")) {
            try {
                System.out.print("Valabilitate în ore: ");
                int ore = Integer.parseInt(scanner.nextLine());
                if (ore <= 0) {
                    System.out.println("Valabilitatea trebuie să fie mai mare decât 0.");
                    return;
                }
                listaSosuri.add(new SosFermentabil(numeSos, ore));
                sosAdaugat = true;
            } catch (NumberFormatException e) {
                System.out.println("Introdu un număr valid pentru ore.");
            }
        } else if (raspuns.equals("2")) {
            listaSosuri.add(new Sos(numeSos));
            sosAdaugat = true;
        } else {
            System.out.println("Opțiune invalidă. Sosul nu a fost adăugat.");
        }
        if (sosAdaugat) {
            System.out.println("Sos adăugat cu succes.");
        }
    }

    private static void stergeSos() {
        listeazaSosuri();
        System.out.print("Introduceți indexul sosului de șters: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < listaSosuri.size()) {
                Sos sosSters = listaSosuri.remove(index);
                System.out.println("Sosul \"" + sosSters.getNume() + "\" a fost șters cu succes.");
            } else {
                System.out.println("Index invalid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valoare introdusă invalidă. Trebuie introdus un număr.");
        }
    }

    private static void serializeazaSosuri() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SOS_FILE))) {
            for (Sos sos : listaSosuri) {
                if (sos.esteFermentabil()) {
                    SosFermentabil sf = (SosFermentabil) sos;
                    writer.write(sf.getNume() + "," + sf.getOreValabilitate());
                } else {
                    writer.write(sos.getNume());
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
                if (parts.length == 2) {
                    String nume = parts[0].trim();
                    int ore = Integer.parseInt(parts[1].trim());
                    listaSosuri.add(new SosFermentabil(nume, ore));
                } else {
                    listaSosuri.add(new Sos(parts[0].trim()));
                }
            }
            System.out.println("Sosuri încărcate din fișier.");
            listeazaSosuri();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
