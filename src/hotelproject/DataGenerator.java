package hotelproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class DataGenerator {

    static int NUMBER = 100;
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
