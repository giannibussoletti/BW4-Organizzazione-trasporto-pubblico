package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditore_autorizzato")
@DiscriminatorValue("rivenditore autorizzato")
public class RivenditoreAutorizzato extends PuntoEmissione {

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;
    @Column(name = "nome_attività", nullable = false)
    private String nomeAttivita;


    protected RivenditoreAutorizzato() {
    }

    public RivenditoreAutorizzato(String indirizzo, String ragioneSociale, String nomeAttivita) {
        super(indirizzo);
        this.ragioneSociale = ragioneSociale;
        this.nomeAttivita = nomeAttivita;
    }

    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" +
                "ragioneSociale='" + ragioneSociale + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                "} " + super.toString();
    }
}
