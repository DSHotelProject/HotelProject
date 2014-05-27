package hotelproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class Statistiky {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelProjectPU");
        EntityManager em = emf.createEntityManager();
        prumernaObsazenostPokoje(em);
        System.out.println("-----");
        nejviceZakaznikuZMesta(em);
        System.out.println("-----");
        rezervaceVLetech(em);
        System.out.println("-----");
        rezervaceVMesicich(em);
        em.close();
        emf.close();
    }

    private static void prumernaObsazenostPokoje(EntityManager em) {
        long obsazeno = 0, kapacita = 0;
        Query queryRezervace = em.createNamedQuery("Rezervace.findAll");
        List<Rezervace> rezervace = queryRezervace.getResultList();
        for (Rezervace r : rezervace) {
            obsazeno += r.getPocetOsob();
            for (Pokoje p : r.getPokojeCollection()) {
                kapacita += p.getPocetLuzek();
            }
        }
        double obsazenost = ((double) obsazeno) / kapacita;
        System.out.println("Prumerna obsazenost pokoju je: " + obsazenost * 100 + " %");
    }

    private static void nejviceZakaznikuZMesta(EntityManager em) {
        Query query = em.createQuery("SELECT NEW hotelproject.MestaStats(a.mesto, COUNT(a.mesto)) FROM Adresy a GROUP BY a.mesto HAVING COUNT(a.mesto) > 1 ORDER BY COUNT(a.mesto) DESC");
        em.getTransaction().begin();
        List<MestaStats> l = query.getResultList();
        em.getTransaction().commit();
        for (MestaStats m : l) {
            System.out.printf("%s %d\n", m.name, m.count);
        }
    }

    private static void rezervaceVLetech(EntityManager em) {
        Query query = em.createQuery("SELECT NEW hotelproject.RezervaceStats(CAST(r.datumOd AS VARCHAR(4)), COUNT(CAST(r.datumOd AS VARCHAR(4)))) FROM Rezervace r GROUP BY CAST(r.datumOd AS VARCHAR(4)) ORDER BY CAST(r.datumOd AS VARCHAR(4)) DESC");
        // YEAR(r.datumOd) - nefunguje
        em.getTransaction().begin();
        List<RezervaceStats> l = query.getResultList();
        em.getTransaction().commit();
        for (RezervaceStats r : l) {
            System.out.printf("%s %d\n", r.obdobi, r.count);
        }
    }

    private static void rezervaceVMesicich(EntityManager em) {
        Query query = em.createQuery("SELECT NEW hotelproject.RezervaceStats(SUBSTRING(CAST(r.datumOd AS VARCHAR(7)),6,2), COUNT(SUBSTRING(CAST(r.datumOd AS VARCHAR(7)),6,2))) FROM Rezervace r GROUP BY CAST(r.datumOd AS VARCHAR(7)) ORDER BY SUBSTRING(CAST(r.datumOd AS VARCHAR(7)),6,2) DESC");
        // MONTH(r.datumOd) nefunguje
        // GROUP BY SUBSTRING(CAST(r.datumOd AS VARCHAR(7)),6,2) nefunguje
        em.getTransaction().begin();
        List<RezervaceStats> l = query.getResultList();
        em.getTransaction().commit();
        for (RezervaceStats r : l) {
            System.out.printf("%s %d\n", r.obdobi, r.count);
        }
    }

}
