package SCANNER;

import DAO.MezzoDAO;
import DAO.PercorrenzaDAO;
import DAO.TrattaDAO;
import entities.Mezzo;
import entities.Percorrenza;
import entities.Tratta;
import jakarta.persistence.EntityManager;

import java.time.LocalTime;
import java.util.Scanner;

public class PercorrenzaScan {

    private final PercorrenzaDAO percorrenzaDAO;
    private final TrattaDAO trattaDAO;
    private final MezzoDAO mezzoDAO;
    private final Scanner scanner;

    public PercorrenzaScan(EntityManager em) {
        this.percorrenzaDAO = new PercorrenzaDAO(em);
        this.trattaDAO = new TrattaDAO(em);
        this.mezzoDAO = new MezzoDAO(em);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\nGESTIONE PERCORRENZE");
            System.out.println("1. Crea nuova percorrenza");
            System.out.println("2. Mostra percorrenze attive");
            System.out.println("3. Cerca percorrenza per ID");
            System.out.println("0. Torna indietro");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> creaPercorrenza();
                case "2" -> percorrenzaDAO.percorrenzeAttive().forEach(System.out::println);
                case "3" -> cercaPercorrenza();
                case "0" -> running = false;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private void creaPercorrenza() {
        System.out.println("Inserisci ID tratta:");
        String idTratta = scanner.nextLine();
        Tratta tratta = trattaDAO.findById(idTratta);

        System.out.println("Inserisci ID mezzo:");
        String idMezzo = scanner.nextLine();
        Mezzo mezzo = mezzoDAO.findById(idMezzo);

        LocalTime oraPartenza = LocalTime.now();

        Percorrenza p = new Percorrenza(oraPartenza, tratta, mezzo);
        percorrenzaDAO.save(p);

    }

    private void cercaPercorrenza() {
        System.out.println("Inserisci ID percorrenza:");
        String id = scanner.nextLine();

        try {
            Percorrenza p = percorrenzaDAO.findById(id);
            System.out.println(p);
        } catch (Exception e) {
            System.out.println("Percorrenza non trovata.");
        }
    }
}
