package entities;

import enums.ParcoMezzi_Types;
import jakarta.persistence.*;

import java.util.UUID;
//Paolo
@Entity
public class ParcoMezzi {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;
    @GeneratedValue
    @Column(name = "manutenzione_id", nullable = false)
    private UUID servizioManutenzione;
    @Column(name = "pm_type")
    private ParcoMezzi_Types type;
    @Column(name = "capacity")
    private int capienza;
    @GeneratedValue
    @Column(name = "route_id", nullable = false)
    private UUID trattaAssegnata;

    protected ParcoMezzi(){}
    public ParcoMezzi(ParcoMezzi_Types type, int capienza ){
        this.type = type;
        this.capienza = capienza;
    }
    //============= GETTERS ==================//

    public UUID getId() {
        return id;
    }

    public UUID getServizioManutenzione() {
        return servizioManutenzione;
    }

    public ParcoMezzi_Types getType() {
        return type;
    }

    public int getCapienza() {
        return capienza;
    }

    public UUID getTrattaAssegnata() {
        return trattaAssegnata;
    }


    //============= TOSTRING ==================//

    @Override
    public String toString() {
        return "ParcoMezzi{" +
                "id=" + id +
                ", servizioManutenzione=" + servizioManutenzione +
                ", type=" + type +
                ", capienza=" + capienza +
                ", trattaAssegnata=" + trattaAssegnata +
                '}';
    }
}
