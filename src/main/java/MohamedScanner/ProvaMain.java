package MohamedScanner;

import DAO.*;
import SCANNER.MezzoScanner;
import SCANNER.StatoDelMezzoScanner;
import entities.DistributoreAutomatico;
import entities.PuntoEmissione;
import entities.Tessera;
import enums.TipoAbbonamento;
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
    private static final MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static final StatoMezzoDAO statoMezzoDAO = new StatoMezzoDAO(em);
    private static final TrattaScan trattaScanner = new TrattaScan(em);
    private static final PercorrenzaScan percorrenzaScanner = new PercorrenzaScan(em);
    private static final MezzoScanner mezzoScanner = new MezzoScanner(em);
    private static final StatoDelMezzoScanner statoScanner = new StatoDelMezzoScanner(em);

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
                case 1 -> System.out.println("h");
                case 2 -> System.out.println("h");
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
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> mezzoScanner.start();
                case 2 -> statoScanner.start();
                case 3 -> trattaScanner.start();
                case 4 -> percorrenzaScanner.start();
                case 0 -> b = true;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void verificaAbbonamento() {
        System.out.println("inserisci codice tessera");
        long codice = Long.parseLong(scanner.nextLine());

        boolean valido = abbonamentoDAO.isAbbonamentoValidoByCodiceTessera(codice);
    }
    private static void acquistaAbbonamento() {

        System.out.println("Inserisci codice tessera");
        long codice = Long.parseLong(scanner.nextLine());

        Tessera tessera = new TesseraDAO(em).findByCodiceTessera(codice);
        System.out.println("Tipo abbonamento:");
        System.out.println("1) Settimanale");
        System.out.println("2) Mensile");
        int scelta = Integer.parseInt(scanner.nextLine());
        TipoAbbonamento tipo =
                scelta == 1 ? TipoAbbonamento.SETTIMANALE : TipoAbbonamento.MENSILE;

        PuntoEmissione punto = new DistributoreAutomatico("Via Roma 10", true);

        abbonamentoDAO.creaAbbonamento(punto, tipo, tessera);

        System.out.println("abbonamento acquistato con successo");
    }
}
