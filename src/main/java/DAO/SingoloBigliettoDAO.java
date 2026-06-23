package DAO;


import entities.SingoloBiglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.UUID;
//Paolo
public class SingoloBigliettoDAO {
    public final EntityManager entityManager;

    public SingoloBigliettoDAO(EntityManager em){
        this.entityManager = em;
    }

    //===== Metodo SAVE ====//
    public void save(SingoloBiglietto newBiglietto){
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newBiglietto);

        transaction.commit();

        System.out.println(newBiglietto + "biglietto creato con successo!");
    }


    //===== Metodo findByID ====//
    public SingoloBiglietto findByCodiceUnico(UUID codiceUnivoco) {

        TypedQuery<SingoloBiglietto> query = entityManager.createQuery(
                "SELECT t FROM TitoloViaggio t WHERE t.codiceUnivoco = :codice",
                SingoloBiglietto.class
        );

        query.setParameter("codice", codiceUnivoco);

        return query.getSingleResult();
    }

    public void delete(UUID codiceUnivoco) {
        SingoloBiglietto found = findByCodiceUnico(codiceUnivoco);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();
    }




}
