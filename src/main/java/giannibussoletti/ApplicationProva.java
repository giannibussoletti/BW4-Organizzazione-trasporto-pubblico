package giannibussoletti;

import DAO.*;
import entities.*;
import enums.TipoAbbonamento;
import enums.TipoMezzo;

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




        Tessera t1 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1));
        Tessera t2 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1));
        Tessera t3 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1));

        tesseraDAO.save(t1);
        tesseraDAO.save(t2);
        tesseraDAO.save(t3);


        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 12), t1);
        Utente u2 = new Utente("Luca", "Bianchi", LocalDate.of(1985, 3, 20), t2);
        Utente u3 = new Utente("Giulia", "Verdi", LocalDate.of(1998, 11, 2), t3);

        utenteDAO.save(u1);
        utenteDAO.save(u2);
        utenteDAO.save(u3);



        DistributoreAutomatico da1 = new DistributoreAutomatico("Via Roma 10", true);
        DistributoreAutomatico da2 = new DistributoreAutomatico("Via Milano 22", false);
        RivenditoreAutorizzato ra1 = new RivenditoreAutorizzato("Via Verdi 33", "Tabacchi SRL", "Tabaccheria Centrale");
        puntoEmissioneDAO.save(da1);
        puntoEmissioneDAO.save(da2);
        puntoEmissioneDAO.save(ra1);


        Mezzo m1 = new Mezzo(TipoMezzo.AUTOBUS, 50, "AB123CD");
        Mezzo m2 = new Mezzo(TipoMezzo.TRAM, 120, "TRM567");
        Mezzo m3 = new Mezzo(TipoMezzo.AUTOBUS, 300, "MTR999");
        mezzoDAO.save(m1);
        mezzoDAO.save(m2);
        mezzoDAO.save(m3);


        Tratta tr1 = new Tratta("Modena", "Sassuolo", LocalTime.of(0, 45));
        Tratta tr2 = new Tratta("Carpi", "Modena", LocalTime.of(0, 30));
        Tratta tr3 = new Tratta("Bologna", "Modena", LocalTime.of(1, 0));
        trattaDAO.save(tr1);
        trattaDAO.save(tr2);
        trattaDAO.save(tr3);

        Percorrenza p1 = new Percorrenza(LocalTime.of(8, 0), LocalTime.of(8, 45), tr1, m1);
        Percorrenza p2 = new Percorrenza(LocalTime.of(9, 0), LocalTime.of(9, 30), tr2, m2);
        Percorrenza p3 = new Percorrenza(LocalTime.of(7, 30), LocalTime.of(8, 30), tr3, m3);
        percorrenzaDAO.save(p1);
        percorrenzaDAO.save(p2);
        percorrenzaDAO.save(p3);


        SingoloBiglietto b1 = new SingoloBiglietto(LocalDate.now(), null, m1, da1);
        SingoloBiglietto b2 = new SingoloBiglietto(LocalDate.now(), LocalDate.now(), m2, da2);
        SingoloBiglietto b3 = new SingoloBiglietto(LocalDate.now(), null, m3, ra1);
        bigliettoDAO.save(b1);
        bigliettoDAO.save(b2);
        bigliettoDAO.save(b3);

        Abbonamento a1 = new Abbonamento(ra1, TipoAbbonamento.MENSILE, t1, LocalDate.now());
        Abbonamento a2 = new Abbonamento(da1, TipoAbbonamento.SETTIMANALE, t2, LocalDate.now());
        Abbonamento a3 = new Abbonamento(da2, TipoAbbonamento.MENSILE, t3, LocalDate.now());

        abbonamentoDAO.save(a1);
        abbonamentoDAO.save(a2);
        abbonamentoDAO.save(a3);
        System.out.println("salvato");
    }
}

