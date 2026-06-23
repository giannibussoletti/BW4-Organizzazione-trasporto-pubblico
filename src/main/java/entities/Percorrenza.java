package entities;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "percorrenza")
public class Percorrenza {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "ora_partenza")
    private LocalTime oraPartenza;

    @Column(name = "ora_arrivo")
    private LocalTime oraArrivo;

    // Relazione N:1 verso Tratta
    @ManyToOne
    @JoinColumn(name = "tratta_id", nullable = false)
    private Tratta tratta;

    // Relazione N:1 verso Mezzo )
    // @ManyToOne
   //  @JoinColumn(name = "mezzo_id", nullable = false)
  //   private Mezzo mezzo;

    public Percorrenza() {
    }

    public Percorrenza(LocalTime oraPartenza, LocalTime oraArrivo, Tratta tratta, Mezzo mezzo) {
        this.oraPartenza = oraPartenza;
        this.oraArrivo = oraArrivo;
        this.tratta = tratta;
        //  this.mezzo = mezzo;
    }

    public UUID getId() {
        return id;
    }

    public LocalTime getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(LocalTime oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public LocalTime getOraArrivo() {
        return oraArrivo;
    }

    public void setOraArrivo(LocalTime oraArrivo) {
        this.oraArrivo = oraArrivo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    // public Mezzo getMezzo() {
       //  return mezzo;
    // }

    // public void setMezzo(Mezzo mezzo) {
       //  this.mezzo = mezzo;
    // }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", oraPartenza=" + oraPartenza +
                ", oraArrivo=" + oraArrivo +
                ", tratta=" + (tratta != null ? tratta.getId() : "null") +
               // ", mezzo=" + (mezzo != null ? mezzo.getId() : "null") +
                '}';
    }
}
