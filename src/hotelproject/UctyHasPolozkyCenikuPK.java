package hotelproject;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
@Embeddable
public class UctyHasPolozkyCenikuPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ucty_id_uctu")
    private int uctyIdUctu;
    @Basic(optional = false)
    @Column(name = "polozky_ceniku_id_polozky")
    private int polozkyCenikuIdPolozky;

    public UctyHasPolozkyCenikuPK() {
    }

    public UctyHasPolozkyCenikuPK(int uctyIdUctu, int polozkyCenikuIdPolozky) {
        this.uctyIdUctu = uctyIdUctu;
        this.polozkyCenikuIdPolozky = polozkyCenikuIdPolozky;
    }

    public int getUctyIdUctu() {
        return uctyIdUctu;
    }

    public void setUctyIdUctu(int uctyIdUctu) {
        this.uctyIdUctu = uctyIdUctu;
    }

    public int getPolozkyCenikuIdPolozky() {
        return polozkyCenikuIdPolozky;
    }

    public void setPolozkyCenikuIdPolozky(int polozkyCenikuIdPolozky) {
        this.polozkyCenikuIdPolozky = polozkyCenikuIdPolozky;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) uctyIdUctu;
        hash += (int) polozkyCenikuIdPolozky;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UctyHasPolozkyCenikuPK)) {
            return false;
        }
        UctyHasPolozkyCenikuPK other = (UctyHasPolozkyCenikuPK) object;
        if (this.uctyIdUctu != other.uctyIdUctu) {
            return false;
        }
        if (this.polozkyCenikuIdPolozky != other.polozkyCenikuIdPolozky) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.UctyHasPolozkyCenikuPK[ uctyIdUctu=" + uctyIdUctu + ", polozkyCenikuIdPolozky=" + polozkyCenikuIdPolozky + " ]";
    }

}
