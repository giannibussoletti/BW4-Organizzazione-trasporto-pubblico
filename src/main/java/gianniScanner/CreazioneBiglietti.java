package gianniScanner;

import DAO.PercorrenzaDAO;
import DAO.PuntoEmissioneDAO;
import DAO.SingoloBigliettoDAO;
import DAO.TrattaDAO;
import entities.Percorrenza;
import entities.PuntoEmissione;
import entities.SingoloBiglietto;
import entities.Tratta;

import java.util.List;
import java.util.Scanner;

public class CreazioneBiglietti {

    public static void vendita(String choiceBiglietti, TrattaDAO trattaDAO, PercorrenzaDAO percorrenzaDAO, PuntoEmissioneDAO puntoEmissioneDAO, SingoloBigliettoDAO singoloBigliettoDAO, PuntoEmissione puntoScelto) {
        Scanner scanner = new Scanner(System.in);
        switch (choiceBiglietti) {
            case "si" -> {
                System.out.println("Queste sono le tratte disponibili");
                List<Tratta> tratte = trattaDAO.showAll();

                for (int i = 0; i < tratte.size(); i++) {
                    System.out.println((i + 1) + ") " + tratte.get(i));
                }
                System.out.println("Quale tratta devi fare? (Usa i numeri all'inizio per scegliere)");
                int sceltaTratta = Integer.parseInt(scanner.nextLine());
                List<Percorrenza> mezziPercorrenze = percorrenzaDAO.findMezzoPerTratta(tratte.get(sceltaTratta - 1));
                System.out.println("Questi sono i mezzi disponibili per la tratta");
                System.out.println("Scegli il tuo veicolo: (Usa i numeri all'inizio per scegliere)");
                for (int i = 0; i < mezziPercorrenze.size(); i++) {
                    System.out.println((i + 1) + ") " + mezziPercorrenze.get(i).getMezzo().getTipoMezzo() +
                            ", Orario: " +
                            mezziPercorrenze.get(i).getOraPartenza());
                }
                int sceltaVeicolo = Integer.parseInt(scanner.nextLine());
                puntoEmissioneDAO.save(puntoScelto);
                singoloBigliettoDAO.save(new SingoloBiglietto(mezziPercorrenze.get(sceltaVeicolo - 1).getMezzo(), puntoScelto));
                System.out.println("grazie mille per l'acquisto, ti auguriamo buon viaggio!");
                System.exit(0);
            }
            case "no" -> {
                System.out.println("Ti auguriamo un buon proseguimento.\nArrivederci al prossimo acquisto!");
                System.exit(0);
            }
        }
    }
}
