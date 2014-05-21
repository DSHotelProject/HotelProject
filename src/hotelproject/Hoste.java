package hotelproject;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Hoste.findAll", query = "SELECT h FROM Hoste h"),
    @NamedQuery(name = "Hoste.findByIdHosta", query = "SELECT h FROM Hoste h WHERE h.idHosta = :idHosta"),
    @NamedQuery(name = "Hoste.findByJmenoOsoby", query = "SELECT h FROM Hoste h WHERE h.jmenoOsoby = :jmenoOsoby"),
    @NamedQuery(name = "Hoste.findByTelefon", query = "SELECT h FROM Hoste h WHERE h.telefon = :telefon"),
    @NamedQuery(name = "Hoste.findByEMail", query = "SELECT h FROM Hoste h WHERE h.eMail = :eMail"),
    @NamedQuery(name = "Hoste.findByObchJmeno", query = "SELECT h FROM Hoste h WHERE h.obchJmeno = :obchJmeno"),
    @NamedQuery(name = "Hoste.findByIc", query = "SELECT h FROM Hoste h WHERE h.ic = :ic"),
    @NamedQuery(name = "Hoste.findByDic", query = "SELECT h FROM Hoste h WHERE h.dic = :dic")})
public class Hoste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hosta")
    private Integer idHosta;
    @Basic(optional = false)
    @Column(name = "jmeno_osoby")
    private String jmenoOsoby;
    private String telefon;
    @Column(name = "email")
    private String eMail;
    @Column(name = "obch_jmeno")
    private String obchJmeno;
    private String ic;
    private String dic;
    @JoinTable(name = "hoste_has_rezervace", joinColumns = {
        @JoinColumn(name = "hoste_id_hosta", referencedColumnName = "id_hosta")}, inverseJoinColumns = {
        @JoinColumn(name = "rezervace_id_rezervace", referencedColumnName = "id_rezervace")})
    @ManyToMany
    private Collection<Rezervace> rezervaceCollection;
    @JoinColumn(name = "slevy_id_slevy", referencedColumnName = "id_slevy")
    @ManyToOne
    private Slevy slevyIdSlevy;
    @JoinColumn(name = "adresy_id_adresy", referencedColumnName = "id_adresy")
    @ManyToOne(optional = false)
    private Adresy adresyIdAdresy;

    public Hoste() {
    }

    public Hoste(Integer idHosta) {
        this.idHosta = idHosta;
    }

    public Hoste(Integer idHosta, String jmenoOsoby) {
        this.idHosta = idHosta;
        this.jmenoOsoby = jmenoOsoby;
    }

    public Integer getIdHosta() {
        return idHosta;
    }

    public void setIdHosta(Integer idHosta) {
        this.idHosta = idHosta;
    }

    public String getJmenoOsoby() {
        return jmenoOsoby;
    }

    public void setJmenoOsoby(String jmenoOsoby) {
        this.jmenoOsoby = jmenoOsoby;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getObchJmeno() {
        return obchJmeno;
    }

    public void setObchJmeno(String obchJmeno) {
        this.obchJmeno = obchJmeno;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    @XmlTransient
    public Collection<Rezervace> getRezervaceCollection() {
        return rezervaceCollection;
    }

    public void setRezervaceCollection(Collection<Rezervace> rezervaceCollection) {
        this.rezervaceCollection = rezervaceCollection;
    }

    public Slevy getSlevyIdSlevy() {
        return slevyIdSlevy;
    }

    public void setSlevyIdSlevy(Slevy slevyIdSlevy) {
        this.slevyIdSlevy = slevyIdSlevy;
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
        hash += (idHosta != null ? idHosta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoste)) {
            return false;
        }
        Hoste other = (Hoste) object;
        if ((this.idHosta == null && other.idHosta != null) || (this.idHosta != null && !this.idHosta.equals(other.idHosta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Hoste[ idHosta=" + idHosta + " ]";
    }
}
