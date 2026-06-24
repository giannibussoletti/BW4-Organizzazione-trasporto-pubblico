package entities;

import enums.StatoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

//Paolo
@Entity
@Table(name = "stato_del_mezzo")
public class StatoDelMezzo {
    @Id
    @GeneratedValue
    @Column(name = "id_stato_vehicle", nullable = false)
    private UUID id;

    @Column(name = "status_vehicle", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoMezzo type;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo idMezzo;

    protected StatoDelMezzo() {
    }

    public StatoDelMezzo(StatoMezzo type, LocalDate dataInizio, Mezzo idMezzo) {
        this.type = type;
        this.dataInizio = dataInizio;
        this.idMezzo = idMezzo;
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

    public Mezzo getIdMezzo() {
        return idMezzo;
    }

//============= TOSTRING ==================//


    @Override
    public String toString() {
        return "StatoDelMezzo{" +
                "id=" + id +
                ", type=" + type +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", idMezzo=" + idMezzo +
                '}';
    }
}
