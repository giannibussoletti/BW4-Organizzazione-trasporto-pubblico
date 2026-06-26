package entities;

import enums.TipoMezzo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mezzo")
public class Mezzo {

    @Id
    @GeneratedValue
    @Column(name = "id_mezzo", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mezzo", nullable = false)
    private TipoMezzo tipoMezzo;

    @Column(name = "capienza_massima", nullable = false)
    private int capienza;

    @Column(name = "targa_veicolo", nullable = false)
    private String targaVeicolo;

    @OneToMany(mappedBy = "idMezzo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StatoDelMezzo> periodiStato = new ArrayList<>();

    protected Mezzo() {}

    public Mezzo(TipoMezzo tipoMezzo, int capienza, String targaVeicolo) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.targaVeicolo = targaVeicolo;
    }

    public UUID getId() {
        return id;
    }

    public String getTargaVeicolo() {
        return targaVeicolo;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public List<StatoDelMezzo> getPeriodiStato() {
        return periodiStato;
    }

    public List<StatoDelMezzo> getManutenzioni() {
        return periodiStato;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipoMezzo=" + tipoMezzo +
                ", capienza=" + capienza +
                '}';
    }
}
