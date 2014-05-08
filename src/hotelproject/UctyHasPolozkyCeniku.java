package hotelproject;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
@Entity
@Table(name = "ucty_has_polozky_ceniku", catalog = "student_db13_24", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UctyHasPolozkyCeniku.findAll", query = "SELECT u FROM UctyHasPolozkyCeniku u"),
    @NamedQuery(name = "UctyHasPolozkyCeniku.findByUctyIdUctu", query = "SELECT u FROM UctyHasPolozkyCeniku u WHERE u.uctyHasPolozkyCenikuPK.uctyIdUctu = :uctyIdUctu"),
    @NamedQuery(name = "UctyHasPolozkyCeniku.findByPolozkyCenikuIdPolozky", query = "SELECT u FROM UctyHasPolozkyCeniku u WHERE u.uctyHasPolozkyCenikuPK.polozkyCenikuIdPolozky = :polozkyCenikuIdPolozky"),
    @NamedQuery(name = "UctyHasPolozkyCeniku.findByMnozstvi", query = "SELECT u FROM UctyHasPolozkyCeniku u WHERE u.mnozstvi = :mnozstvi"),
    @NamedQuery(name = "UctyHasPolozkyCeniku.findByJednCena", query = "SELECT u FROM UctyHasPolozkyCeniku u WHERE u.jednCena = :jednCena"),
    @NamedQuery(name = "UctyHasPolozkyCeniku.findByJednotka", query = "SELECT u FROM UctyHasPolozkyCeniku u WHERE u.jednotka = :jednotka")})
public class UctyHasPolozkyCeniku implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UctyHasPolozkyCenikuPK uctyHasPolozkyCenikuPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    private BigDecimal mnozstvi;
    @Basic(optional = false)
    @Column(name = "jedn_cena")
    private BigDecimal jednCena;
    @Basic(optional = false)
    private String jednotka;
    @JoinColumn(name = "ucty_id_uctu", referencedColumnName = "id_uctu", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ucty ucty;
    @JoinColumn(name = "polozky_ceniku_id_polozky", referencedColumnName = "id_polozky", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PolozkyCeniku polozkyCeniku;

    public UctyHasPolozkyCeniku() {
    }

    public UctyHasPolozkyCeniku(UctyHasPolozkyCenikuPK uctyHasPolozkyCenikuPK) {
        this.uctyHasPolozkyCenikuPK = uctyHasPolozkyCenikuPK;
    }

    public UctyHasPolozkyCeniku(UctyHasPolozkyCenikuPK uctyHasPolozkyCenikuPK, BigDecimal mnozstvi, BigDecimal jednCena, String jednotka) {
        this.uctyHasPolozkyCenikuPK = uctyHasPolozkyCenikuPK;
        this.mnozstvi = mnozstvi;
        this.jednCena = jednCena;
        this.jednotka = jednotka;
    }

    public UctyHasPolozkyCeniku(int uctyIdUctu, int polozkyCenikuIdPolozky) {
        this.uctyHasPolozkyCenikuPK = new UctyHasPolozkyCenikuPK(uctyIdUctu, polozkyCenikuIdPolozky);
    }

    public UctyHasPolozkyCenikuPK getUctyHasPolozkyCenikuPK() {
        return uctyHasPolozkyCenikuPK;
    }

    public void setUctyHasPolozkyCenikuPK(UctyHasPolozkyCenikuPK uctyHasPolozkyCenikuPK) {
        this.uctyHasPolozkyCenikuPK = uctyHasPolozkyCenikuPK;
    }

    public BigDecimal getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(BigDecimal mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public BigDecimal getJednCena() {
        return jednCena;
    }

    public void setJednCena(BigDecimal jednCena) {
        this.jednCena = jednCena;
    }

    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    public Ucty getUcty() {
        return ucty;
    }

    public void setUcty(Ucty ucty) {
        this.ucty = ucty;
    }

    public PolozkyCeniku getPolozkyCeniku() {
        return polozkyCeniku;
    }

    public void setPolozkyCeniku(PolozkyCeniku polozkyCeniku) {
        this.polozkyCeniku = polozkyCeniku;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uctyHasPolozkyCenikuPK != null ? uctyHasPolozkyCenikuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UctyHasPolozkyCeniku)) {
            return false;
        }
        UctyHasPolozkyCeniku other = (UctyHasPolozkyCeniku) object;
        if ((this.uctyHasPolozkyCenikuPK == null && other.uctyHasPolozkyCenikuPK != null) || (this.uctyHasPolozkyCenikuPK != null && !this.uctyHasPolozkyCenikuPK.equals(other.uctyHasPolozkyCenikuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.UctyHasPolozkyCeniku[ uctyHasPolozkyCenikuPK=" + uctyHasPolozkyCenikuPK + " ]";
    }

}
