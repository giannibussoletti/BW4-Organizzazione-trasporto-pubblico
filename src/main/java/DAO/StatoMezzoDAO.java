package DAO;

import entities.StatoDelMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.UUID;

public class StatoMezzoDAO {
    private final EntityManager entityManager;

    public StatoMezzoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(StatoDelMezzo newStato) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newStato);
        transaction.commit();
        System.out.println(newStato + "creato con successo!");
    }

    public StatoDelMezzo findById(String id) {

        TypedQuery<StatoDelMezzo> query = entityManager.createQuery(
                "SELECT m FROM MezzoDiTrasporto m WHERE m.id = :id", StatoDelMezzo.class);
        query.setParameter("id", UUID.fromString(id));

        return query.getSingleResult();
    }
    
}
