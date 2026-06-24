package entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String partenza; // e

    @Column(nullable = false)
    private String capolinea;

    @Column(name = "tempo_percorrenza", nullable = false)
    private LocalTime tempoPercorrenza;

    public Tratta() {
    }

    public Tratta(String partenza, String capolinea, LocalTime tempoPercorrenza) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public UUID getId() {
        return id;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public LocalTime getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(LocalTime tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }


    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}
