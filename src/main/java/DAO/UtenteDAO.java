package DAO;

import entities.Utente;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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
        System.out.println("L'utente " + nuovoUtente + " e stato salvato correttamente");
    }
    public Utente findById(String id) {
        Utente found = em.find(Utente.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }
    public void delete(String id) {
        Utente found = findById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("utente con id " + id + " e stato eliminato correttamente");
    }
    public Utente findByTesserato(String tesserato) {
        Utente found = em.createQuery(
                        "SELECT u FROM Utente u WHERE u.tesserato = :tesserato", Utente.class)
                .setParameter("tesserato", tesserato)
                .getSingleResult();
        if (found == null) throw new NotFoundException(tesserato);
        return found;
    }
}