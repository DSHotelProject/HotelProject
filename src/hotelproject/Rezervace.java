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
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Rezervace.findAll", query = "SELECT r FROM Rezervace r"),
    @NamedQuery(name = "Rezervace.findByIdRezervace", query = "SELECT r FROM Rezervace r WHERE r.idRezervace = :idRezervace"),
    @NamedQuery(name = "Rezervace.findByPocetOsob", query = "SELECT r FROM Rezervace r WHERE r.pocetOsob = :pocetOsob"),
    @NamedQuery(name = "Rezervace.findByDatumOd", query = "SELECT r FROM Rezervace r WHERE r.datumOd = :datumOd"),
    @NamedQuery(name = "Rezervace.findByDatumDo", query = "SELECT r FROM Rezervace r WHERE r.datumDo = :datumDo"),
    @NamedQuery(name = "Rezervace.findByVyseZalohy", query = "SELECT r FROM Rezervace r WHERE r.vyseZalohy = :vyseZalohy"),
    @NamedQuery(name = "Rezervace.findByZalohaZapl", query = "SELECT r FROM Rezervace r WHERE r.zalohaZapl = :zalohaZapl"),
    @NamedQuery(name = "Rezervace.findByPoznamka", query = "SELECT r FROM Rezervace r WHERE r.poznamka = :poznamka")})
public class Rezervace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rezervace")
    private Integer idRezervace;
    @Basic(optional = false)
    @Column(name = "pocet_osob")
    private short pocetOsob;
    @Basic(optional = false)
    @Column(name = "datum_od")
    @Temporal(TemporalType.DATE)
    private Date datumOd;
    @Basic(optional = false)
    @Column(name = "datum_do")
    @Temporal(TemporalType.DATE)
    private Date datumDo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vyse_zalohy")
    private BigDecimal vyseZalohy;
    @Basic(optional = false)
    @Column(name = "zaloha_zapl")
    private boolean zalohaZapl;
    private String poznamka;
    @ManyToMany(mappedBy = "rezervaceCollection")
    private Collection<Pokoje> pokojeCollection;
    @ManyToMany(mappedBy = "rezervaceCollection")
    private Collection<Hoste> hosteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rezervaceIdRezervace")
    private Collection<Ucty> uctyCollection;

    public Rezervace() {
    }

    public Rezervace(Integer idRezervace) {
        this.idRezervace = idRezervace;
    }

    public Rezervace(Integer idRezervace, short pocetOsob, Date datumOd, Date datumDo, BigDecimal vyseZalohy, boolean zalohaZapl) {
        this.idRezervace = idRezervace;
        this.pocetOsob = pocetOsob;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.vyseZalohy = vyseZalohy;
        this.zalohaZapl = zalohaZapl;
    }

    public Integer getIdRezervace() {
        return idRezervace;
    }

    public void setIdRezervace(Integer idRezervace) {
        this.idRezervace = idRezervace;
    }

    public short getPocetOsob() {
        return pocetOsob;
    }

    public void setPocetOsob(short pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public BigDecimal getVyseZalohy() {
        return vyseZalohy;
    }

    public void setVyseZalohy(BigDecimal vyseZalohy) {
        this.vyseZalohy = vyseZalohy;
    }

    public boolean getZalohaZapl() {
        return zalohaZapl;
    }

    public void setZalohaZapl(boolean zalohaZapl) {
        this.zalohaZapl = zalohaZapl;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    @XmlTransient
    public Collection<Pokoje> getPokojeCollection() {
        return pokojeCollection;
    }

    public void setPokojeCollection(Collection<Pokoje> pokojeCollection) {
        this.pokojeCollection = pokojeCollection;
    }

    @XmlTransient
    public Collection<Hoste> getHosteCollection() {
        return hosteCollection;
    }

    public void setHosteCollection(Collection<Hoste> hosteCollection) {
        this.hosteCollection = hosteCollection;
    }

    @XmlTransient
    public Collection<Ucty> getUctyCollection() {
        return uctyCollection;
    }

    public void setUctyCollection(Collection<Ucty> uctyCollection) {
        this.uctyCollection = uctyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRezervace != null ? idRezervace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervace)) {
            return false;
        }
        Rezervace other = (Rezervace) object;
        if ((this.idRezervace == null && other.idRezervace != null) || (this.idRezervace != null && !this.idRezervace.equals(other.idRezervace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotelproject.Rezervace[ idRezervace=" + idRezervace + " ]";
    }

}
