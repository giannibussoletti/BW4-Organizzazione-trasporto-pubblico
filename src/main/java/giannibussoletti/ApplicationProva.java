package giannibussoletti;

import DAO.*;
import entities.*;
import enums.StatoMezzo;
import enums.TipoAbbonamento;
import enums.TipoMezzo;

import gianniScanner.Acquisizione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApplicationProva {

    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bw4traspublicpu");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();


        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PuntoEmissioneDAO puntoEmissioneDAO = new PuntoEmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        SingoloBigliettoDAO bigliettoDAO = new SingoloBigliettoDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        StatoMezzoDAO statoMezzoDAO = new StatoMezzoDAO(em);


        Tessera t1 = new Tessera();
        Tessera t2 = new Tessera();
        Tessera t3 = new Tessera();


        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 12), t1);
        Utente u2 = new Utente("Luca", "Bianchi", LocalDate.of(1985, 3, 20), t2);
        Utente u3 = new Utente("Giulia", "Verdi", LocalDate.of(1998, 11, 2), t3);


        DistributoreAutomatico da1 = new DistributoreAutomatico("Via Roma 10", true);
        DistributoreAutomatico da2 = new DistributoreAutomatico("Via Milano 22", false);
        RivenditoreAutorizzato ra1 = new RivenditoreAutorizzato("Via Verdi 33", "Tabacchi SRL", "Tabaccheria Centrale");

        PuntoEmissione peFromDB1 = puntoEmissioneDAO.findByID("6d96ab5a-eaff-48af-a058-9c9acaefe934");
        PuntoEmissione peFromDB2 = puntoEmissioneDAO.findByID("f0f13036-a21d-4e71-b38a-29ebcbb4a11e");
        PuntoEmissione peFromDB3 = puntoEmissioneDAO.findByID("f971e05d-7fba-4165-a2b5-241a8712870f");

        Mezzo m1 = new Mezzo(TipoMezzo.AUTOBUS, 50, "AB123CD");
        Mezzo m2 = new Mezzo(TipoMezzo.TRAM, 120, "TRM567");
        Mezzo m3 = new Mezzo(TipoMezzo.AUTOBUS, 300, "MTR999");


        Mezzo m1FromDB = mezzoDAO.findById("0d915f20-80d8-4ea8-a60d-4a9997c23bed");
        Mezzo m2FromDB = mezzoDAO.findById("467b2420-5213-49d7-b7ad-3eaa592db9d3");

        StatoDelMezzo sM1 = new StatoDelMezzo(StatoMezzo.ATTIVO, LocalDate.now(), m1FromDB);
        StatoDelMezzo sM2 = new StatoDelMezzo(StatoMezzo.MANUTENZIONE, LocalDate.now(), m2FromDB);


        Tratta tr1 = new Tratta("Modena", "Sassuolo", LocalTime.of(0, 45));
        Tratta tr2 = new Tratta("Carpi", "Modena", LocalTime.of(0, 30));
        Tratta tr3 = new Tratta("Bologna", "Modena", LocalTime.of(1, 0));

        Tratta tr1FromDB = trattaDAO.findById("4d028197-c8b0-4ed0-9d53-074c6dd4268b");
        Tratta tr2FromDB = trattaDAO.findById("6cbd6b4d-8a4f-4a9f-b6d5-753d8b8491e3");
        Tratta tr3FromDB = trattaDAO.findById("e41564ed-ab32-431d-a06b-bdc87bbf27b0");

        Percorrenza p1 = new Percorrenza(LocalTime.of(8, 0), tr3FromDB, m2FromDB);
        Percorrenza p2 = new Percorrenza(LocalTime.of(9, 0), tr3FromDB, m2FromDB);
        Percorrenza p3 = new Percorrenza(LocalTime.of(7, 30), tr3FromDB, m2FromDB);

//        SingoloBiglietto b1 = new SingoloBiglietto(LocalDate.now(), m1, da1);
//        SingoloBiglietto b2 = new SingoloBiglietto(LocalDate.now(), m2, da2);
//        SingoloBiglietto b3 = new SingoloBiglietto(LocalDate.now(), m3, ra1);

        Tessera t1FromDB = tesseraDAO.findByCodiceTessera(7103439486469225740L);
        Tessera t2FromDB = tesseraDAO.findByCodiceTessera(1587270423070119018L);
        Tessera t3FromDB = tesseraDAO.findByCodiceTessera(7211449860680267463L);

        Abbonamento a1 = new Abbonamento(peFromDB1, TipoAbbonamento.MENSILE, t1FromDB);
        Abbonamento a2 = new Abbonamento(peFromDB2, TipoAbbonamento.SETTIMANALE, t2FromDB);
        Abbonamento a3 = new Abbonamento(peFromDB3, TipoAbbonamento.MENSILE, t3FromDB);


//        percorrenzaDAO.percorrenzeAttive().forEach(System.out::println);
//        percorrenzaDAO.numPercorrenzeTratta("4d028197-c8b0-4ed0-9d53-074c6dd4268b");
//        abbonamentoDAO.rinnovoAbbonamento(1821095931, TipoAbbonamento.MENSILE);
//        abbonamentoDAO.scadenzaAbbonamento(1821095931);
//        percorrenzaDAO.differenzaTempoPercorrenza("4d028197-c8b0-4ed0-9d53-074c6dd4268b");
        Acquisizione.BigliettiEAbbonamenti(tesseraDAO, utenteDAO, bigliettoDAO, trattaDAO, percorrenzaDAO, puntoEmissioneDAO, abbonamentoDAO);

//        statoMezzoDAO.save(sM1);
//        statoMezzoDAO.save(sM2);
//        trattaDAO.save(tr1);
//        trattaDAO.save(tr2);
//        trattaDAO.save(tr3);
//        percorrenzaDAO.save(p1);
//        percorrenzaDAO.save(p2);
//        percorrenzaDAO.save(p3);
//        bigliettoDAO.save(b1);
//        bigliettoDAO.save(b2);
//        bigliettoDAO.save(b3);
//        abbonamentoDAO.save(a1);
//        abbonamentoDAO.save(a2);
//        abbonamentoDAO.save(a3);
//        tesseraDAO.save(t1);
//        tesseraDAO.save(t2);
//        tesseraDAO.save(t3);
//        utenteDAO.save(u1);
//        utenteDAO.save(u2);
//        utenteDAO.save(u3);
//        puntoEmissioneDAO.save(da1);
//        puntoEmissioneDAO.save(da2);
//        puntoEmissioneDAO.save(ra1);
//        mezzoDAO.save(m1);
//        mezzoDAO.save(m2);
//        mezzoDAO.save(m3);
//        System.out.println("salvato");


    }
}

