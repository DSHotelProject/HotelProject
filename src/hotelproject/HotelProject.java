package hotelproject;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class HotelProject {
//neco
    public static void main(String[] args) {
//Entity manager and transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelProjectPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

//create new entity and persist it to the database
        Pokoje p1 = new Pokoje((short) 1, (short) 4, new BigDecimal(2500));
        tx.begin();
        em.persist(p1);
        tx.commit();
//finding by ID
        Pokoje p2 = em.find(Pokoje.class, 2);
        System.out.println(p2);
//search by cislo pokoje
        Query query = em.createQuery("from Pokoje p where p.cisloPokoje=:cislo");
        List<Pokoje> pList = query.setParameter("cislo", (short) 1).getResultList();
        for (Pokoje p : pList) {
            System.out.println(p);
        }

    }

}
