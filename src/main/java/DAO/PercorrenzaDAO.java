package DAO;

import entities.Percorrenza;
import entities.Tratta;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PercorrenzaDAO {

    private final EntityManager entityManager;

    public PercorrenzaDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Percorrenza newPercorrenza) {
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newPercorrenza);

        transaction.commit();

        System.out.println("Percorrenza creata correttamente");
    }
    public Percorrenza findById(String id) {
        Percorrenza found = entityManager.find(Percorrenza.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public List<Percorrenza> percorrenzeAttive() {
        TypedQuery<Percorrenza> query = entityManager.createQuery("SELECT p FROM Percorrenza p JOIN FETCH p.mezzo JOIN FETCH p.tratta WHERE p.oraArrivo IS NULL", Percorrenza.class);

        return query.getResultList();

    }

    public void numPercorrenzeTratta(String id_tratta) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM Percorrenza p WHERE p.tratta.id = :id_tratta", Long.class);
        query.setParameter("id_tratta", UUID.fromString(id_tratta));
        System.out.println(query.getSingleResult());
    }

    public void differenzaTempoPercorrenza(String id_tratta) {
        TypedQuery<Percorrenza> query = entityManager.createQuery("SELECT p FROM Percorrenza p JOIN FETCH p.tratta t WHERE t.id = :id_tratta AND p.oraArrivo IS NOT NULL", Percorrenza.class);
        query.setParameter("id_tratta", UUID.fromString(id_tratta));
        List<Long> mediaDifferenzaPercorrenza = new ArrayList<>();
        long differenzaTotale = 0;
        for (Percorrenza p : query.getResultList()) {
            long minutiTratta = p.getTratta().getTempoPercorrenza().getMinute();

            long oreTratta = p.getTratta().getTempoPercorrenza().getHour();

            long minutiTotaliTratta = (oreTratta * 60) + minutiTratta;

            long minutiPercorrenza = ChronoUnit.MINUTES.between(p.getOraPartenza(), p.getOraArrivo());

            long differenzaMinuti = minutiPercorrenza - minutiTotaliTratta;

            differenzaTotale += differenzaMinuti;
            mediaDifferenzaPercorrenza.add(differenzaMinuti);


            System.out.println("Il mezzo " + p.getMezzo().getTipoMezzo() +
                    " con targa: " + p.getMezzo().getTargaVeicolo() +
                    "\nÈ partito alle: " + p.getOraPartenza() +
                    "\ned è arrivato alle: " + p.getOraArrivo() +
                    "\ncon una differenza di: " + differenzaMinuti + " minuti\nrispetto al tempo previsto di " + oreTratta + " ore e " + minutiTratta + " minuti" + "\n");
        }


    }

    public List<Percorrenza> findMezzoPerTratta(Tratta tratta) {
        TypedQuery<Percorrenza> query = entityManager.createQuery("SELECT p FROM Percorrenza p JOIN FETCH p.mezzo JOIN FETCH p.tratta t WHERE t = :tratta", Percorrenza.class);
        query.setParameter("tratta", tratta);
        return query.getResultList();
    }


}
