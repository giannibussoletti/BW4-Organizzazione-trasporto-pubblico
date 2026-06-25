package giannibussoletti.Prova_method;

import DAO.TesseraDAO;
import DAO.UtenteDAO;
import entities.Tessera;
import entities.Utente;
import giannibussoletti.ApplicationProva;
import jakarta.persistence.EntityManager;

public class TesseraMeth {
    public static void main(String[] args) {

        EntityManager em = ApplicationProva.emf.createEntityManager();

        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);

        //   2. findById
        Tessera tById = tesseraDAO.findById("35e67b0c-30b3-405b-bb52-26f24591f987");
        System.out.println("\ntessera " + tById);

        // 2. findByCodiceTessera
        Tessera tByCodice = tesseraDAO.findByCodiceTessera(7847064255687516881L);
        System.out.println("\nfindByCodiceTessera " + tByCodice);
        // 3. findByUtente
        Tessera tByUtente = tesseraDAO.findByUtente("17d9b3e2-abb2-41d8-851d-352d64cfad65");
        System.out.println("\nfindByUtente " + tByUtente);

        // 4. isAbbonamentoValido
        boolean valido = tesseraDAO.isAbbonamentoValido(8600756319471574059L);
        System.out.println("isAbbonamentoValido\n " + valido);

        // 5. rinnova
//        tesseraDAO.rinnova(8600756319471574059L);

        Utente uConTessera = utenteDAO.findById("17d9b3e2-abb2-41d8-851d-352d64cfad65");
        tesseraDAO.creaTesseraSeNonEsiste(uConTessera);
    }}
