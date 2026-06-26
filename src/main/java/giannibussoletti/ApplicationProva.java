package giannibussoletti;

import DAO.*;
import entities.*;
import enums.StatoMezzo;
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
        StatoMezzoDAO statoMezzoDAO = new StatoMezzoDAO(em);


        Tessera t1 = new Tessera();
        Tessera t2 = new Tessera();
        Tessera t3 = new Tessera();
        Tessera t4 = new Tessera();
        Tessera t5 = new Tessera();

        tesseraDAO.save(t1);
        tesseraDAO.save(t2);
        tesseraDAO.save(t3);
        tesseraDAO.save(t4);
        tesseraDAO.save(t5);

        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 12), t1);
        Utente u2 = new Utente("Luca", "Bianchi", LocalDate.of(1985, 3, 20), t2);
        Utente u3 = new Utente("Giulia", "Verdi", LocalDate.of(1998, 11, 2), t3);
        Utente u4 = new Utente("Sara", "Neri", LocalDate.of(2000, 1, 15), t4);
        Utente u5 = new Utente("Paolo", "Gialli", LocalDate.of(1975, 7, 8), t5);

        utenteDAO.save(u1);
        utenteDAO.save(u2);
        utenteDAO.save(u3);
        utenteDAO.save(u4);
        utenteDAO.save(u5);


        PuntoEmissione pe1 = new DistributoreAutomatico("Via Roma 10", true);
        PuntoEmissione pe2 = new DistributoreAutomatico("Via Milano 22", true);
        PuntoEmissione pe3 = new RivenditoreAutorizzato("Via Verdi 33", "Tabacchi SRL", "Tabaccheria Centrale");
        PuntoEmissione pe4 = new RivenditoreAutorizzato("Via Emilia 100", "Edicola 24", "Edicola Centrale");
        PuntoEmissione pe5 = new DistributoreAutomatico("Stazione FS", true);

        puntoEmissioneDAO.save(pe1);
        puntoEmissioneDAO.save(pe2);
        puntoEmissioneDAO.save(pe3);
        puntoEmissioneDAO.save(pe4);
        puntoEmissioneDAO.save(pe5);


        Mezzo m1 = new Mezzo(TipoMezzo.AUTOBUS, 50, "AB123CD");
        Mezzo m2 = new Mezzo(TipoMezzo.TRAM, 120, "TRM567");
        Mezzo m3 = new Mezzo(TipoMezzo.TRAM, 300, "MTR999");
        Mezzo m4 = new Mezzo(TipoMezzo.AUTOBUS, 80, "AB888EF");
        Mezzo m5 = new Mezzo(TipoMezzo.TRAM, 100, "TRM222");

        mezzoDAO.save(m1);
        mezzoDAO.save(m2);
        mezzoDAO.save(m3);
        mezzoDAO.save(m4);
        mezzoDAO.save(m5);


        Tratta tr1 = new Tratta("Modena", "Sassuolo", LocalTime.of(0, 45));
        Tratta tr2 = new Tratta("Carpi", "Modena", LocalTime.of(0, 30));
        Tratta tr3 = new Tratta("Bologna", "Modena", LocalTime.of(1, 0));
        Tratta tr4 = new Tratta("Reggio Emilia", "Modena", LocalTime.of(0, 40));
        Tratta tr5 = new Tratta("Mirandola", "Modena", LocalTime.of(1, 10));

        trattaDAO.save(tr1);
        trattaDAO.save(tr2);
        trattaDAO.save(tr3);
        trattaDAO.save(tr4);
        trattaDAO.save(tr5);


        Percorrenza p1 = new Percorrenza(LocalTime.of(8, 0), tr1, m1);
        Percorrenza p2 = new Percorrenza(LocalTime.of(9, 0), tr2, m2);
        Percorrenza p3 = new Percorrenza(LocalTime.of(7, 30), tr3, m3);
        Percorrenza p4 = new Percorrenza(LocalTime.of(10, 15), tr4, m4);
        Percorrenza p5 = new Percorrenza(LocalTime.of(11, 45), tr5, m5);

        percorrenzaDAO.save(p1);
        percorrenzaDAO.save(p2);
        percorrenzaDAO.save(p3);
        percorrenzaDAO.save(p4);
        percorrenzaDAO.save(p5);


        SingoloBiglietto b1 = new SingoloBiglietto(m1, pe1);
        SingoloBiglietto b2 = new SingoloBiglietto(m2, pe2);
        SingoloBiglietto b3 = new SingoloBiglietto(m3, pe3);
        SingoloBiglietto b4 = new SingoloBiglietto(m4, pe4);
        SingoloBiglietto b5 = new SingoloBiglietto(m5, pe5);

        bigliettoDAO.save(b1);
        bigliettoDAO.save(b2);
        bigliettoDAO.save(b3);
        bigliettoDAO.save(b4);
        bigliettoDAO.save(b5);


        StatoDelMezzo sm1 = new StatoDelMezzo(StatoMezzo.ATTIVO, LocalDate.now(), m1);
        StatoDelMezzo sm2 = new StatoDelMezzo(StatoMezzo.MANUTENZIONE, LocalDate.now(), m2);
        StatoDelMezzo sm3 = new StatoDelMezzo(StatoMezzo.ATTIVO, LocalDate.now(), m3);
        StatoDelMezzo sm4 = new StatoDelMezzo(StatoMezzo.ATTIVO, LocalDate.now(), m4);
        StatoDelMezzo sm5 = new StatoDelMezzo(StatoMezzo.MANUTENZIONE, LocalDate.now(), m5);

        statoMezzoDAO.save(sm1);
        statoMezzoDAO.save(sm2);
        statoMezzoDAO.save(sm3);
        statoMezzoDAO.save(sm4);
        statoMezzoDAO.save(sm5);

        System.out.println("DATABASE CREATO CON SUCCESSO (5 DATI PER TABELLA)");
    }
}
