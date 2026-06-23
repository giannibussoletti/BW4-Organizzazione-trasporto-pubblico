package DAO;

import entities.Abbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AbbonamentoDAO {

    private final EntityManager entityManager;

    public AbbonamentoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Abbonamento newAbbonamento) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newAbbonamento);

        transaction.commit();

        System.out.println("Nuovo Abbonamento salvato correttamente");
    }
}
