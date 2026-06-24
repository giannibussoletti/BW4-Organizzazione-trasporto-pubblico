package DAO;

import entities.PuntoEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.UUID;

public class PuntoEmissioneDAO {

    private final EntityManager entityManager;

    public PuntoEmissioneDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(PuntoEmissione newPuntoEmissione) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newPuntoEmissione);

        transaction.commit();

        System.out.println("Punto di emissione creato correttamente");
    }

    public PuntoEmissione findByID(String id) {
        TypedQuery<PuntoEmissione> query = entityManager.createQuery("SELECT p FROM PuntoEmissione p WHERE p.id = :id", PuntoEmissione.class);
        query.setParameter("id", UUID.fromString(id));
        return query.getSingleResult();
    }
}
