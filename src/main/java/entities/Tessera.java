package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessera")
public class Tessera {

    @Id
    @GeneratedValue
    @Column(name = "id_tessera", nullable = false)
    private UUID id;

    @Column(name = "codice_tessera", nullable = false, unique = true)
    private String codiceTessera;

    @Column(name = "data_di_rinnovo", nullable = false)
    private LocalDate dataDiRinnovo;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    public Tessera() {
    }

    public Tessera(String codiceTessera, LocalDate dataEmissione, LocalDate dataDiRinnovo) {
        this.codiceTessera = codiceTessera;
        this.dataEmissione = dataEmissione;
        this.dataDiRinnovo = dataDiRinnovo;
    }

    public UUID getId() {
        return id;
    }

    public String getCodiceTessera() {
        return codiceTessera;
    }

    public void setCodiceTessera(String codiceTessera) {
        this.codiceTessera = codiceTessera;
    }

    public LocalDate getDataDiRinnovo() {
        return dataDiRinnovo;
    }

    public void setDataDiRinnovo(LocalDate dataDiRinnovo) {
        this.dataDiRinnovo = dataDiRinnovo;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", codiceTessera='" + codiceTessera + '\'' +
                ", dataDiRinnovo=" + dataDiRinnovo +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}