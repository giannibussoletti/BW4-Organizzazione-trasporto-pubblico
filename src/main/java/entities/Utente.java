package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tesserato", nullable = false, unique = true)
    private String tesserato;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;

    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    public Utente() {}

    public Utente(String tesserato, String nome, String cognome, LocalDate dataDiNascita) {
        this.tesserato = tesserato;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public UUID getId() { return id; }

    public String getTesserato() { return tesserato; }
    public void setTesserato(String tessarato) { this.tesserato = tesserato; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public LocalDate getDataDiNascita() { return dataDiNascita; }
    public void setDataDiNascita(LocalDate dataDiNascita) { this.dataDiNascita = dataDiNascita; }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", tesserato='" + tesserato + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }
}