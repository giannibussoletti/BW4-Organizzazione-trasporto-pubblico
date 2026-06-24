package entities;

import enums.TipoAbbonamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Random;
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

    @Column(name = "tipo_di_abbonamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessera tessera;

    @Column(name = "codice_unico", nullable = false)
    private int codiceUnico;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    protected Abbonamento() {
    }

    public Abbonamento(PuntoEmissione puntoEmissione, TipoAbbonamento tipoAbbonamento, Tessera tessera) {
        this.puntoEmissione = puntoEmissione;
        this.tipoAbbonamento = tipoAbbonamento;
        this.tessera = tessera;
        this.dataEmissione = LocalDate.now();
        Random r = new Random();
        this.codiceUnico = Math.abs(r.nextInt());
        if (tipoAbbonamento == TipoAbbonamento.SETTIMANALE) {
            this.dataScadenza = LocalDate.now().plusDays(7);
        } else {
            this.dataScadenza = LocalDate.now().plusMonths(1);
        }

    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public PuntoEmissione getPuntoEmissione() {
        return puntoEmissione;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public int getCodiceUnico() {
        return codiceUnico;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", puntoEmissione=" + puntoEmissione +
                ", tipoAbbonamento=" + tipoAbbonamento +
                ", tessera=" + tessera +
                ", codiceUnico=" + codiceUnico +
                '}';
    }
}
