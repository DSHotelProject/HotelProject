package hotelproject;

import java.io.Serializable;
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
@Table(catalog = "student_db13_24", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresy.findAll", query = "SELECT a FROM Adresy a"),
    @NamedQuery(name = "Adresy.findByIdAdresy", query = "SELECT a FROM Adresy a WHERE a.idAdresy = :idAdresy"),
    @NamedQuery(name = "Adresy.findByStat", query = "SELECT a FROM Adresy a WHERE a.stat = :stat"),
    @NamedQuery(name = "Adresy.findByMesto", query = "SELECT a FROM Adresy a WHERE a.mesto = :mesto"),
    @NamedQuery(name = "Adresy.findByUlice", query = "SELECT a FROM Adresy a WHERE a.ulice = :ulice"),
    @NamedQuery(name = "Adresy.findByCp", query = "SELECT a FROM Adresy a WHERE a.cp = :cp"),
    @NamedQuery(name = "Adresy.findByPsc", query = "SELECT a FROM Adresy a WHERE a.psc = :psc")})
public class Adresy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adresy")
    private Integer idAdresy;
    @Basic(optional = false)
    private String stat = "česká republika";
    @Basic(optional = false)
    private String mesto;
    @Basic(optional = false)
    private String ulice;
    @Basic(optional = false)
    private String cp;
    private String psc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresyIdAdresy")
    private Collection<Hoste> hosteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresyIdAdresy")
    private Collection<Zamestnanci> zamestnanciCollection;

    public Adresy() {
    }

    public Adresy(Integer idAdresy) {
        this.idAdresy = idAdresy;
    }

    public Adresy(String mesto, String ulice, String cp) {
        this.mesto = mesto;
        this.ulice = ulice;
        this.cp = cp;
    }

    public Adresy(String stat, String mesto, String ulice, String cp, String psc) {
        this.stat = stat;
        this.mesto = mesto;
        this.ulice = ulice;
        this.cp = cp;
        this.psc = psc;
    }

    public Adresy(Integer idAdresy, String stat, String mesto, String ulice, String cp) {
        this.idAdresy = idAdresy;
        this.stat = stat;
        this.mesto = mesto;
        this.ulice = ulice;
        this.cp = cp;
    }

    public Integer getIdAdresy() {
        return idAdresy;
    }

    public void setIdAdresy(Integer idAdresy) {
        this.idAdresy = idAdresy;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    @XmlTransient
    public Collection<Hoste> getHosteCollection() {
        return hosteCollection;
    }

    public void setHosteCollection(Collection<Hoste> hosteCollection) {
        this.hosteCollection = hosteCollection;
    }

    @XmlTransient
    public Collection<Zamestnanci> getZamestnanciCollection() {
        return zamestnanciCollection;
    }

    public void setZamestnanciCollection(Collection<Zamestnanci> zamestnanciCollection) {
        this.zamestnanciCollection = zamestnanciCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresy != null ? idAdresy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresy)) {
            return false;
        }
        Adresy other = (Adresy) object;
        if ((this.idAdresy == null && other.idAdresy != null) || (this.idAdresy != null && !this.idAdresy.equals(other.idAdresy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Adresy[ idAdresy=" + idAdresy + " ]";
    }

}
