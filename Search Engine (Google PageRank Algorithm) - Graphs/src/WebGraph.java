import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * Organizes the WebPage objects as a directed graph.
 *
 * @author Nicholas Stamatakis
 *
 */
public class WebGraph {
    //Static Variables
    /**
     * Maximum Number of Pages
     */
    public int MAX_PAGES = 40;

    //Member Variables
    /**
     * Collection of WebPages member variable
     * (Note: This can be any class which implements the Collection interface)
     */
    private ArrayList<WebPage> pages;

    /**
     * Creates adjacency matrix for directed graph
     * instead of array, you may use ArrayList, Vector, etc.
     */
    private ArrayList<ArrayList<Integer>> edges;

    public int[][] initialAdjacencyMatrix =  new int[MAX_PAGES][MAX_PAGES];

    //Constructors

    /**
     * No-Arg Constructor
     */
    public WebGraph(){
        pages = new ArrayList<>(MAX_PAGES);
        edges = new ArrayList<>(MAX_PAGES);
    }

    /**
     * Arg-Constructor
     */
    public WebGraph(ArrayList<WebPage> pages1, ArrayList<ArrayList<Integer>> edges1){
        pages = pages1;
        edges = edges1;
    }

    /**
     * Getter for pages
     * @return
     * ArrayList of pages
     */
    public ArrayList<WebPage> getPages() {
        return pages;
    }

    /**
     * Setter for pages
     * @param pages
     * Return method signature pages ArrayList
     */
    public void setPages(ArrayList<WebPage> pages) {
        this.pages = pages;
    }

    /**
     * Getter for Edges
     * @return
     * Current value of Edges ArrayList
     */
    public ArrayList<ArrayList<Integer>> getEdges() {
        return edges;
    }

    /**
     * Setter for edges ArrayList
     * @param edges
     * Changes value to method signature
     */
    public void setEdges(ArrayList<ArrayList<Integer>> edges) {
        this.edges = edges;
    }
    //Static Method to build Graph

    /**
     * Constructs a WebGraph object using the indicated files as the source for pages and edges.
     * See the section "Reading Graph from File" below for information on how to construct the
     * graph using two files.
     * @param pagesFile
     * String of the relative path to the file containing the page information.
     * @param linksFile
     * String of the relative path to the file containing the link information.
     * @pre-condition
     * Both parameters reference text files which exist.
     * The files follow proper format as outlined in the "Reading Graph from File" section below.
     * @post-condition
     * A WebGraph has been constructed and initialized based on the text files.
     * @return
     * The WebGraph constructed from the text files.
     * @throws IllegalArgumentException
     * Thrown if either of the files does not reference a valid text file,
     * or if the files are not formatted correctly.
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile)
            throws IllegalArgumentException{
        /**
         * Create WebGraph Object
         */
        WebGraph graph = new WebGraph();
        try {
            //Scan pages
            Scanner scanner1 = new Scanner(new File("./" + pagesFile));
            //While loop for pagesFile
            while (scanner1.hasNextLine()){
                String line = scanner1.nextLine();
                line = line.trim();
                String[] NameAndKeywords = line.split(" ");
                if (!line.isEmpty() && NameAndKeywords.length > 0){
                    ArrayList<String> keywords = new ArrayList<>();
                    WebPage page = new WebPage();
                    page.setURL(NameAndKeywords[0]);
                    for (int i = 1; i < NameAndKeywords.length; i++){
                        keywords.add(NameAndKeywords[i]);
                    }
                    page.setKeywords(keywords);
                    page.setIndex(graph.pages.size());
                    graph.pages.add(page);
                }
            }
            //Prints Adjacency Matrix
//            WebGraph.print2DArray(graph);
            //Scan linksFile
            Scanner scanner2 = new Scanner(new File("./" + linksFile));
            while (scanner2.hasNextLine()){
                String line = scanner2.nextLine();
                line = line.trim();
                String[] sourceAndDestinations = line.split(" ");
                ArrayList<String> urls = graph.createURLArrayList();
                //Sets indices of links in adjacency matrix
                int index1 = urls.indexOf(sourceAndDestinations[0]);
                int index2 = urls.indexOf(sourceAndDestinations[1]);
//                System.out.println(index1 + " " + index2);
                //Changes indices of ArrayList
                if (index1 != -1 && index2 != -1){
                    graph.initialAdjacencyMatrix[index1][index2] = 1;
//                    graph.initialAdjacencyMatrix[index2][index1] = 1;
                }
            }
            //Initialize ArrayList to with new ArrayList
            for (int i = 0; i < graph.pages.size(); i++){
                graph.edges.add(new ArrayList<>());
            }
            //Copy over elements
            for (int i = 0; i < graph.pages.size(); i++){
                for (int j = 0; j < graph.pages.size(); j++){
                    if (graph.initialAdjacencyMatrix[i][j] == 1){
                        graph.edges.get(i).add(1);
                    }
                    else{
                        graph.edges.get(i).add(0);
                    }
                }
            }
            graph.createLinksStrings();
            //Prints Adjacency Matrix
//            WebGraph.printArrayList(graph);
        }
        catch (FileNotFoundException ex){
            throw new IllegalArgumentException("File not found.");
        }

        return graph;
    }

    /**
     * Adds a page to the WebGraph.
     * @param url
     * The URL of the webpage (must not already exist in the WebGraph).
     * @param keywords
     * The keywords associated with the WebPage.
     * @pre-condition
     * url is unique and does not exist as the URL of a WebPage already in the graph.
     * url and keywords are not null.
     * @post-condition
     * The page has been added to pages at index 'i' and links has been logically
     * extended to include the new row and column indexed by 'i'.
     * @throws IllegalArgumentException
     * If url is not unique and already exists in the graph, or if either argument is null.
     */
    public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
        ArrayList<String> urls = this.createURLArrayList();
        if (urls.contains(url) || url == null || keywords == null){
            throw new IllegalArgumentException();
        }
        //Adds Page
        this.pages.add(new WebPage(url, pages.size(), 0, keywords, ""));
        //Adds new row
        edges.add(new ArrayList<>());
        for (int i = 0; i < pages.size(); i++){
            edges.get(pages.size() - 1).add(0);
        }
        //Adds new values for possible links to previous ArrayLists
        for (int i = 0; i < pages.size() - 1; i++){
            edges.get(i).add(0);
        }
    }

    /**
     * Removes the WebPage from the graph with the given URL.
     *
     * When the page is removed, it's corresponding row and column must be removed from the
     * adjacency matrix. This can be accomplished by copying links[k][j+1] to links[k][j] and
     * links[j+1][k] to links[j][k] for 0 ≤ k < size(pages) and i ≤ j < size(pages)-1, where
     * i is the index of the WebPage being removed.
     *
     * @param url
     * The URL of the page to remove from the graph.
     * @post-condition
     * The WebPage with the indicated URL has been removed from the graph, and it's corresponding
     * row and column has been removed from the adjacency matrix.
     * All pages that have an index greater than the index that was removed should
     * decrease their index value by 1.
     * If url is null or could not be found in pages, the method ignores the
     * input and does nothing.
     */
    public void removePage(String url){
        ArrayList<String> urls = this.createURLArrayList();
        if (url == null || !urls.contains(url)){

        }
        else{
            int indexToRemove = urls.indexOf(url);
            //Remove links from page
            for (int i = 0; i < this.pages.size(); i++){
                this.edges.get(i).remove(indexToRemove);
            }
            //Remove row of links for given index
            this.edges.remove(indexToRemove);
            //Remove Page
            this.pages.remove(indexToRemove);
            //Update indices
            for (int i = indexToRemove; i < this.pages.size(); i++){
                this.pages.get(i).setIndex(this.pages.get(i).getIndex() - 1);
            }
            //Update links
            this.createLinksStrings();
        }
    }

    /**
     * Adds a link from the WebPage with the URL indicated
     * by source to the WebPage with the URL indicated by destination
     * @param source
     * the URL of the page which contains the hyperlink to destination.
     * @param destination
     * the URL of the page which the hyperlink points to.
     * @pre-condition
     * Both parameters reference WebPages which exist in the graph.
     * @throws IllegalArgumentException
     * If either of the URLs are null or could not be found in pages.
     */
    public void addLink(String source, String destination) throws IllegalArgumentException{
        ArrayList<String> urls = this.createURLArrayList();
        if (!urls.contains(source) || !urls.contains(destination)){
            throw new IllegalArgumentException();
            //Error: instagram.com could not be found in the WebGraph.
        }
        //Find indices to place 1 in Adjacency Matrix
        int index1 = urls.indexOf(source);
        int index2 = urls.indexOf(destination);
        if (this.edges.get(index1).get(index2) == 1){
            System.out.println("Error: link was already established");
        }
        //Set value to 1
        this.edges.get(index1).set(index2, 1);
        //Update Links
        this.createLinksStrings();
    }

    /**
     * Removes the link from WebPage with the URL indicated by source to the WebPage with the URL
     * indicated by destination.
     * @param source
     *  The URL of the WebPage to remove the link.
     * @param destination
     * The URL of the link to be removed.
     * @post-condition
     * The entry in links for the specified hyperlink has been set to 0 (no link).
     * If either of the URLs could not be found, the input is ignored and the method does nothing.
     */
    public void removeLink(String source, String destination){
        ArrayList<String> urls = this.createURLArrayList();
        //Find indices to place 1 in Adjacency Matrix
        int index1 = urls.indexOf(source);
        int index2 = urls.indexOf(destination);
        //Set value to 1
        this.edges.get(index1).set(index2, 0);
        //Update Links
        this.createLinksStrings();
    }

    /**
     * Searches for websites with certain keywords
     * @param keyword
     * keyword of choice to look for
     */
    public void searchForKeyWord(String keyword){
        ArrayList<String> urls = this.createURLArrayList();
        ArrayList<WebPage> keywordPages = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++){
            if (pages.get(i).getKeywords().contains(keyword)){
                keywordPages.add(pages.get(i));
            }
        }
        if (keywordPages.size() >= 1) {
            //Print header
            System.out.println("Rank   PageRank    URL\n");
            System.out.println("--------------------------------------------------------------------------------------");
            //Sort Lists
            RankComparator c = new RankComparator();
            keywordPages.sort(c);
            //Print the rest of the lines
            for (int i = 0; i < keywordPages.size(); i++) {
                System.out.println(this.printRowKeyWord(keywordPages.get(i), i + 1));
            }
        }
        else{
            System.out.println("No search results found for the keyword " + keyword + ".");
        }

    }

    /**
     * Gives String for each line with a certain keyword
     * @param page
     * WebPage Object
     * @param leftRank
     * Rank on left side of the table
     * @return
     * String corresponding to a WebPage with a certain keyword
     */
    public String printRowKeyWord(WebPage page, int leftRank){
        return String.format("  %-3s|    %-6s| %-10s", leftRank, page.getRank(), page.getURL());
    }

    /**
     * Sorts based off index in ascending order
     */
    public void sortBasedOffIndex(){
        IndexComparator c = new IndexComparator();
        pages.sort(c);
    }

    /**
     * Sorts based off url in ascending order
     */
    public void sortBasedOffUrl(){
        URLComparator c = new URLComparator();
        pages.sort(c);
    }

    /**
     * Sorts based off rank in descending order
     */
    public void sortBasedOffRank(){
        RankComparator c = new RankComparator();
        pages.sort(c);
    }


    /**
     * Calculates and assigns the PageRank for every page in the WebGraph
     * (see the PageRank Algorithm section for further detail).
     * This operation should be performed after ANY alteration of the graph structure
     * (adding/removing a link, adding/removing a page).
     * @post-condition
     * All WebPages in the graph have been assigned their proper PageRank.
     */
    public void updatePageRanks(){
        for (int i = 0; i < this.edges.size(); i++){
            int sumColumn = 0;
            for (int j = 0; j < this.edges.size(); j++){
                sumColumn += this.edges.get(j).get(i);
            }
            this.pages.get(i).setRank(sumColumn);
            this.pages.get(i).setRank(sumColumn);
        }
    }

    /**
     * Prints the WebGraph in tabular form (see sample I/O for more information).
     */
    public void printTable(WebGraph graph){
        //Add list of Links
        System.out.println("Index     URL               PageRank  Links               Keywords");
        System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i < graph.pages.size(); i++){
            System.out.println(graph.pages.get(i).toString());
        }
    }


    /**
     * Creates
     * @return
     * ArrayList of Strings containing all the urls added so far
     */
    public ArrayList<String> createURLArrayList(){
        ArrayList<String> urls = new ArrayList<>();
        for(int i = 0; i < this.pages.size(); i++){
            urls.add(this.pages.get(i).getURL());
        }
        return urls;
    }

    /**
     * Prints value of current Array
     * @param graph
     * Takes in current WebGraph Object
     */
    public static void print2DArray(WebGraph graph){
        for(int i = 0; i < graph.initialAdjacencyMatrix.length; i++){
            for(int j = 0; j < graph.initialAdjacencyMatrix.length; j++){
                System.out.print(graph.initialAdjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints value of current ArrayList
     * @param graph
     * Takes in current WebGraph Object
     */
    public static void printArrayList(WebGraph graph){
        for(int i = 0; i < graph.edges.size(); i++){
            for(int j = 0; j < graph.edges.get(i).size(); j++){
                System.out.print(graph.edges.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void createLinksStrings(){
        //Add list of Links
        ArrayList<String> links = new ArrayList<>();
        for (int i = 0; i < this.pages.size(); i++){
            links.add(new String());
        }
        //Add to link ArrayList
        for (int i = 0; i < this.pages.size(); i++){
            StringBuilder res = new StringBuilder();
            for (int j = 0; j < this.pages.size(); j++){
                if (this.edges.get(i).get(j) == 1){
                    res.append(j).append(", ");
                }
            }
            if (res.length() > 2)
                res = new StringBuilder(res.substring(0, res.length() - 2));
            this.pages.get(i).setLinks(res.toString());
            links.set(i, res.toString());
        }
    }
}