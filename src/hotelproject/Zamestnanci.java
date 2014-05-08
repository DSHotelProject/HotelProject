package hotelproject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
@Entity
@Table(catalog = "student_db13_24", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamestnanci.findAll", query = "SELECT z FROM Zamestnanci z"),
    @NamedQuery(name = "Zamestnanci.findByIdZamestnance", query = "SELECT z FROM Zamestnanci z WHERE z.idZamestnance = :idZamestnance"),
    @NamedQuery(name = "Zamestnanci.findByJmeno", query = "SELECT z FROM Zamestnanci z WHERE z.jmeno = :jmeno"),
    @NamedQuery(name = "Zamestnanci.findByPlat", query = "SELECT z FROM Zamestnanci z WHERE z.plat = :plat"),
    @NamedQuery(name = "Zamestnanci.findByTelefon", query = "SELECT z FROM Zamestnanci z WHERE z.telefon = :telefon"),
    @NamedQuery(name = "Zamestnanci.findByZamestnanOd", query = "SELECT z FROM Zamestnanci z WHERE z.zamestnanOd = :zamestnanOd"),
    @NamedQuery(name = "Zamestnanci.findByZamestnanDo", query = "SELECT z FROM Zamestnanci z WHERE z.zamestnanDo = :zamestnanDo")})
public class Zamestnanci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_zamestnance")
    private Integer idZamestnance;
    @Basic(optional = false)
    private String jmeno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    private BigDecimal plat;
    private String telefon;
    @Basic(optional = false)
    @Column(name = "zamestnan_od")
    @Temporal(TemporalType.DATE)
    private Date zamestnanOd;
    @Column(name = "zamestnan_do")
    @Temporal(TemporalType.DATE)
    private Date zamestnanDo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamestnanciIdZamestnance")
    private Collection<Ucty> uctyCollection;
    @JoinColumn(name = "adresy_id_adresy", referencedColumnName = "id_adresy")
    @ManyToOne(optional = false)
    private Adresy adresyIdAdresy;

    public Zamestnanci() {
    }

    public Zamestnanci(Integer idZamestnance) {
        this.idZamestnance = idZamestnance;
    }

    public Zamestnanci(Integer idZamestnance, String jmeno, BigDecimal plat, Date zamestnanOd) {
        this.idZamestnance = idZamestnance;
        this.jmeno = jmeno;
        this.plat = plat;
        this.zamestnanOd = zamestnanOd;
    }

    public Integer getIdZamestnance() {
        return idZamestnance;
    }

    public void setIdZamestnance(Integer idZamestnance) {
        this.idZamestnance = idZamestnance;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public BigDecimal getPlat() {
        return plat;
    }

    public void setPlat(BigDecimal plat) {
        this.plat = plat;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getZamestnanOd() {
        return zamestnanOd;
    }

    public void setZamestnanOd(Date zamestnanOd) {
        this.zamestnanOd = zamestnanOd;
    }

    public Date getZamestnanDo() {
        return zamestnanDo;
    }

    public void setZamestnanDo(Date zamestnanDo) {
        this.zamestnanDo = zamestnanDo;
    }

    @XmlTransient
    public Collection<Ucty> getUctyCollection() {
        return uctyCollection;
    }

    public void setUctyCollection(Collection<Ucty> uctyCollection) {
        this.uctyCollection = uctyCollection;
    }

    public Adresy getAdresyIdAdresy() {
        return adresyIdAdresy;
    }

    public void setAdresyIdAdresy(Adresy adresyIdAdresy) {
        this.adresyIdAdresy = adresyIdAdresy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZamestnance != null ? idZamestnance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamestnanci)) {
            return false;
        }
        Zamestnanci other = (Zamestnanci) object;
        if ((this.idZamestnance == null && other.idZamestnance != null) || (this.idZamestnance != null && !this.idZamestnance.equals(other.idZamestnance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Zamestnanci[ idZamestnance=" + idZamestnance + " ]";
    }

}
