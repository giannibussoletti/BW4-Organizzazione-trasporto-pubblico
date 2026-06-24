package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "distributore_automatico")
@DiscriminatorValue("distributore automatico")
public class DistributoreAutomatico extends PuntoEmissione {

    @Column(name = "attivo")
    private boolean attivo;

    protected DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String indirizzo, boolean attivo) {
        super(indirizzo);
        this.attivo = attivo;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "attivo=" + attivo +
                "} " + super.toString();
    }
}
