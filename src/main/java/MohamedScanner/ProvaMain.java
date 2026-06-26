package SCANNER;

import DAO.AbbonamentoDAO;
import DAO.PercorrenzaDAO;
import DAO.TrattaDAO;
import MohamedScanner.PercorrenzaScan;
import MohamedScanner.TrattaScan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class ProvaMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bw4traspublicpu");
    public static final EntityManager em = emf.createEntityManager();
    private static final AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
    private static final TrattaDAO trattaDAO = new TrattaDAO(em);
    private static final PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
    private static final TrattaScan trattaScanner = new TrattaScan(em);
    private static final PercorrenzaScan percorrenzaScanner = new PercorrenzaScan(em);

    public static void main(String[] args) {

        boolean i = true;

        while (i) {
            System.out.println(" MENU PRINCIPALE");
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
            System.out.println("\n MENU UTENTE ");
            System.out.println("1) Acquista biglietto");
            System.out.println("2) Vidima biglietto");
            System.out.println("3) Crea tessera");
            System.out.println("4) Verifica abbonamento");
            System.out.println("5) Acquista abbonamento");
            System.out.println("0) Torna indietro");
            System.out.print("scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> System.out.println("f");
                case 2 -> System.out.println("f");
                case 3 -> System.out.println("f");
                case 4 -> verificaAbbonamento();
                case 5 -> System.out.println("f");
                case 0 -> b = true;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void menuAdmin() {
        boolean b = false;

        while (!b) {
            System.out.println("\n MENU AMMINISTRATORE ");
            System.out.println("1) Biglietti emessi in un periodo");
            System.out.println("2) Abbonamenti emessi in un periodo");
            System.out.println("3) Biglietti vidimati per mezzo");
            System.out.println("4) Mezzi in manutenzione");
            System.out.println("5) Percorrenze attive");
            System.out.println("6) Tempo medio percorrenza tratta");
            System.out.println("7) Gestione Tratte");
            System.out.println("8) Gestione Percorrenze");
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> System.out.println("f");
                case 2 -> System.out.println("f");
                case 3 -> System.out.println("f");
                case 4 -> System.out.println("f");
                case 5 -> System.out.println("f");
                case 6 -> System.out.println("f");
                case 7 -> trattaScanner.start();
                case 8 -> percorrenzaScanner.start();

                case 0 -> b = true;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void verificaAbbonamento() {
        System.out.println("Inserisci codice tessera:");
        long codice = Long.parseLong(scanner.nextLine());

        boolean valido = abbonamentoDAO.isAbbonamentoValidoByCodiceTessera(codice);

        if (valido) {
            System.out.println("L'abbonamento è valido.");
        } else {
            System.out.println("L'abbonamento NON è valido.");
        }
    }
}
