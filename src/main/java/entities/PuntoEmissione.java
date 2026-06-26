package entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "punto_di_emissione")
@DiscriminatorColumn(name = "tipo_di_distributore")
public abstract class PuntoEmissione {

    @Id
    @GeneratedValue
    @Column(name = "id_punto_emissione", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String indirizzo;

    protected PuntoEmissione() {
    }

    protected PuntoEmissione(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "id=" + id +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}