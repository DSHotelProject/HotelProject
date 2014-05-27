package hotelproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class DataGenerator {

    static int NUMBER = 10;
    static List<String> JMENA = loadAllLines("/resources/jmena.txt");
    static List<String> PRIJMENI = loadAllLines("/resources/prijmeni.txt");
    static List<String> MESTA = loadAllLines("/resources/mesta.txt");
    static List<String> ULICE = loadAllLines("/resources/ulice.txt");
    static Random RANDOM = new Random();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelProjectPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Adresy> adresy = generateAdresy(NUMBER);
        for (Adresy a : adresy) {
            em.persist(a);
        }
        List<Hoste> hoste = generateHoste(NUMBER);
        for (int i = 0; i < NUMBER; i++) {
            Hoste h = hoste.get(i);
            h.setAdresyIdAdresy(adresy.get(i));
            em.persist(h);
        }
        et.commit();
        generateReservations(hoste, 0.8);

        List<Hoste> allHoste = em.createNamedQuery("Hoste.findAll").getResultList();
        generateReservations(allHoste, 0.2);

        em.close();
        emf.close();
    }

    private static void generateReservations(List<Hoste> hoste, double probabilty) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelProjectPU");
        EntityManager em = emf.createEntityManager();
        for (Hoste host : hoste) {
            if (Math.random() < 1 - probabilty) {
                continue;
            }

            GregorianCalendar gc = new GregorianCalendar();
            int year = 2000 + (int) Math.round(Math.random() * (2015 - 2000));
            int dayInYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) - 1));
            gc.set(GregorianCalendar.YEAR, year);
            gc.set(GregorianCalendar.DAY_OF_YEAR, dayInYear);
            Date checkIn = gc.getTime();
            gc.add(GregorianCalendar.DATE, RANDOM.nextInt(14) + 1);
            Date checkOut = gc.getTime();

            EntityTransaction et = em.getTransaction();
            et.begin();
            Pokoje pokoj = getPokojFor(em, checkIn, checkOut);
            if (pokoj == null) {
                et.rollback();
            }

            Rezervace r = new Rezervace();
            int pocetOsob = pokoj.getPocetLuzek();
            pocetOsob -= RANDOM.nextInt(pocetOsob);
            r.setPocetOsob((short) pocetOsob);
            r.setDatumOd(checkIn);
            r.setDatumDo(checkOut);
            r.setVyseZalohy(BigDecimal.ZERO);
            r.setZalohaZapl(false);
            em.persist(r);
            em.flush();

            Collection<Rezervace> rezervace = new ArrayList<>();
            rezervace.add(r);
            host.setRezervaceCollection(rezervace);
            em.merge(host);
            em.flush();

            et.commit();
        }
    }

    private static Pokoje getPokojFor(EntityManager em, Date checkIn, Date checkOut) {
        Query queryPokoje = em.createNamedQuery("Pokoje.findByPocetLuzek");

        queryPokoje.setParameter("pocetLuzek", RANDOM.nextInt(5) + 1);
        List<Pokoje> listP = queryPokoje.getResultList();
        if (listP.isEmpty()) {
            return null;
        }

        Query queryRezervace = em.createNamedQuery("Rezervace.findAll");
        List<Rezervace> listRezervaci = queryRezervace.getResultList();

        for (Rezervace r : listRezervaci) {
            if (!((checkIn.before(r.getDatumOd()) && checkOut.before(r.getDatumOd()))
                    || (checkIn.after(r.getDatumDo()) && checkOut.after(r.getDatumDo())))) {
                for (Pokoje tmp : r.getPokojeCollection()) {
                    if (listP.contains(tmp)) {
                        listP.remove(tmp);
                    }
                }
            }
        }

        return listP.get(RANDOM.nextInt(listP.size()));
    }

    private static List<Hoste> generateHoste(int number) {
        List<Hoste> hoste = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            hoste.add(generateHost());
        }
        return hoste;
    }

    private static Hoste generateHost() {
        int rand = RANDOM.nextInt(JMENA.size());
        Hoste host = new Hoste();
        String jmeno = JMENA.get(rand);
        rand = RANDOM.nextInt(PRIJMENI.size());
        jmeno += " " + PRIJMENI.get(rand);
        host.setJmenoOsoby(jmeno);
        rand = RANDOM.nextInt(1000000000);
        host.setTelefon(Integer.toString(rand));
        String email = "";
        for (int i = 0; i < RANDOM.nextInt(10) + 1; i++) {
            email += (char) (RANDOM.nextInt(26) + 'a');
        }
        host.setEMail(email + "@email.cz");
        if (Math.random() < 0.5) {
            rand = RANDOM.nextInt(1000000000);
            host.setIc(Integer.toString(rand));
        }
        return host;
    }

    private static List<Adresy> generateAdresy(int number) {
        List<Adresy> adresy = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            adresy.add(generateAdresa());
        }
        return adresy;
    }

    private static Adresy generateAdresa() {
        int rand = RANDOM.nextInt(MESTA.size());
        String mesto = MESTA.get(rand);
        rand = RANDOM.nextInt(ULICE.size());
        String ulice = ULICE.get(rand);
        rand = RANDOM.nextInt(1000);
        String cp = Integer.toString(rand);
        return new Adresy(mesto, ulice, cp);
    }

    private static List<String> loadAllLines(String file) {
        List<String> lines = new ArrayList<>(200);
        final InputStream is = DataGenerator.class.getResourceAsStream(file);
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            System.err.println("Error reading file.");
        }
        return lines;
    }

}
