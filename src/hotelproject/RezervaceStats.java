package hotelproject;

/**
 *
 * @author Michal Stanke <stankmic@fel.cvut.cz>
 */
public class RezervaceStats {

    String obdobi;
    long count;

    public RezervaceStats(String obdobi, long count) {
        this.obdobi = obdobi;
        this.count = count;
    }
}
