package DAO;

import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {

    private final EntityManager entityManager;

    public TrattaDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Tratta newTratta) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newTratta);

        transaction.commit();

        System.out.println("Tratta creata correttamente");
    }
}