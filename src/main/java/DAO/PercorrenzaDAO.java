package DAO;

import entities.Percorrenza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PercorrenzaDAO {

    private final EntityManager entityManager;

    public PercorrenzaDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Percorrenza newPercorrenza) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newPercorrenza);

        transaction.commit();

        System.out.println("Percorrenza creata correttamente");
    }

    public List<Percorrenza> percorrenzeAttive() {
        TypedQuery<Percorrenza> query = entityManager.createQuery("SELECT p FROM Percorrenza p JOIN FETCH p.mezzo JOIN FETCH p.tratta WHERE p.oraArrivo IS NULL", Percorrenza.class);
        
        return query.getResultList();

    }
}
