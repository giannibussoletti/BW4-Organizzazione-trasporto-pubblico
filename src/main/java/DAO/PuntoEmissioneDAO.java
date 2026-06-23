package DAO;

import entities.PuntoEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public ab class PuntoEmissioneDAO {

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
}
