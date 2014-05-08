package hotelproject;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Slevy.findAll", query = "SELECT s FROM Slevy s"),
    @NamedQuery(name = "Slevy.findByIdSlevy", query = "SELECT s FROM Slevy s WHERE s.idSlevy = :idSlevy"),
    @NamedQuery(name = "Slevy.findByNazev", query = "SELECT s FROM Slevy s WHERE s.nazev = :nazev"),
    @NamedQuery(name = "Slevy.findBySleva", query = "SELECT s FROM Slevy s WHERE s.sleva = :sleva")})
public class Slevy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_slevy")
    private Integer idSlevy;
    @Basic(optional = false)
    private String nazev;
    @Basic(optional = false)
    private short sleva;
    @OneToMany(mappedBy = "slevyIdSlevy")
    private Collection<Hoste> hosteCollection;

    public Slevy() {
    }

    public Slevy(Integer idSlevy) {
        this.idSlevy = idSlevy;
    }

    public Slevy(Integer idSlevy, String nazev, short sleva) {
        this.idSlevy = idSlevy;
        this.nazev = nazev;
        this.sleva = sleva;
    }

    public Integer getIdSlevy() {
        return idSlevy;
    }

    public void setIdSlevy(Integer idSlevy) {
        this.idSlevy = idSlevy;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public short getSleva() {
        return sleva;
    }

    public void setSleva(short sleva) {
        this.sleva = sleva;
    }

    @XmlTransient
    public Collection<Hoste> getHosteCollection() {
        return hosteCollection;
    }

    public void setHosteCollection(Collection<Hoste> hosteCollection) {
        this.hosteCollection = hosteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSlevy != null ? idSlevy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slevy)) {
            return false;
        }
        Slevy other = (Slevy) object;
        if ((this.idSlevy == null && other.idSlevy != null) || (this.idSlevy != null && !this.idSlevy.equals(other.idSlevy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Slevy[ idSlevy=" + idSlevy + " ]";
    }

}
