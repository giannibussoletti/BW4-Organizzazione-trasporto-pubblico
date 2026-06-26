package DAO;

import entities.Abbonamento;
import entities.PuntoEmissione;
import entities.Tessera;
import enums.TipoAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.UUID;

public class AbbonamentoDAO {

    private final EntityManager entityManager;

    public AbbonamentoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Abbonamento newAbbonamento) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newAbbonamento);
        transaction.commit();
        System.out.println("Nuovo Abbonamento salvato correttamente");
    }

    public boolean isTesseraValida(String id) {
        Abbonamento a = entityManager.find(Abbonamento.class, UUID.fromString(id));
        if (a == null) return false;
        return a.getDataScadenza().isAfter(LocalDate.now());
    }

    public void rinnovoAbbonamento(int codiceUnico, TipoAbbonamento tipoAbbonamento) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery(
                "UPDATE Abbonamento a SET a.dataEmissione = :dataEmissione, a.dataScadenza = :dataScadenza, a.tipoAbbonamento = :tipoAbbonamento WHERE a.codiceUnico = :codiceUnico"
        );
        query.setParameter("tipoAbbonamento", tipoAbbonamento);
        query.setParameter("dataEmissione", LocalDate.now());
        if (tipoAbbonamento == TipoAbbonamento.SETTIMANALE)
            query.setParameter("dataScadenza", LocalDate.now().plusDays(7));
        else
            query.setParameter("dataScadenza", LocalDate.now().plusMonths(1));
        query.setParameter("codiceUnico", codiceUnico);
        query.executeUpdate();
        transaction.commit();
        System.out.println("Abbonamento Aggiornato con successo");
    }

    public void scadenzaAbbonamento(int codiceUnico) {
        TypedQuery<Abbonamento> query = entityManager.createQuery("SELECT a FROM Abbonamento a WHERE a.codiceUnico = :codiceUnico", Abbonamento.class);
        query.setParameter("codiceUnico", codiceUnico);
        Abbonamento abbonamento = query.getSingleResult();
        if (abbonamento.getDataScadenza().isAfter(LocalDate.now()))
            System.out.println("Il suo abbonamento è ancora valido");
        else System.out.println("Il suo abbonamento è scaduto");


    }

    public boolean isAbbonamentoValidoByCodiceTessera(long codiceTessera) {
        TypedQuery<Abbonamento> query = entityManager.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.tessera.codiceTessera = :codice ORDER BY a.dataScadenza DESC",
                Abbonamento.class
        );
        query.setParameter("codice", codiceTessera);
        query.setMaxResults(1);
        Abbonamento abbonamento = query.getResultStream().findFirst().orElse(null);
        if (abbonamento == null) return false;
        return !abbonamento.getDataScadenza().isBefore(LocalDate.now());
    }

    public Abbonamento creaAbbonamento(PuntoEmissione punto, TipoAbbonamento tipo, Tessera tessera) {
        Abbonamento nuovo = new Abbonamento(punto, tipo, tessera);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(nuovo);
        transaction.commit();
        System.out.println("Nuovo abbonamento creato. Codice " + nuovo.getCodiceUnico());
        return nuovo;
    }


}
