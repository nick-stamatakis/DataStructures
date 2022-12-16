import javax.swing.*;
import java.awt.*;
/**
 *
 * GUI EXTRA CREDIT.
 *
 * @author Nicholas Stamatakis
 *
 */
public class GUIExtraCredit extends WebGraph{
    /**
     * Uses JTable class to create a GUI for the Search Engine model created in previous classes
     * @param args
     * String[] that allows main method to create GUI
     * */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"Index",
                "URL",
                "Rank",
                "Links",
                "Keywords"};
        WebGraph graph = WebGraph.buildFromFiles("pages.txt", "links.txt");
        graph.updatePageRanks();
        Object[][] rowData = new Object[graph.MAX_PAGES][graph.MAX_PAGES];
        for (int i = 0; i < graph.getPages().size(); i++){
            for (int j = 0; j < graph.getPages().size(); j++){
                if (j == 0)
                    rowData[i][j] = graph.getPages().get(i).getIndex();
                else if (j == 1)
                    rowData[i][j] = graph.getPages().get(i).getURL();
                else if (j == 2)
                    rowData[i][j] = graph.getPages().get(i).getRank();
                else if (j == 3)
                    rowData[i][j] = graph.getPages().get(i).getLinks();
                else if (j == 4)
                    rowData[i][j] = graph.getPages().get(i).getKeywords();
            }
        }
        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
        table.setAutoCreateRowSorter(true);
    }
}