package gianniScanner;


import DAO.*;
import entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Acquisizione {


    public static void BigliettiEAbbonamenti(TesseraDAO tesseraDAO, UtenteDAO utenteDAO, SingoloBigliettoDAO singoloBigliettoDAO, TrattaDAO trattaDAO, PercorrenzaDAO percorrenzaDAO, PuntoEmissioneDAO puntoEmissioneDAO) {
        Scanner scanner = new Scanner(System.in);
        PuntoEmissione puntoScelto = null;
        while (true) {

            System.out.println("benvenuto");
            while (true) {

                System.out.println("Da dove stai acquistando?\n1)Distributore automatico\n2) Rivenditore autorizzato");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice == 1 || choice == 2) {
                        switch (choice) {
                            case 1 -> {
                                puntoScelto = new DistributoreAutomatico("Via Mascagni 11", true);
                            }
                            case 2 -> {
                                puntoScelto = new RivenditoreAutorizzato("Via della morte 11", "21304932", "Il Bar del demonio");
                            }
                        }
                        scanner.nextLine();
                        break;
                    } else {
                        scanner.nextLine();
                        System.out.println("Numero non valido!");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Valore non valido!");
                }
            }
            while (true) {
                System.out.println("Sei tesserato? (si o no)");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("si") || choice.equals("no")) {
                    switch (choice) {
                        case "si" -> {
                            System.out.println("Inserisci il numero di tessera");
                            String numeroTessera = scanner.nextLine();
                        }
                        case "no" -> {
                            System.out.println("Vuoi tesserarti?");
                            String tesseramento = scanner.nextLine();
                            while (true) {
                                if (tesseramento.equalsIgnoreCase("si") || tesseramento.equalsIgnoreCase("no")) {
                                    switch (tesseramento) {
                                        case "si" -> {
                                            String nomeNuovoAbbonato;
                                            String cognomeNuovoAbbonato;
                                            LocalDate dataNascitaNuovoAbbonato;
                                            while (true) {
                                                System.out.println("Inserisci il tuo nome");
                                                nomeNuovoAbbonato = scanner.nextLine();
                                                System.out.println("Confermi che " + nomeNuovoAbbonato + " è corretto? (si o no)");
                                                if (scanner.nextLine().equalsIgnoreCase("si")) break;
                                                else if (scanner.nextLine().equalsIgnoreCase("no")) {
                                                    System.out.println("Nuovo tentativo");
                                                } else {
                                                    System.out.println("valore non valido");
                                                }

                                            }
                                            while (true) {
                                                System.out.println("Inserisci il tuo cognome");
                                                cognomeNuovoAbbonato = scanner.nextLine();
                                                System.out.println("Confermi che " + cognomeNuovoAbbonato + " è corretto? (si o no)");
                                                if (scanner.nextLine().equalsIgnoreCase("si")) break;
                                                else if (scanner.nextLine().equalsIgnoreCase("no")) {
                                                    System.out.println("Nuovo tentativo");
                                                } else {
                                                    System.out.println("valore non valido");
                                                }
                                            }
                                            while (true) {
                                                System.out.println("Inserisci la tua data di nascita (yyyy-mm-dd)");
                                                dataNascitaNuovoAbbonato = LocalDate.parse(scanner.nextLine());
                                                System.out.println("Confermi che " + dataNascitaNuovoAbbonato + " è corretta? (si o no)");
                                                if (scanner.nextLine().equalsIgnoreCase("si")) break;
                                                else if (scanner.nextLine().equalsIgnoreCase("no")) {
                                                    System.out.println("Nuovo tentativo");
                                                } else {
                                                    System.out.println("valore non valido");
                                                }
                                            }
                                            Tessera nuovaTessera = new Tessera();
                                            tesseraDAO.save(nuovaTessera);
                                            utenteDAO.save(new Utente(nomeNuovoAbbonato, cognomeNuovoAbbonato, dataNascitaNuovoAbbonato, nuovaTessera));

                                        }
                                        case "no" -> {
                                            System.out.println("Vuoi acquistare dei singoli biglietti? (si o no)");
                                            String choiceBiglietti = scanner.nextLine();
                                            if (choiceBiglietti.equalsIgnoreCase("si") || choiceBiglietti.equalsIgnoreCase("no")) {
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
                                                    }
                                                    case "no" -> {
                                                        System.out.println("Ti auguriamo un buon proseguimento.\nArrivederci al prossimo acquisto!");
                                                        System.exit(0);
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    break;
                                } else System.out.println("Valore non valido");
                            }
                        }

                    }
                    break;
                } else System.out.println("Risposta non valida");
            }
        }

    }
}
