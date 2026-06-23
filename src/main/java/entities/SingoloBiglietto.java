package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;
//Paolo
@Entity
@Table(name ="single_ticket")
public class SingoloBiglietto {
    @Id
    @GeneratedValue
    @Column(name = "biglietto_id", nullable = false)
    private UUID idBiglietto;
    @Column(name = "date_issue", nullable = false)
    private LocalDate dataEmissione;
    @Column(name = "date_certification")
    private LocalDate dataVidimazione;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "single_code")
    private int codiceUnico;
    //@JoinColumn(name = "id_mezzo", nullable = false)
    // private id_mezzo
    // @JoinColumn(name = "punto_di_emissione_id", nullable = false)
    // private id_punto_emissione

    protected SingoloBiglietto (){}

    public SingoloBiglietto(LocalDate dataEmissione,LocalDate dataVidimazione){
        this.dataEmissione = dataEmissione;
        this.dataVidimazione = dataVidimazione;

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
