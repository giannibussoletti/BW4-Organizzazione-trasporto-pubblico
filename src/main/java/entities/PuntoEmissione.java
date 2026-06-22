package entities;

import enums.TipoPuntoEmissione;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "punto_di_emissione")
public class PuntoEmissione {

    @Id
    @GeneratedValue
    @Column(name = "id_punto_emissione", nullable = false)
    private UUID id;

    @Column(name = "tipo_punto_emissione", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPuntoEmissione tipoPuntoEmissione;

    @Column(name = "distributore_automatico_attivo")
    private boolean distributoreAutomaticoAttivo;


    protected PuntoEmissione() {
    }

    public PuntoEmissione(TipoPuntoEmissione tipoPuntoEmissione) {
        this.tipoPuntoEmissione = tipoPuntoEmissione;
    }

    public PuntoEmissione(TipoPuntoEmissione tipoPuntoEmissione, boolean distributoreAutomaticoAttivo) {
        this.tipoPuntoEmissione = tipoPuntoEmissione;
        this.distributoreAutomaticoAttivo = distributoreAutomaticoAttivo;
    }

    public TipoPuntoEmissione getTipoPuntoEmissione() {
        return tipoPuntoEmissione;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "id=" + id +
                ", tipoPuntoEmissione=" + tipoPuntoEmissione +
                ", distributoreAutomaticoAttivo=" + distributoreAutomaticoAttivo +
                '}';
    }
}
