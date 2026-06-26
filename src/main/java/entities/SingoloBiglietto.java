package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "single_ticket")
public class SingoloBiglietto {

    @Id
    @GeneratedValue
    @Column(name = "biglietto_id", nullable = false)
    private UUID idBiglietto;

    @Column(name = "date_issue", nullable = false)
    private LocalDate dataEmissione = LocalDate.now();

    @Column(name = "date_vidimazione")
    private LocalDateTime dataVidimazione;

    @Column(name = "single_code")
    private int codiceUnico;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo id_mezzo;

    @ManyToOne
    @JoinColumn(name = "id_punto_di_emissione", nullable = false)
    private PuntoEmissione id_punto_emissione;

    protected SingoloBiglietto() {
    }

    public SingoloBiglietto(Mezzo id_mezzo, PuntoEmissione id_punto_emissione) {
        Random r = new Random();
        this.codiceUnico = Math.abs(r.nextInt());
        this.id_mezzo = id_mezzo;
        this.id_punto_emissione = id_punto_emissione;
    }

    // ===== GETTERS & SETTERS =====

    public UUID getIdBiglietto() {
        return idBiglietto;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDateTime getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDateTime dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    public int getCodiceUnico() {
        return codiceUnico;
    }

    public Mezzo getId_mezzo() {
        return id_mezzo;
    }

    public PuntoEmissione getId_punto_emissione() {
        return id_punto_emissione;
    }

    // ===== TO STRING =====

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
