package SCANNER;

import DAO.StatoMezzoDAO;
import DAO.MezzoDAO;
import entities.Mezzo;
import entities.StatoDelMezzo;
import enums.StatoMezzo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StatoDelMezzoScanner {
    private final StatoMezzoDAO statoMezzoDAO;
    private final MezzoDAO mezzoDAO;
    private final Scanner scanner;

    public StatoDelMezzoScanner(EntityManager em) {
        this.statoMezzoDAO = new StatoMezzoDAO(em);
        this.mezzoDAO = new MezzoDAO(em);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\nGESTIONE STATO DEL MEZZO");
            System.out.println("1. Assegna un nuovo Stato a un Mezzo (Save)");
            System.out.println("2. Cerca Stato tramite ID (Find By ID)");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    gestisciSave();
                    break;
                case "2":
                    gestisciFindById();
                    break;
                case "0":
                    System.out.println("Uscita dal menu gestione stati.");
                    running = false;
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    private void gestisciSave() {
        System.out.println("\nNuovo Stato del Mezzo");

        // 1. Recupero del Mezzo
        System.out.print("Inserisci l'UUID del Mezzo coinvolto: ");
        String idMezzoStr = scanner.nextLine();
        Mezzo mezzo;
        try {
            mezzo = mezzoDAO.findById(idMezzoStr);
            if (mezzo == null) {
                System.out.println("Mezzo non trovato nel database.");
                return;
            }
        } catch (Exception e) {
            System.out.println("ID Mezzo non valido o non trovato: " + e.getMessage());
            return;
        }

        // 2. Selezione Tipo Stato (Enum)
        System.out.println("Seleziona il tipo di stato:");
        System.out.println("1. IN_MANUTENZIONE");
        System.out.println("2. IN_SERVIZIO");
        System.out.print("Scelta: ");
        String tipoScelta = scanner.nextLine();
        StatoMezzo tipoStato;

        if (tipoScelta.equals("1")) {
            tipoStato = StatoMezzo.ATTIVO;
        } else if (tipoScelta.equals("2")) {
            tipoStato = StatoMezzo.MANUTENZIONE;
        } else {
            System.out.println("Scelta non valida. Annullamento.");
            return;
        }

        // 3. Data Inizio
        System.out.print("Inserisci la data di inizio (AAAA-MM-GG) [Premi ENTER per usare OGGI]: ");
        String dataStr = scanner.nextLine();
        LocalDate dataInizio;

        if (dataStr.trim().isEmpty()) {
            dataInizio = LocalDate.now();
        } else {
            try {
                dataInizio = LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido. Operazione annullata.");
                return;
            }
        }

        // Creación y persistencia
        StatoDelMezzo nuovoStato = new StatoDelMezzo(tipoStato, dataInizio, mezzo);
        try {
            statoMezzoDAO.save(nuovoStato);
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    private void gestisciFindById() {
        System.out.println("\nRicerca Stato");
        System.out.print("Inserisci l'UUID dello Stato da cercare: ");
        String idStatoStr = scanner.nextLine();
        try {
            StatoDelMezzo statoTrovato = statoMezzoDAO.findById(idStatoStr);
            System.out.println("Risultato trovato:");
            System.out.println(statoTrovato);
        } catch (Exception e) {
            System.out.println("Nessun record trovato con l'ID fornito o formato non valido.");
        }
    }
}
