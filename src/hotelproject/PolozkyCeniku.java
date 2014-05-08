package hotelproject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
@Entity
@Table(name = "polozky_ceniku", catalog = "student_db13_24", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PolozkyCeniku.findAll", query = "SELECT p FROM PolozkyCeniku p"),
    @NamedQuery(name = "PolozkyCeniku.findByIdPolozky", query = "SELECT p FROM PolozkyCeniku p WHERE p.idPolozky = :idPolozky"),
    @NamedQuery(name = "PolozkyCeniku.findByNazev", query = "SELECT p FROM PolozkyCeniku p WHERE p.nazev = :nazev"),
    @NamedQuery(name = "PolozkyCeniku.findByJednCena", query = "SELECT p FROM PolozkyCeniku p WHERE p.jednCena = :jednCena"),
    @NamedQuery(name = "PolozkyCeniku.findByJednotka", query = "SELECT p FROM PolozkyCeniku p WHERE p.jednotka = :jednotka"),
    @NamedQuery(name = "PolozkyCeniku.findByPlatna", query = "SELECT p FROM PolozkyCeniku p WHERE p.platna = :platna")})
public class PolozkyCeniku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_polozky")
    private Integer idPolozky;
    @Basic(optional = false)
    private String nazev;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "jedn_cena")
    private BigDecimal jednCena;
    @Basic(optional = false)
    private String jednotka;
    @Basic(optional = false)
    private boolean platna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polozkyCeniku")
    private Collection<UctyHasPolozkyCeniku> uctyHasPolozkyCenikuCollection;

    public PolozkyCeniku() {
    }

    public PolozkyCeniku(Integer idPolozky) {
        this.idPolozky = idPolozky;
    }

    public PolozkyCeniku(Integer idPolozky, String nazev, BigDecimal jednCena, String jednotka, boolean platna) {
        this.idPolozky = idPolozky;
        this.nazev = nazev;
        this.jednCena = jednCena;
        this.jednotka = jednotka;
        this.platna = platna;
    }

    public Integer getIdPolozky() {
        return idPolozky;
    }

    public void setIdPolozky(Integer idPolozky) {
        this.idPolozky = idPolozky;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
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

    public boolean getPlatna() {
        return platna;
    }

    public void setPlatna(boolean platna) {
        this.platna = platna;
    }

    @XmlTransient
    public Collection<UctyHasPolozkyCeniku> getUctyHasPolozkyCenikuCollection() {
        return uctyHasPolozkyCenikuCollection;
    }

    public void setUctyHasPolozkyCenikuCollection(Collection<UctyHasPolozkyCeniku> uctyHasPolozkyCenikuCollection) {
        this.uctyHasPolozkyCenikuCollection = uctyHasPolozkyCenikuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPolozky != null ? idPolozky.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolozkyCeniku)) {
            return false;
        }
        PolozkyCeniku other = (PolozkyCeniku) object;
        if ((this.idPolozky == null && other.idPolozky != null) || (this.idPolozky != null && !this.idPolozky.equals(other.idPolozky))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.PolozkyCeniku[ idPolozky=" + idPolozky + " ]";
    }

}
