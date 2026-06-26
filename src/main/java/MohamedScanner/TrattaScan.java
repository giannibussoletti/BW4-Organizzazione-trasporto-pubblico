package MohamedScanner;

import DAO.TrattaDAO;
import entities.Tratta;
import jakarta.persistence.EntityManager;

import java.time.LocalTime;
import java.util.Scanner;

public class TrattaScan {

    private final TrattaDAO trattaDAO;
    private final Scanner scanner;

    public TrattaScan(EntityManager em) {
        this.trattaDAO = new TrattaDAO(em);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\nGESTIONE TRATTE");
            System.out.println("1. Crea nuova tratta");
            System.out.println("2. Mostra tutte le tratte");
            System.out.println("3. Cerca tratta per ID");
            System.out.println("0. Torna indietro");
            System.out.print("scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> creaTratta();
                case "2" -> mostraTratte();
                case "3" -> cercaTratta();
                case "0" -> running = false;
                default -> System.out.println("scelta non valida");
            }
        }
    }

    private void creaTratta() {
        System.out.println("Inserisci partenza:");
        String partenza = scanner.nextLine();

        System.out.println("Inserisci capolinea:");
        String capolinea = scanner.nextLine();

        LocalTime tempoPercorrenza = null;

        while (tempoPercorrenza == null) {
            try {
                System.out.println("Inserisci tempo percorrenza (formato HH:mm):");
                String input = scanner.nextLine();
                tempoPercorrenza = LocalTime.parse(input);
            } catch (Exception e) {
                System.out.println("Formato non valido \n Devi usare HH:mm (esempio: 01:30)");
            }
        }


        Tratta t = new Tratta(partenza, capolinea, tempoPercorrenza);
        trattaDAO.save(t);

        System.out.println("Tratta creata!");
    }

    private void mostraTratte() {
        trattaDAO.showAll().forEach(System.out::println);
    }

    private void cercaTratta() {
        System.out.println("Inserisci ID tratta:");
        String id = scanner.nextLine();

        try {
            Tratta t = trattaDAO.findById(id);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println("Tratta non trovata.");
        }
    }
}
