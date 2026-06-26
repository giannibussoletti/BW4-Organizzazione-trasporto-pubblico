package SCANNER;

import DAO.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;
import java.util.UUID;

public class ProvaMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bw4traspublicpu");
    public static final EntityManager em = emf.createEntityManager();

    private static final AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
    private static final TrattaDAO trattaDAO = new TrattaDAO(em);
    private static final PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
    private static final MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static final StatoMezzoDAO statoMezzoDAO = new StatoMezzoDAO(em);
    private static final TrattaScan trattaScanner = new TrattaScan(em);
    private static final PercorrenzaScan percorrenzaScanner = new PercorrenzaScan(em);
    private static final MezzoScanner mezzoScanner = new MezzoScanner(em);
    private static final StatoDelMezzoScanner statoScanner = new StatoDelMezzoScanner(em);
    private static final TesseraDAO tesseraDAO = new TesseraDAO(em);
    private static final UtenteDAO utenteDAO = new UtenteDAO(em);
    private static final PuntoEmissioneDAO puntoEmissioneDAO = new PuntoEmissioneDAO(em);
    private static final SingoloBigliettoDAO bigliettoDAO = new SingoloBigliettoDAO(em);

    private static final AdminScanner adminScanner = new AdminScanner(em);

    public static void main(String[] args) {

        boolean i = true;

        while (i) {
            System.out.println("\nMENU PRINCIPALE");
            System.out.println("1) Utente");
            System.out.println("2) Amministratore");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> menuUtente();
                case 2 -> menuAdmin();
                case 0 -> i = false;
                default -> System.out.println("Scelta non valida");
            }
        }

        em.close();
        emf.close();
        System.out.println("Programma terminato");
    }

    private static void menuUtente() {
        boolean b = false;

        while (!b) {
            System.out.println("\nMENU UTENTE");
            System.out.println("1) Acquista titolo di viaggio");
            System.out.println("2) Vidima biglietto");
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 ->
                        Acquisizione.BigliettiEAbbonamenti(tesseraDAO, utenteDAO, bigliettoDAO, trattaDAO, percorrenzaDAO, puntoEmissioneDAO, abbonamentoDAO);
                case 2 -> vidimaBiglietto();
                case 0 -> b = true;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void menuAdmin() {
        boolean b = false;

        while (!b) {
            System.out.println("\nMENU AMMINISTRATORE");
            System.out.println("1) Gestione Mezzi");
            System.out.println("2) Gestione Stato Mezzi");
            System.out.println("3) Gestione Tratte");
            System.out.println("4) Gestione Percorrenze");
            System.out.println("5) Statistiche Amministratore");
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> mezzoScanner.start();
                case 2 -> statoScanner.start();
                case 3 -> trattaScanner.start();
                case 4 -> percorrenzaScanner.start();
                case 5 -> adminScanner.start();
                case 0 -> b = true;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void vidimaBiglietto() {
        System.out.println("inserisci l'ID del biglietto da vidimare");
        String id = scanner.nextLine();

        try {
            UUID uuid = UUID.fromString(id);
            SingoloBigliettoDAO bigliettoDAO = new SingoloBigliettoDAO(em);
            bigliettoDAO.vidima(uuid);
        } catch (IllegalArgumentException e) {
            System.out.println("formato ID non valido\n Assicurati di inserire un UUID corretto");
        }
    }
}
