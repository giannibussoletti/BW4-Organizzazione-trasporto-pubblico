package DAO;

import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente nuovoUtente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(nuovoUtente);
        transaction.commit();
        System.out.println("L'utente " + nuovoUtente + " è stato salvato correttamente");
    }}