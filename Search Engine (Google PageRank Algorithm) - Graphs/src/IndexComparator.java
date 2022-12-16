import java.util.Comparator;
/**
 *
 * Comparator for Index.
 *
 * @author Nicholas Stamatakis
 *
 */
public class IndexComparator implements Comparator {
    public int compare(Object o1, Object o2){
        WebPage web1 = (WebPage) o1;
        WebPage web2 = (WebPage) o2;
        if (web1.getIndex() == web2.getIndex()){
            return 0;
        }
        else if (web1.getIndex() > web2.getIndex()){
            return 1;
        }
        else{
            return -1;
        }
    }
}