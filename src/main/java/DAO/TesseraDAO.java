package DAO;

import entities.Tessera;
import entities.Utente;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        System.out.println("La tessera " + nuovaTessera + " e stata salvata correttamente");
    }
    public Tessera findById(String id) {
        Tessera found = em.find(Tessera.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }
    public Tessera findByCodiceTessera(String codiceTessera) {
        Tessera found = em.createQuery(
                        "SELECT t FROM Tessera t WHERE t.codiceTessera = :codice", Tessera.class)
                .setParameter("codice", codiceTessera)
                .getSingleResult();
        if (found == null) throw new NotFoundException(codiceTessera);
        return found;
    }
    public Tessera findByUtente(String idUtente) {
        Tessera found = em.createQuery(
                        "SELECT t FROM Tessera t WHERE t.utente.id = :idUtente", Tessera.class)
                .setParameter("idUtente", UUID.fromString(idUtente))
                .getSingleResult();
        if (found == null) throw new NotFoundException(idUtente);
        return found;
    }
    public boolean isAbbonamentoValido(String codiceTessera) {
        Tessera tessera = findByCodiceTessera(codiceTessera);
        LocalDate oggi = LocalDate.now();
        return !tessera.getDataDiRinnovo().isBefore(oggi);
    }
    public void delete(String id) {
        Tessera found = findById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("La tessera con id " + id + " e stata eliminata correttamente");
    }
    public void rinnova(String codiceTessera) {
        Tessera tessera = findByCodiceTessera(codiceTessera);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        tessera.setDataDiRinnovo(LocalDate.now().plusYears(1));
        em.merge(tessera);
        transaction.commit();
        System.out.println("La tessera " + codiceTessera + " e stata rinnovata fino al " + tessera.getDataDiRinnovo());
    }
    }
