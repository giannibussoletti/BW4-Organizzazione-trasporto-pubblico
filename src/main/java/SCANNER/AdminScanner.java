package SCANNER;

import DAO.*;
import entities.Mezzo;
import entities.PuntoEmissione;
import entities.SingoloBiglietto;
import entities.Tratta;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminScanner {

    private final Scanner scanner = new Scanner(System.in);

    private final SingoloBigliettoDAO bigliettoDAO;
    private final PuntoEmissioneDAO puntoDAO;
    private final AbbonamentoDAO abbonamentoDAO;
    private final PercorrenzaDAO percorrenzaDAO;
    private final MezzoDAO mezzoDAO;
    private final TrattaDAO trattaDAO;

    public AdminScanner(EntityManager em) {
        this.bigliettoDAO = new SingoloBigliettoDAO(em);
        this.puntoDAO = new PuntoEmissioneDAO(em);
        this.abbonamentoDAO = new AbbonamentoDAO(em);
        this.percorrenzaDAO = new PercorrenzaDAO(em);
        this.mezzoDAO = new MezzoDAO(em);
        this.trattaDAO = new TrattaDAO(em);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n MENU STATISTICHE AMMINISTRATORE ");
            System.out.println("1) Numero totale biglietti venduti");
            System.out.println("2) Numero biglietti venduti per punto di emissione");
            System.out.println("3) Verifica validità tessera");
            System.out.println("4) Numero vidimazioni per mezzo");
            System.out.println("5) Numero vidimazioni in un periodo");
            System.out.println("6) Storico manutenzioni mezzo");
            System.out.println("7) Numero percorrenze per tratta");
            System.out.println("8) Differenza tempo percorrenza");
            System.out.println("0) Torna indietro");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> countBigliettiTotali();
                case "2" -> countBigliettiPerPunto();
                case "3" -> verificaTessera();
                case "4" -> countVidimazioniPerMezzo();
                case "5" -> countVidimazioniPeriodo();
                case "6" -> storicoManutenzioni();
                case "7" -> numPercorrenzeTratta();
                case "8" -> differenzaTempoPercorrenza();
                case "0" -> running = false;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private void countBigliettiTotali() {
        long count = bigliettoDAO.countAll();
        System.out.println("Totale biglietti venduti: " + count);
    }

    private void countBigliettiPerPunto() {
        System.out.print("Inserisci ID punto di emissione: ");
        String id = scanner.nextLine();
        long count = bigliettoDAO.countByPunto(id);
        System.out.println("Biglietti venduti da questo punto: " + count);
    }

    private void verificaTessera() {
        System.out.print("Inserisci ID abbonamento: ");
        String id = scanner.nextLine();
        boolean valido = abbonamentoDAO.isTesseraValida(id);
        System.out.println(valido ? "Tessera valida" : "Tessera NON valida");
    }

    private void countVidimazioniPerMezzo() {
        System.out.print("Inserisci ID mezzo: ");
        String id = scanner.nextLine();

        Mezzo m = mezzoDAO.findById(id);

        long count = bigliettoDAO.getBigliettiVidimatiPerMezzoInPeriodo(
                m,
                LocalDateTime.of(1900, 1, 1, 0, 0),
                LocalDateTime.now()
        );

        System.out.println("Vidimazioni totali per mezzo: " + count);
    }


    private void countVidimazioniPeriodo() {
        System.out.print("Data inizio (YYYY-MM-DD): ");
        LocalDate inizioData = LocalDate.parse(scanner.nextLine());

        System.out.print("Data fine (YYYY-MM-DD): ");
        LocalDate fineData = LocalDate.parse(scanner.nextLine());

        LocalDateTime inizio = inizioData.atStartOfDay();
        LocalDateTime fine = fineData.atTime(23, 59, 59);

        long count = bigliettoDAO.getBigliettiVidimatiTotaliInPeriodo(inizio, fine);

        System.out.println("Vidimazioni nel periodo scelto e : " + count);
    }



    private void storicoManutenzioni() {
        System.out.print("Inserisci ID mezzo: ");
        String id = scanner.nextLine();

        Mezzo m = mezzoDAO.findById(id);
        List<?> manutenzioni = m.getManutenzioni();

        System.out.println("Numero manutenzioni: " + manutenzioni.size());
        manutenzioni.forEach(System.out::println);
    }

    private void numPercorrenzeTratta() {
        System.out.print("Inserisci ID tratta: ");
        String id = scanner.nextLine();

        long count = percorrenzaDAO.numPercorrenzeTratta(id);

        System.out.println("Numero percorrenze per tratta: " + count);
    }


    private void differenzaTempoPercorrenza() {
        System.out.print("Inserisci ID percorrenza: ");
        String id = scanner.nextLine();

        long diff = percorrenzaDAO.differenzaTempoPercorrenza(id);

        System.out.println("Differenza tempo percorrenza: " + diff + " minuti");
    }


}
