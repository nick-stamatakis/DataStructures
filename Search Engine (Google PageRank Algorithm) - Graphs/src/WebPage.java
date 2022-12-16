import java.util.ArrayList;

/**
 *
 * Represents a hyperlinked document.
 *
 * @author Nicholas Stamatakis
 *
 */
public class WebPage {
    //Member Variables
    /**
     * URL for WebPage
     */
    private String URL;

    /**
     * An int to represent its position in the adjacency matrix
     */
    private int index;

    /**
     * An int to represent the rank (described later in the specs) of this WebPage
     */
    private int rank;

    /**
     * A Collection of Strings containing the keywords.
     * When we say keywords must be a "Collection" of Strings, we mean that it can be any
     * data structure you like that implements the Collection interface -
     * e.g. ArrayList, Vector, Array, LinkedList, etc.
     */
    private ArrayList<String> keywords;

    /**
     * Holds links for WebPage
     */
    private String links;

    //Constructors
    /**
     * No-arg constructor
     */
    public WebPage(){
        URL = "";
        index = 0;
        rank = 0;
        keywords = new ArrayList<>();
        links = "";
    }

    /**
     * Arg constructor
     */
    public WebPage(String URL1, int index1, int rank1, ArrayList<String> keywords1, String links1){
        URL = URL1;
        index = index1;
        rank = rank1;
        keywords = keywords1;
        links = links1;
    }

    //Getters and Setters

    /**
     * Getter for URL
     * @return
     * Current value of URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * Setter for URL
     * @param URL
     * Changes URL to method signature
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Getter for Index
     * @return
     * Current value of Index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for Index
     * @param index
     * Changes index to method signature
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter for rank
     * @return
     * Current value of rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Setter for rank
     * @param rank
     * Changes rank to method signature
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Getter for keywords
     * @return
     * current value of Keywords ArrayList
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    /**
     * Setter for keywords
     * @param keywords
     * Changes keywords to ArrayList in method signature
     */
    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Getter for links
     * @return
     * Current value of links
     */
    public String getLinks() {
        return links;
    }

    /**
     * Setter for links
     * @param links
     * Changes value of links to that in the method signature
     */
    public void setLinks(String links) {
        this.links = links;
    }

    //Methods

    /**
     * KeyWord Formatter.
     * @param keywords
     * ArrayList of keyWords
     * @return
     * Returns formatted String of KeyWords
     */
    public String keyWordsFormat(ArrayList<String> keywords){
        StringBuilder str = new StringBuilder();
        for (String keyword : keywords) {
            str.append(keyword).append(", ");
        }
        if (str.length() > 1) {
            str = new StringBuilder(str.substring(0, str.length() - 2));
        }
        return str.toString();
    }

    @Override
    /**
      Returns string of data members in tabular form.
      In the WebGraph class, when we want to print, we will determine all the Links for the URL
      "google.com" and can use the String.replace() method to replace your unique String
      (e.g. ***) with the correct values.
      @return
     * Formatted String
     */
    public String toString(){
        if (links == null)
            return String.format("  %-4s| %-19s|    %-5s|***| %-42s", index, URL, rank, keyWordsFormat(keywords));
        else
            return String.format("  %-4s| %-19s|    %-5s|%-18s| %-42s", index, URL, rank, links, keyWordsFormat(keywords));
    }
}