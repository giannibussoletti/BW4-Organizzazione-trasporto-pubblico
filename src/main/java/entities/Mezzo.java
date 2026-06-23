package entities;


import enums.StatoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "status")
public class Mezzo {
    @Id
    @GeneratedValue
    @Column(name = "id_stato_vehicle",nullable = false)
    private UUID id;

    //idStatodelMezzo

    @Column(name = "status_vehicle")
    @Enumerated(EnumType.STRING)
    private StatoMezzo type;

    @Column(name = "capienza_massima", nullable = false)
    private int capienza;

    @GeneratedValue
    @Column(name = "id_tratta", nullable = false)
    private UUID idTratta;

    protected Mezzo(){}
    public Mezzo(StatoMezzo type,int capienza, LocalDate dataInizio, LocalDate dataFine){
        this.type = type;
        this.capienza = capienza;
    }
    //============= GETTERS ==================//

    public UUID getId() {
        return id;
    }

    public StatoMezzo getType() {
        return type;
    }

    public int getCapienza() {
        return capienza;
    }

    public UUID getIdTratta() {
        return idTratta;
    }

    //============= SETTERS ==================//

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public void setType(StatoMezzo type) {
        this.type = type;
    }


    //============= TOSTRING ==================//


    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", type=" + type +
                ", capienza=" + capienza +
                ", idTratta=" + idTratta +
                '}';
    }
}
