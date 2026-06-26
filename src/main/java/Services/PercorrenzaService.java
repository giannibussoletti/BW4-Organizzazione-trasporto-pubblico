package Services;
import DAO.PercorrenzaDAO;
import entities.Mezzo;
import entities.Percorrenza;
import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalTime;
import java.util.UUID;


public class PercorrenzaService {
    private final PercorrenzaDAO percorrenzaDAO;
    private final EntityManager entityManager;

    public PercorrenzaService(EntityManager em) {
        this.entityManager = em;
        this.percorrenzaDAO = new PercorrenzaDAO(em);
    }


    public long contaPercorrenzeMezzoSuTratta(String idMezzo, String idTratta) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p " +
                        "WHERE p.mezzo.id = :idMezzo AND p.tratta.id = :idTratta",
                Long.class
        );

        query.setParameter("idMezzo", UUID.fromString(idMezzo));
        query.setParameter("idTratta", UUID.fromString(idTratta));

        return query.getSingleResult();
    }


    public void iniziaPercorrenza(Tratta tratta, Mezzo mezzo) {
        Percorrenza nuovaPercorrenza = new Percorrenza(LocalTime.now(), tratta, mezzo);
        percorrenzaDAO.save(nuovaPercorrenza);
    }

    public void terminaPercorrenza(Percorrenza percorrenza) {
        this.entityManager.getTransaction().begin();
        percorrenza.setOraArrivo(LocalTime.now());
        this.entityManager.getTransaction().commit();
        System.out.println("Percorrenza completata alle ore: " + percorrenza.getOraArrivo());
    }
}
