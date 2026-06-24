package DAO;

import entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.UUID;

public class MezzoDAO {
    private final EntityManager entityManager;

    public MezzoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Mezzo newMezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newMezzo);

        transaction.commit();

        System.out.println(newMezzo + "creato con successo!");
    }

    public Mezzo findById(UUID id) {

        TypedQuery<Mezzo> query = entityManager.createQuery(
                "SELECT m FROM Mezzo m WHERE m.id = :id",
                Mezzo.class
        );

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    //Chiedere si e meglio cambiare id per targa
    public void deleteById(UUID id) {

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Mezzo found = findById(id);

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Mezzo eliminato con successo!");
    }
//    public void updateMezzo(
//            UUID id,
//            StatoMezzo tipoMezzo,
//            int capienza,
//            StatoMezzo statoMezzo
//    ) {
//
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        transaction.begin();
//
//        Mezzo found = findById(id);
//
//        found.setType(tipoMezzo);
//        found.setCapienza(capienza);
//
//        transaction.commit();
//
//        System.out.println("Mezzo aggiornato con successo!");
//    }
}

