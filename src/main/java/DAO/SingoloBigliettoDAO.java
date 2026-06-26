package DAO;

import entities.SingoloBiglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.UUID;

public class SingoloBigliettoDAO {
    public final EntityManager entityManager;

    public SingoloBigliettoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(SingoloBiglietto newBiglietto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newBiglietto);
        transaction.commit();
        System.out.println(newBiglietto + "biglietto creato con successo!");
    }

    public SingoloBiglietto findByCodiceUnico(UUID codiceUnivoco) {
        TypedQuery<SingoloBiglietto> query = entityManager.createQuery(
                "SELECT b FROM SingoloBiglietto b WHERE b.codiceUnico = :codice",
                SingoloBiglietto.class
        );
        query.setParameter("codice", codiceUnivoco);
        return query.getSingleResult();
    }

    public SingoloBiglietto findById(String id) {
        return entityManager.find(SingoloBiglietto.class, UUID.fromString(id));
    }

    public void delete(UUID codiceUnivoco) {
        SingoloBiglietto found = findByCodiceUnico(codiceUnivoco);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();
    }

    public void acquistaBiglietto(entities.Mezzo mezzo, entities.PuntoEmissione punto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            SingoloBiglietto nuovo = new SingoloBiglietto(mezzo, punto);
            entityManager.persist(nuovo);
            transaction.commit();
            System.out.println("Biglietto acquistato con successo! ID: " + nuovo.getIdBiglietto());
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.err.println("Errore durante l'acquisto del biglietto: " + e.getMessage());
        }
    }

    public long getBigliettiVidimatiPerMezzoInPeriodo(entities.Mezzo mezzo, java.time.LocalDate inizio, java.time.LocalDate fine) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b WHERE b.id_mezzo = :mezzo AND b.dataVidimazione BETWEEN :inizio AND :fine",
                Long.class
        );
        query.setParameter("mezzo", mezzo);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getSingleResult();
    }

    public long getBigliettiVidimatiTotaliInPeriodo(java.time.LocalDate inizio, java.time.LocalDate fine) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b WHERE b.dataVidimazione BETWEEN :inizio AND :fine",
                Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getSingleResult();
    }

    public void vidima(UUID idBiglietto) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        SingoloBiglietto b = entityManager.find(SingoloBiglietto.class, idBiglietto);

        if (b == null) {
            System.out.println("Biglietto non trovato");
            t.rollback();
            return;
        }

        if (b.getDataVidimazione() != null) {
            System.out.println("il biglietto è già stato vidimato il " + b.getDataVidimazione());
            t.rollback();
            return;
        }

        b.setDataVidimazione(LocalDateTime.now());
        entityManager.merge(b);
        t.commit();
        System.out.println("biglietto vidimato correttamente alle " + b.getDataVidimazione());
    }

    public long countAll() {
        TypedQuery<Long> q = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b",
                Long.class
        );
        return q.getSingleResult();
    }

    public long countByPunto(String idPunto) {
        TypedQuery<Long> q = entityManager.createQuery(
                "SELECT COUNT(b) FROM SingoloBiglietto b WHERE b.id_punto_emissione.id = :id",
                Long.class
        );
        q.setParameter("id", UUID.fromString(idPunto));
        return q.getSingleResult();
    }
}
