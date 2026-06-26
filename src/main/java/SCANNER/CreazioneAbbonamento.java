package SCANNER;

import DAO.AbbonamentoDAO;
import DAO.PuntoEmissioneDAO;
import entities.Abbonamento;
import entities.PuntoEmissione;
import entities.Tessera;
import enums.TipoAbbonamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreazioneAbbonamento {

    public static void creazione(PuntoEmissioneDAO puntoEmissioneDAO, AbbonamentoDAO abbonamentoDAO,
                                 PuntoEmissione puntoScelto, Tessera tesseraUtente) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nChe abbonamento vuoi?" +
                        "\n1) MENSILE" +
                        "\n2) SETTIMANALE" +
                        "\n0) Annulla");

                System.out.print("Scelta: ");
                String input = scanner.nextLine().trim();


                if (input.isEmpty()) {
                    System.out.println("Inserisci un numero.");
                    continue;
                }

                if (!input.matches("\\d+")) {
                    System.out.println("Devi inserire un numero valido.");
                    continue;
                }

                int scelta = Integer.parseInt(input);

                switch (scelta) {

                    case 1 -> {
                        puntoEmissioneDAO.save(puntoScelto);
                        abbonamentoDAO.save(new Abbonamento(puntoScelto, TipoAbbonamento.MENSILE, tesseraUtente));
                        System.out.println("Abbonamento MENSILE creato con successo!");
                        return;
                    }

                    case 2 -> {
                        puntoEmissioneDAO.save(puntoScelto);
                        abbonamentoDAO.save(new Abbonamento(puntoScelto, TipoAbbonamento.SETTIMANALE, tesseraUtente));
                        System.out.println("Abbonamento SETTIMANALE creato con successo!");
                        return;
                    }

                    case 0 -> {
                        System.out.println("Operazione annullata.");
                        return;
                    }

                    default -> System.out.println("Scelta non valida. Riprova.");
                }

            } catch (InputMismatchException e) {
                System.out.println("devi inserire un numero.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(" Errore imprevisto: " + e.getMessage());
            }
        }
    }
}
