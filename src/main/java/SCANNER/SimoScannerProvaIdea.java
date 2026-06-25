package SCANNER;

import DAO.AbbonamentoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class SimoScannerProvaIdea {

    private static final Scanner scanner = new Scanner(System.in);

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw4traspublicpu");
    public static final EntityManager em = emf.createEntityManager();
    private static AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
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
                case 1 ->System.out.println("f");
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
            System.out.println(" MENU AMMINISTRATORE ");
            System.out.println("1) Biglietti emessi in un periodo");
            System.out.println("2) Abbonamenti emessi in un periodo");
            System.out.println("3) Biglietti vidimati per mezzo");
            System.out.println("4) Mezzi in manutenzione");
            System.out.println("5) Percorrenze attive");
            System.out.println("6) Tempo medio percorrenza tratta");
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> System.out.println("f");
                case 2 ->System.out.println("f");
                case 3 -> System.out.println("f");
                case 4 -> System.out.println("f");
                case 5 -> System.out.println("f");
                case 6 -> System.out.println("f");
                case 0 -> b = true;
                default -> System.out.println("sacelta non valida");
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
