package SCANNER;
import DAO.MezzoDAO;
import entities.Mezzo;
import enums.TipoMezzo;
import jakarta.persistence.EntityManager;

import java.util.Scanner;
public class MezzoScanner {
    private final MezzoDAO mezzoDAO;
    private final Scanner scanner;

    public MezzoScanner(EntityManager em) {
        this.mezzoDAO = new MezzoDAO(em);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== GESTIONE MEZZI DI TRASPORTO =====");
            System.out.println("1. Crea e Salva un nuovo Mezzo");
            System.out.println("2. Cerca Mezzo tramite ID");
            System.out.println("3. Elimina Mezzo tramite ID");
            System.out.println("0. Torna al Menu Principale");
            System.out.print("Scegli un'opzione: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    gestisciSave();
                    break;
                case "2":
                    gestisciFindById();
                    break;
                case "3":
                    gestisciDeleteById();
                    break;
                case "0":
                    System.out.println("Uscita dal menu gestione mezzi.");
                    running = false;
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    private void gestisciSave() {
        System.out.println("\n--- Creazione Nuovo Mezzo ---");

        // 1. Selezione TipoMezzo (Enum)
        System.out.println("Seleziona il tipo di mezzo:");
        System.out.println("1. AUTOBUS");
        System.out.println("2. TRAM");
        System.out.print("Scelta: ");
        String tipoScelta = scanner.nextLine();
        TipoMezzo tipoMezzo;

        if (tipoScelta.equals("1")) {
            tipoMezzo = TipoMezzo.TRAM;
        } else if (tipoScelta.equals("2")) {
            tipoMezzo = TipoMezzo.TRAM;
        } else {
            System.out.println("Scelta errata. Operazione annullata.");
            return;
        }

        // 2. Capienza Massima
        System.out.print("Inserisci la capienza massima (numero intero): ");
        int capienza;
        try {
            capienza = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Numero non valido. Operazione annullata.");
            return;
        }

        // 3. Targa Veicolo
        System.out.print("Inserisci la targa del veicolo: ");
        String targa = scanner.nextLine().trim();
        if (targa.isEmpty()) {
            System.out.println("La targa non può essere vuota. Operazione annullata.");
            return;
        }

        // Costruzione dell'oggetto e persistenza tramite DAO
        Mezzo nuovoMezzo = new Mezzo(tipoMezzo, capienza, targa);
        try {
            mezzoDAO.save(nuovoMezzo);
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio del mezzo: " + e.getMessage());
        }
    }

    private void gestisciFindById() {
        System.out.println("\n--- Ricerca Mezzo ---");
        System.out.print("Inserisci l'UUID del Mezzo da cercare: ");
        String idStr = scanner.nextLine().trim();

        try {
            Mezzo mezzoTrovato = mezzoDAO.findById(idStr);
            System.out.println("Mezzo trovato:");
            System.out.println(mezzoTrovato);
            System.out.println("Targa: " + mezzoTrovato.getTargaVeicolo());
            System.out.println("Numero di stati registrati: " + mezzoTrovato.getPeriodiStato().size());
        } catch (Exception e) {
            System.out.println("Nessun mezzo trovato con l'ID fornito o formato non valido.");
        }
    }

    private void gestisciDeleteById() {
        System.out.println("\n--- Eliminazione Mezzo ---");
        System.out.print("Inserisci l'UUID del Mezzo da eliminare: ");
        String idStr = scanner.nextLine().trim();

        try {
            // Chiamata diretta al metodo del tuo DAO
            mezzoDAO.deleteById(idStr);
        } catch (Exception e) {
            System.out.println("Impossibile eliminare il mezzo. Verificare l'ID inserito.");
        }
    }

}
