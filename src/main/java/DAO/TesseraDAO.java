package DAO;

import entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class TesseraDAO {

    private final EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera nuovaTessera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(nuovaTessera);
        transaction.commit();
        System.out.println("La tessera " + nuovaTessera + " è stata salvata correttamente");
    }}