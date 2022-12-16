import java.util.Comparator;
/**
 *
 * Comparator for Rank
 *
 * @author Nicholas Stamatakis
 *
 */
public class RankComparator implements Comparator {
    public int compare(Object o1, Object o2){
        WebPage web1 = (WebPage) o1;
        WebPage web2 = (WebPage) o2;
        if (web1.getRank() == web2.getRank()){
            return 0;
        }
        else if (web1.getRank() > web2.getRank()){
            return -1;
        }
        else{
            return 1;
        }
    }
}