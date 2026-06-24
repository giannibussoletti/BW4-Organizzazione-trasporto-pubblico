package DAO;


import entities.SingoloBiglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.UUID;
//Paolo
public class SingoloBigliettoDAO {
    public final EntityManager entityManager;

    public SingoloBigliettoDAO(EntityManager em){
        this.entityManager = em;
    }

    //===== Metodo SAVE ====//
    public void save(SingoloBiglietto newBiglietto){
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newBiglietto);

        transaction.commit();

        System.out.println(newBiglietto + "biglietto creato con successo!");
    }


    //===== Metodo findByID ====//
    public SingoloBiglietto findByCodiceUnico(UUID codiceUnivoco) {

        TypedQuery<SingoloBiglietto> query = entityManager.createQuery(
                "SELECT t FROM TitoloViaggio t WHERE t.codiceUnivoco = :codice",
                SingoloBiglietto.class
        );

        query.setParameter("codice", codiceUnivoco);

        return query.getSingleResult();
    }

    public void delete(UUID codiceUnivoco) {
        SingoloBiglietto found = findByCodiceUnico(codiceUnivoco);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();
    }

    // ===== 1. METODO DI ACQUISTO ====
    public void acquistaBiglietto(entities.Mezzo mezzo, entities.PuntoEmissione punto) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();

            SingoloBiglietto nuevoBiglietto = new SingoloBiglietto(java.time.LocalDate.now(), mezzo, punto);

            this.entityManager.persist(nuevoBiglietto);

            transaction.commit();
            System.out.println("Biglietto acquistato con successo! ID: " + nuevoBiglietto.getIdBiglietto());
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.err.println("Errore durante l'acquisto del biglietto: " + e.getMessage());
        }
    }

    // ===== 2. BIGLIETTI VIDIMATI PER UN SINGOLO MEZZO IN UN PERIODO ====
    public long getBigliettiVidimatiPerMezzoInPeriodo(entities.Mezzo mezzo, java.time.LocalDate inizio, java.time.LocalDate fine) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b " +
                        "WHERE b.id_mezzo = :mezzo " +
                        "AND b.dataVidimazione BETWEEN :inizio AND :fine",
                Long.class
        );

        query.setParameter("mezzo", mezzo);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getSingleResult();
    }

    // ===== 3. BIGLIETTI VIDIMATI IN TOTALE IN UN PERIODO ====
    public long getBigliettiVidimatiTotaliInPeriodo(java.time.LocalDate inizio, java.time.LocalDate fine) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b " +
                        "WHERE b.dataVidimazione BETWEEN :inizio AND :fine",
                Long.class
        );

        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getSingleResult();
    }

}
