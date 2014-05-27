package hotelproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class TEST_querySpeedCompare {

    public static void main(String[] args) throws ParseException {
        Long start;
        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
        final Date checkInDate = dateFormat.parse("01/01/2013"),
                checkOutDate = dateFormat.parse("01/02/2013");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelProjectPU");
        EntityManager em = emf.createEntityManager();

        start = System.currentTimeMillis();
        criteriaAPI(checkInDate, checkOutDate, em);
        System.out.println("Criteria API: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        inApp(checkInDate, checkOutDate, em);
        System.out.println("In application: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        inAppJPA(checkInDate, checkOutDate, em);
        System.out.println("In application (JPA): " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        inAppJPANamedQuery(checkInDate, checkOutDate, em);
        System.out.println("In application (JPA Named Query): " + (System.currentTimeMillis() - start) + " ms");

        em.close();
        emf.close();
    }

    private static void criteriaAPI(Date checkInDate, Date checkOutDate, EntityManager em) {
        Query queryPokoje = em.createNamedQuery("Pokoje.findByPocetLuzek");
        queryPokoje.setParameter("pocetLuzek", 3);
        List<Pokoje> listP = queryPokoje.getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rezervace> criteriaQuery = cb.createQuery(Rezervace.class);
        Root<Pokoje> root = criteriaQuery.from(Pokoje.class);
        criteriaQuery.where(cb.equal(root.get(Pokoje_.pocetLuzek), 3));
        CollectionJoin<Pokoje, Rezervace> joined = root.join(Pokoje_.rezervaceCollection);
        criteriaQuery = criteriaQuery.select(joined);

        TypedQuery<Rezervace> query = em.createQuery(criteriaQuery);
        List<Rezervace> listRezervaci = query.getResultList();

        for (Rezervace r : listRezervaci) {
            if (!((checkInDate.before(r.getDatumOd()) && checkOutDate.before(r.getDatumOd()))
                    || (checkInDate.after(r.getDatumDo()) && checkOutDate.after(r.getDatumDo())))) {
                for (Pokoje tmp : r.getPokojeCollection()) {
                    listP.remove(tmp);
                }
            }
        }
    }

    private static void inApp(Date checkInDate, Date checkOutDate, EntityManager em) {
        Query queryPokoje = em.createNamedQuery("Pokoje.findByPocetLuzek");
        queryPokoje.setParameter("pocetLuzek", 3);
        List<Pokoje> listP = queryPokoje.getResultList();

        Query queryRezervace = em.createNamedQuery("Rezervace.findAll");
        List<Rezervace> listRezervaci = queryRezervace.getResultList();

        for (Rezervace r : listRezervaci) {
            if (!((checkInDate.before(r.getDatumOd()) && checkOutDate.before(r.getDatumOd()))
                    || (checkInDate.after(r.getDatumDo()) && checkOutDate.after(r.getDatumDo())))) {
                for (Pokoje tmp : r.getPokojeCollection()) {
                    listP.remove(tmp);
                }
            }
        }
    }

    private static void inAppJPA(Date checkInDate, Date checkOutDate, EntityManager em) {
        Query queryPokoje = em.createNamedQuery("Pokoje.findByPocetLuzek");
        queryPokoje.setParameter("pocetLuzek", 3);
        List<Pokoje> listP = queryPokoje.getResultList();

        List<Rezervace> listRezervaci = new ArrayList<>();
        for (Pokoje p : listP) {
            listRezervaci.addAll(p.getRezervaceCollection());
        }

        for (Rezervace r : listRezervaci) {
            if (!((checkInDate.before(r.getDatumOd()) && checkOutDate.before(r.getDatumOd()))
                    || (checkInDate.after(r.getDatumDo()) && checkOutDate.after(r.getDatumDo())))) {
                for (Pokoje tmp : r.getPokojeCollection()) {
                    listP.remove(tmp);
                }
            }
        }
    }

    private static void inAppJPANamedQuery(Date checkInDate, Date checkOutDate, EntityManager em) {
        Query queryPokoje = em.createNamedQuery("Pokoje.findByPocetLuzek");
        queryPokoje.setParameter("pocetLuzek", 3);
        List<Pokoje> listP = queryPokoje.getResultList();

        Query queryRezervace = em.createNamedQuery("Rezervace.findByDateRange");
        queryRezervace.setParameter("datumOd", checkInDate);
        queryRezervace.setParameter("datumDo", checkOutDate);
        List<Rezervace> listRezervaci = queryRezervace.getResultList();

        for (Rezervace r : listRezervaci) {
            if (!((checkInDate.before(r.getDatumOd()) && checkOutDate.before(r.getDatumOd()))
                    || (checkInDate.after(r.getDatumDo()) && checkOutDate.after(r.getDatumDo())))) {
                for (Pokoje tmp : r.getPokojeCollection()) {
                    listP.remove(tmp);
                }
            }
        }
    }

}
