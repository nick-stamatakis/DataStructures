import java.util.Comparator;
/**
 *
 * Comparator for URL.
 *
 * @author Nicholas Stamatakis
 *
 */
public class URLComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        WebPage web1 = (WebPage) o1;
        WebPage web2 = (WebPage) o2;
        return (web1.getURL().compareTo(web2.getURL()));
    }
}