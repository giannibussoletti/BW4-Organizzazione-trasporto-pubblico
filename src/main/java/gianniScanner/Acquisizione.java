package gianniScanner;


import DAO.TesseraDAO;
import DAO.UtenteDAO;
import entities.Tessera;
import entities.Utente;

import java.time.LocalDate;
import java.util.Scanner;

public class Acquisizione {


    public static void BigliettiEAbbonamenti(TesseraDAO tesseraDAO, UtenteDAO utenteDAO) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("benvenuto");
            while (true) {
                System.out.println("Da dove stai acquistando?\n1)Distributore automatico\n2) Rivenditore autorizzato");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice == 1 || choice == 2) {
                        switch (choice) {
                            case 1 -> System.out.println("distributore automatico");
                            case 2 -> System.out.println("rivenditore autorizzato");
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
                                        case "no" -> System.out.println("Acquisto biglietti");
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
