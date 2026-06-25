package DAO;

import entities.Abbonamento;
import enums.TipoAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;

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

    public void rinnovoAbbonamento(int codiceUnico, TipoAbbonamento tipoAbbonamento) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("UPDATE Abbonamento a SET a.dataEmissione = :dataEmissione, a.dataScadenza = :dataScadenza, a.tipoAbbonamento = :tipoAbbonamento WHERE a.codiceUnico = :codiceUnico");
        query.setParameter("tipoAbbonamento", tipoAbbonamento);
        query.setParameter("dataEmissione", LocalDate.now());
        if (tipoAbbonamento == TipoAbbonamento.SETTIMANALE) {
            query.setParameter("dataScadenza", LocalDate.now().plusDays(7));
        } else {
            query.setParameter("dataScadenza", LocalDate.now().plusMonths(1));
        }
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
                "SELECT a FROM Abbonamento a " +
                        "WHERE a.tessera.codiceTessera = :codice " +
                        "ORDER BY a.dataScadenza DESC",
                Abbonamento.class
        );
        query.setParameter("codice", codiceTessera);
        Abbonamento abbonamento = null;
        try {
            abbonamento = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("nessun abbonamento trovato per la tessera " + codiceTessera);
            return false;
        }
        boolean valido = !abbonamento.getDataScadenza().isBefore(LocalDate.now());
        if (valido)
            System.out.println("abbonamento e valido fino al " + abbonamento.getDataScadenza());
        else
            System.out.println("abbonamento e scaduto il " + abbonamento.getDataScadenza());

        return valido;
    }


}
