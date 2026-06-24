package DAO;

import entities.Tratta;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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

    public Tratta findById(String id) {
        Tratta found = entityManager.find(Tratta.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

}