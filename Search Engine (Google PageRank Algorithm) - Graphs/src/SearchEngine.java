import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Will initialize a WebGraph from the appropriate text files and allow the user to search
 * for keywords in the graph.
 * Class will provide functionality to add/remove pages to/from the graph, as well as
 * alter the hyperlinks between pages in the graph.
 *
 * @author Nicholas Stamatakis
 *
 */
public class SearchEngine {
    //Static Variables
    /**
     * Pages File
     */
    public static final String PAGES_FILE = "pages.txt";

    /**
     * Links File
     */
    public static final String LINKS_FILE = "links.txt";

    //Member Variables
    /**
     * WebGraph Object
     */
    private WebGraph web;

    /**
     * Main method that allows user to interact with the program
     * @param args
     * takes in args (String array) in methods signature
     *
     * Notes:
     * You should make sure that the graph is sorted by index before making any changes
     * to the structure (failing to do so will lead to unintended bugs). Also, be sure
     * to update all the PageRanks for pages after any alterations by using the
     * updatePageRanks() method for the graph.
     *
     * After choosing the print option, the user should be given another set of menu options.
     * Display the results based on the secondary menu option selected.
     */
    public static void main(String[] args) {
        //Create SearchEngine Object
        SearchEngine engine = new SearchEngine();
        //Create Scanner Object
        Scanner in = new Scanner(System.in);
    // Load WebGraph from file. PageRanks should be calculated and initialized.
        engine.web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
        engine.web.updatePageRanks();
        System.out.println("Loading WebGraph data...\n" +
                "Success!");
        String input;
        do{
            input = SearchEngine.printMenu(in);
            if (input.equals("AP")){
                String url = engine.AP1(in);
                try{
                    engine.AP2(in, engine, url);
                }
                catch(IllegalArgumentException ex){
                    System.out.println("Error: " + url + " already exists");
                }
            }
            else if (input.equals("RP")){
                engine.RP(in, engine);
            }
            else if (input.equals("AL")){
                String sourceUrl = engine.AL1(in);
                try{
                    engine.AL2(in, engine, sourceUrl);
                }
                catch(IllegalArgumentException ex){
                    System.out.println("Error: " + sourceUrl + " could not be found");
                }
            }
            else if (input.equals("RL")){
                engine.RL(in, engine);
            }
            else if (input.equals("P")){
                input = SearchEngine.subMenu(in);
                if (input.equals("I")){
                    engine.web.sortBasedOffIndex();
                    engine.web.printTable(engine.web);
                }
                else if(input.equals("U")){
                    engine.web.sortBasedOffUrl();
                    engine.web.printTable(engine.web);
                }
                else if (input.equals("R")){
                    engine.web.sortBasedOffRank();
                    engine.web.printTable(engine.web);
                }
                else{
                    System.out.println("Please try again. A valid input from the sub-menu is needed.");
                }
            }
            else if (input.equals("S")){
                System.out.println("Search keyword: ");
                String keyword = in.nextLine();
                String keyword2 = in.nextLine();
                engine.web.searchForKeyWord(keyword2);
                engine.web.updatePageRanks();
            }
            else if (input.equals("Q")){
                System.out.println("\nGoodbye.");
            }
            else{
                System.out.println("Please try again. A valid input from the menu is needed.");
            }

        }
        while (!input.equals("Q"));
    }

    /**
     * Prints General Menu
     * @param in
     * Scanner Object
     * @return
     * String with corresponding user answer
     */
    public static String printMenu(Scanner in){
        System.out.println("\nMenu:");
        System.out.println("    (AP) - Add a new page to the graph.\n" +
                "    (RP) - Remove a page from the graph.\n" +
                "    (AL) - Add a link between pages in the graph.\n" +
                "    (RL) - Remove a link between pages in the graph.\n" +
                "    (P)  - Print the graph.\n" +
                "    (S)  - Search for pages with a keyword.\n" +
                "    (Q)  - Quit.\n");
        System.out.print("Please select an option: ");
        String answer = in.next();
        return answer.toUpperCase();
    }

    /**
     * Print subMenu
     * @param in
     * Scanner Object
     * @return
     * String with answer to menu
     */
    public static String subMenu(Scanner in){
        System.out.println("\n    (I) Sort based on index (ASC)\n" +
                "    (U) Sort based on URL (ASC)\n" +
                "    (R) Sort based on rank (DSC)\n");
        System.out.print("Please select an option: \n");
        String answer = in.next();
        System.out.println();
        return answer.toUpperCase();
    }

    /**
     * Add a new page to the graph part 1
     * @param in
     * Scanner Object
     */
    public String AP1(Scanner in){
        //Take in user input
        System.out.println("Enter a URL: ");
        String url = in.nextLine();
        String url2 = in.nextLine();
        return url2;
    }

    /**
     * Add page part 2
     * @param in
     * Scanner Object
     * @param engine
     * Search Engine Object
     * @return
     * String so that error message can be shown
     */
    public String AP2(Scanner in, SearchEngine engine, String url2){
        System.out.println("Enter keywords (space-separated): ");
        String keywordString = in.nextLine();
        keywordString = keywordString.trim();
        String[] keywordsArray = keywordString.split(" ");
        ArrayList<String> keywordsArrayList = new ArrayList<String>();
        keywordsArrayList.addAll(Arrays.asList(keywordsArray));
        //Add page
        engine.web.addPage(url2, keywordsArrayList);
        //Update Rank of new page
        engine.web.updatePageRanks();
        System.out.println("\n" + url2 + " successfully added to the WebGraph!");
        return url2;
    }

    /**
     * Removes a page from the graph.
     * @param in
     * Scanner Object
     * @param engine
     * Search Engine Object
     */
    public void RP(Scanner in, SearchEngine engine){
        System.out.println("Enter a URL: ");
        String url = in.next();
        engine.web.removePage(url);
        engine.web.updatePageRanks();
        System.out.println("\n" + url + " has been removed from the graph!");
    }

    /**
     * Adds a link part 1
     * @param in
     * Scanner Object
     * @return
     * String with url
     */
    public String AL1(Scanner in){
        System.out.println("Enter a source URL: ");
        String sourceUrl = in.nextLine();
        String sourceUrl2 = in.nextLine();
        return sourceUrl2;
    }

    /**
     * Adds a link part2
     * @param in
     * Scanner Object
     * @param engine
     * SearchEngine Object
     * @param sourceUrl2
     * String for SourceUrl
     * @return
     * String with URl
     */
    public String AL2(Scanner in, SearchEngine engine, String sourceUrl2){
        System.out.println("Enter a destination URL: ");
        String destinationUrl = in.nextLine();
        engine.web.addLink(sourceUrl2, destinationUrl);
        engine.web.updatePageRanks();
        System.out.println("\nLink successfully added from " + sourceUrl2 + " to " + destinationUrl + "!");
        return sourceUrl2;
    }

    /**
     * Removes a link
     * @param in
     * Scanner Object
     * @param engine
     * Search Engine Object
     */
    public void RL(Scanner in, SearchEngine engine){
        System.out.println("Enter a source URL: ");
        String sourceUrl = in.nextLine();
        String sourceUrl2 = in.nextLine();
        System.out.println("Enter a destination URL: ");
        String destinationUrl = in.nextLine();
        engine.web.removeLink(sourceUrl2, destinationUrl);
        engine.web.updatePageRanks();
        System.out.println("\nLink removed from " + sourceUrl2 + " to " + destinationUrl + "!");
    }
}