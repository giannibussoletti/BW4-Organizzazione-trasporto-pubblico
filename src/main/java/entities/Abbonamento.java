package entities;

import enums.TipoAbbonamento;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "abbonamento")
public class Abbonamento {
    @Id
    @GeneratedValue
    @Column(name = "id_abbonamento", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_punto_emissione", nullable = false)
    private PuntoEmissione puntoEmissione;

    @Column(name = "tipo_di_abbonamento")
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    @OneToOne
    @JoinColumn(name = "id_tessera", nullable = false, unique = true)
    private Tessera tessera;

    @Column(name = "codice_unico")
    private int codiceUnico;


    protected Abbonamento() {
    }

    public Abbonamento(PuntoEmissione puntoEmissione, TipoAbbonamento tipoAbbonamento, Tessera tessera, int codiceUnico) {
        this.puntoEmissione = puntoEmissione;
        this.tipoAbbonamento = tipoAbbonamento;
        this.tessera = tessera;
        this.codiceUnico = codiceUnico;
    }
}
