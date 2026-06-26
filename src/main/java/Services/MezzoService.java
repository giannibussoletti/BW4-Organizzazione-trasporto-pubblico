package Services;

import DAO.MezzoDAO;
import entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.UUID;

public class MezzoService {

    private final MezzoDAO mezzoDAO;
    private final EntityManager entityManager;

    public MezzoService(EntityManager em) {
        this.entityManager = em;
        this.mezzoDAO = new MezzoDAO(em);
    }

    public Mezzo ottieniPeriodiStatoMezzo(String idMezzo) {
        try {
            TypedQuery<Mezzo> query = entityManager.createQuery(
                    "SELECT m FROM Mezzo m " +
                            "LEFT JOIN FETCH m.periodiStato " +
                            "WHERE m.id = :id",
                    Mezzo.class
            );

            query.setParameter("id", UUID.fromString(idMezzo));

            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            System.err.println("Nessun mezzo trovato con l'ID: " + idMezzo);
            return null;
        }
    }
}