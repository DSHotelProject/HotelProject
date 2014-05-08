package hotelproject;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Pokoje.findAll", query = "SELECT p FROM Pokoje p"),
    @NamedQuery(name = "Pokoje.findByIdPokoje", query = "SELECT p FROM Pokoje p WHERE p.idPokoje = :idPokoje"),
    @NamedQuery(name = "Pokoje.findByCisloPokoje", query = "SELECT p FROM Pokoje p WHERE p.cisloPokoje = :cisloPokoje"),
    @NamedQuery(name = "Pokoje.findByPocetLuzek", query = "SELECT p FROM Pokoje p WHERE p.pocetLuzek = :pocetLuzek"),
    @NamedQuery(name = "Pokoje.findByCenaZaNoc", query = "SELECT p FROM Pokoje p WHERE p.cenaZaNoc = :cenaZaNoc")})
public class Pokoje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pokoje")
    private Integer idPokoje;
    @Basic(optional = false)
    @Column(name = "cislo_pokoje")
    private short cisloPokoje;
    @Basic(optional = false)
    @Column(name = "pocet_luzek")
    private short pocetLuzek;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cena_za_noc")
    private BigDecimal cenaZaNoc;
    @JoinTable(name = "rezervace_has_pokoje", joinColumns = {
        @JoinColumn(name = "pokoje_id_pokoje", referencedColumnName = "id_pokoje")}, inverseJoinColumns = {
        @JoinColumn(name = "rezervace_id_rezervace", referencedColumnName = "id_rezervace")})
    @ManyToMany
    private Collection<Rezervace> rezervaceCollection;

    public Pokoje() {
    }

    public Pokoje(Integer idPokoje) {
        this.idPokoje = idPokoje;
    }

    public Pokoje(short cisloPokoje, short pocetLuzek, BigDecimal cenaZaNoc) {
        this.cisloPokoje = cisloPokoje;
        this.pocetLuzek = pocetLuzek;
        this.cenaZaNoc = cenaZaNoc;
    }

    public Pokoje(Integer idPokoje, short cisloPokoje, short pocetLuzek, BigDecimal cenaZaNoc) {
        this.idPokoje = idPokoje;
        this.cisloPokoje = cisloPokoje;
        this.pocetLuzek = pocetLuzek;
        this.cenaZaNoc = cenaZaNoc;
    }

    public Integer getIdPokoje() {
        return idPokoje;
    }

    public void setIdPokoje(Integer idPokoje) {
        this.idPokoje = idPokoje;
    }

    public short getCisloPokoje() {
        return cisloPokoje;
    }

    public void setCisloPokoje(short cisloPokoje) {
        this.cisloPokoje = cisloPokoje;
    }

    public short getPocetLuzek() {
        return pocetLuzek;
    }

    public void setPocetLuzek(short pocetLuzek) {
        this.pocetLuzek = pocetLuzek;
    }

    public BigDecimal getCenaZaNoc() {
        return cenaZaNoc;
    }

    public void setCenaZaNoc(BigDecimal cenaZaNoc) {
        this.cenaZaNoc = cenaZaNoc;
    }

    @XmlTransient
    public Collection<Rezervace> getRezervaceCollection() {
        return rezervaceCollection;
    }

    public void setRezervaceCollection(Collection<Rezervace> rezervaceCollection) {
        this.rezervaceCollection = rezervaceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPokoje != null ? idPokoje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pokoje)) {
            return false;
        }
        Pokoje other = (Pokoje) object;
        if ((this.idPokoje == null && other.idPokoje != null) || (this.idPokoje != null && !this.idPokoje.equals(other.idPokoje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Pokoje[ idPokoje=" + idPokoje + " ]";
    }

}
