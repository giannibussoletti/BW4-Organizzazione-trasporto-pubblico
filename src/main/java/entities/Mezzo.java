package entities;


import enums.TipoMezzo;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "status")
public class Mezzo {
    @Id
    @GeneratedValue
    @Column(name = "id_mezzo", nullable = false)
    private UUID id;

    @Column(name = "tipo_mezzo", nullable = false)
    private TipoMezzo tipoMezzo;

    @Column(name = "capienza_massima", nullable = false)
    private int capienza;


    protected Mezzo() {
    }

    public Mezzo(TipoMezzo tipoMezzo, int capienza) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
    }
    //============= GETTERS ==================//

    public UUID getId() {
        return id;
    }


    public int getCapienza() {
        return capienza;
    }

    //============= SETTERS ==================//

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }


    //============= TOSTRING ==================//


    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipoMezzo=" + tipoMezzo +
                ", capienza=" + capienza +
                '}';
    }
}
