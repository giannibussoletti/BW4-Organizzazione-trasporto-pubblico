package entities;

import enums.StatoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

//Paolo
@Entity
@Table(name = "status")
public class StatoDelMezzo {
    @Id
    @GeneratedValue
    @Column(name = "id_stato_vehicle",nullable = false)
    private UUID id;
    @Column(name = "status_vehicle")
    private StatoMezzo type;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    protected StatoDelMezzo(){}
    public StatoDelMezzo(StatoMezzo type, LocalDate dataInizio, LocalDate dataFine){
        this.type = type;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }
    //============= GETTERS ==================//

    public UUID getId() {
        return id;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public StatoMezzo getType() {
        return type;
    }


    //============= TOSTRING ==================//


    @Override
    public String toString() {
        return "StatoDelMezzo{" +
                "id=" + id +
                ", type=" + type +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
