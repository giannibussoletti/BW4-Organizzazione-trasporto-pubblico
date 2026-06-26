package gianniScanner;

import DAO.AbbonamentoDAO;
import DAO.PuntoEmissioneDAO;
import entities.Abbonamento;
import entities.PuntoEmissione;
import entities.Tessera;
import enums.TipoAbbonamento;

import java.util.Scanner;

public class CreazioneAbbonamento {

    public static void creazione(PuntoEmissioneDAO puntoEmissioneDAO, AbbonamentoDAO abbonamentoDAO, PuntoEmissione puntoScelto, Tessera tesseraUtente) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Che abbonamento vuoi?" +
                    "\n1) MENSILE" +
                    "\n2) SETTIMANALE");
            int sceltaTipoAbbonamento;
            if (scanner.hasNextInt()) {
                sceltaTipoAbbonamento = Integer.parseInt(scanner.nextLine());
                if (sceltaTipoAbbonamento == 1 || sceltaTipoAbbonamento == 2) {
                    switch (sceltaTipoAbbonamento) {
                        case 1 -> {
                            puntoEmissioneDAO.save(puntoScelto);
                            abbonamentoDAO.save(new Abbonamento(puntoScelto, TipoAbbonamento.MENSILE, tesseraUtente));
                        }
                        case 2 -> {
                            puntoEmissioneDAO.save(puntoScelto);
                            abbonamentoDAO.save(new Abbonamento(puntoScelto, TipoAbbonamento.SETTIMANALE, tesseraUtente));
                        }
                    }
                    System.out.println("Ti auguriamo un piacevole viaggio, alla prossima");
                    System.exit(0);
                } else {
                    System.out.println("Valore non valido");
                }
            } else {
                System.out.println("Valore non valido");
            }
        }
    }
}
