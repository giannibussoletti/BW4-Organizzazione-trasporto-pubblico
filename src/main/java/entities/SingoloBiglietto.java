package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

//Paolo
@Entity
@Table(name = "single_ticket")
public class SingoloBiglietto {

    @Id
    @GeneratedValue
    @Column(name = "biglietto_id", nullable = false)
    private UUID idBiglietto;

    @Column(name = "date_issue", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "date_certification")
    private LocalDate dataVidimazione;

    @Column(name = "single_code")
    private int codiceUnico;

    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo id_mezzo;
    @JoinColumn(name = "id_punto_di_emissione", nullable = false)
    private PuntoEmissione id_punto_emissione;

    protected SingoloBiglietto() {
    }

    public SingoloBiglietto(LocalDate dataEmissione, LocalDate dataVidimazione, Mezzo id_mezzo, PuntoEmissione id_punto_emissione) {
        Random random = new Random();
        if (random.nextInt() > 0) this.codiceUnico = random.nextInt();
        else this.codiceUnico = -random.nextInt();
        this.dataEmissione = dataEmissione;
        this.dataVidimazione = dataVidimazione;
        this.id_mezzo = id_mezzo;
        this.id_punto_emissione = id_punto_emissione;

    }

    //============= GETTERS ==================//
    public UUID getIdBiglietto() {
        return idBiglietto;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public int getCodiceUnico() {
        return codiceUnico;
    }

    //============= TOSTRING ==================//
    @Override
    public String toString() {
        return "SingoloBiglietto{" +
                "idBiglietto=" + idBiglietto +
                ", dataEmissione=" + dataEmissione +
                ", dataVidimazione=" + dataVidimazione +
                ", codiceUnico=" + codiceUnico +
                '}';
    }
}
