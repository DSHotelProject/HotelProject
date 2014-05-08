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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Ucty.findAll", query = "SELECT u FROM Ucty u"),
    @NamedQuery(name = "Ucty.findByIdUctu", query = "SELECT u FROM Ucty u WHERE u.idUctu = :idUctu"),
    @NamedQuery(name = "Ucty.findByUzavren", query = "SELECT u FROM Ucty u WHERE u.uzavren = :uzavren"),
    @NamedQuery(name = "Ucty.findByPolozkyFinal", query = "SELECT u FROM Ucty u WHERE u.polozkyFinal = :polozkyFinal"),
    @NamedQuery(name = "Ucty.findByFaJmeno", query = "SELECT u FROM Ucty u WHERE u.faJmeno = :faJmeno"),
    @NamedQuery(name = "Ucty.findByFaIc", query = "SELECT u FROM Ucty u WHERE u.faIc = :faIc"),
    @NamedQuery(name = "Ucty.findByFaDic", query = "SELECT u FROM Ucty u WHERE u.faDic = :faDic"),
    @NamedQuery(name = "Ucty.findByFaStat", query = "SELECT u FROM Ucty u WHERE u.faStat = :faStat"),
    @NamedQuery(name = "Ucty.findByFaMesto", query = "SELECT u FROM Ucty u WHERE u.faMesto = :faMesto"),
    @NamedQuery(name = "Ucty.findByFaUlice", query = "SELECT u FROM Ucty u WHERE u.faUlice = :faUlice"),
    @NamedQuery(name = "Ucty.findByFaCp", query = "SELECT u FROM Ucty u WHERE u.faCp = :faCp"),
    @NamedQuery(name = "Ucty.findByFaPsc", query = "SELECT u FROM Ucty u WHERE u.faPsc = :faPsc")})
public class Ucty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_uctu")
    private Integer idUctu;
    @Basic(optional = false)
    private boolean uzavren;
    @Column(name = "polozky_final")
    private String polozkyFinal;
    @Basic(optional = false)
    @Column(name = "fa_jmeno")
    private String faJmeno;
    @Column(name = "fa_ic")
    private String faIc;
    @Column(name = "fa_dic")
    private String faDic;
    @Basic(optional = false)
    @Column(name = "fa_stat")
    private String faStat;
    @Basic(optional = false)
    @Column(name = "fa_mesto")
    private String faMesto;
    @Basic(optional = false)
    @Column(name = "fa_ulice")
    private String faUlice;
    @Basic(optional = false)
    @Column(name = "fa_cp")
    private String faCp;
    @Column(name = "fa_psc")
    private Integer faPsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ucty")
    private Collection<UctyHasPolozkyCeniku> uctyHasPolozkyCenikuCollection;
    @JoinColumn(name = "zamestnanci_id_zamestnance", referencedColumnName = "id_zamestnance")
    @ManyToOne(optional = false)
    private Zamestnanci zamestnanciIdZamestnance;
    @JoinColumn(name = "rezervace_id_rezervace", referencedColumnName = "id_rezervace")
    @ManyToOne(optional = false)
    private Rezervace rezervaceIdRezervace;

    public Ucty() {
    }

    public Ucty(Integer idUctu) {
        this.idUctu = idUctu;
    }

    public Ucty(Integer idUctu, boolean uzavren, String faJmeno, String faStat, String faMesto, String faUlice, String faCp) {
        this.idUctu = idUctu;
        this.uzavren = uzavren;
        this.faJmeno = faJmeno;
        this.faStat = faStat;
        this.faMesto = faMesto;
        this.faUlice = faUlice;
        this.faCp = faCp;
    }

    public Integer getIdUctu() {
        return idUctu;
    }

    public void setIdUctu(Integer idUctu) {
        this.idUctu = idUctu;
    }

    public boolean getUzavren() {
        return uzavren;
    }

    public void setUzavren(boolean uzavren) {
        this.uzavren = uzavren;
    }

    public String getPolozkyFinal() {
        return polozkyFinal;
    }

    public void setPolozkyFinal(String polozkyFinal) {
        this.polozkyFinal = polozkyFinal;
    }

    public String getFaJmeno() {
        return faJmeno;
    }

    public void setFaJmeno(String faJmeno) {
        this.faJmeno = faJmeno;
    }

    public String getFaIc() {
        return faIc;
    }

    public void setFaIc(String faIc) {
        this.faIc = faIc;
    }

    public String getFaDic() {
        return faDic;
    }

    public void setFaDic(String faDic) {
        this.faDic = faDic;
    }

    public String getFaStat() {
        return faStat;
    }

    public void setFaStat(String faStat) {
        this.faStat = faStat;
    }

    public String getFaMesto() {
        return faMesto;
    }

    public void setFaMesto(String faMesto) {
        this.faMesto = faMesto;
    }

    public String getFaUlice() {
        return faUlice;
    }

    public void setFaUlice(String faUlice) {
        this.faUlice = faUlice;
    }

    public String getFaCp() {
        return faCp;
    }

    public void setFaCp(String faCp) {
        this.faCp = faCp;
    }

    public Integer getFaPsc() {
        return faPsc;
    }

    public void setFaPsc(Integer faPsc) {
        this.faPsc = faPsc;
    }

    @XmlTransient
    public Collection<UctyHasPolozkyCeniku> getUctyHasPolozkyCenikuCollection() {
        return uctyHasPolozkyCenikuCollection;
    }

    public void setUctyHasPolozkyCenikuCollection(Collection<UctyHasPolozkyCeniku> uctyHasPolozkyCenikuCollection) {
        this.uctyHasPolozkyCenikuCollection = uctyHasPolozkyCenikuCollection;
    }

    public Zamestnanci getZamestnanciIdZamestnance() {
        return zamestnanciIdZamestnance;
    }

    public void setZamestnanciIdZamestnance(Zamestnanci zamestnanciIdZamestnance) {
        this.zamestnanciIdZamestnance = zamestnanciIdZamestnance;
    }

    public Rezervace getRezervaceIdRezervace() {
        return rezervaceIdRezervace;
    }

    public void setRezervaceIdRezervace(Rezervace rezervaceIdRezervace) {
        this.rezervaceIdRezervace = rezervaceIdRezervace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUctu != null ? idUctu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ucty)) {
            return false;
        }
        Ucty other = (Ucty) object;
        if ((this.idUctu == null && other.idUctu != null) || (this.idUctu != null && !this.idUctu.equals(other.idUctu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Ucty[ idUctu=" + idUctu + " ]";
    }

}
