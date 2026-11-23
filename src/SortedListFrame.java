import javax.swing.*;
import java.awt.*;

/**
 * Swing GUI for the SortedList lab.
 * Add Strings (kept in sorted order) and search for them with binary search.
 */
public class SortedListFrame extends JFrame
{
    private SortedList sortedList = new SortedList();

    private JTextField inputTF;
    private JTextArea displayTA;

    public SortedListFrame()
    {
        setTitle("Sorted List - Binary Search");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPnl = new JPanel(new BorderLayout(10, 10));
        mainPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPnl.add(createTitlePanel(), BorderLayout.NORTH);
        mainPnl.add(createDisplayPanel(), BorderLayout.CENTER);
        mainPnl.add(createControlPanel(), BorderLayout.SOUTH);

        add(mainPnl);
        setVisible(true);
    }

    private JPanel createTitlePanel()
    {
        JPanel titlePnl = new JPanel();
        JLabel titleLbl = new JLabel("Sorted List (Binary Search)");
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        titlePnl.add(titleLbl);
        return titlePnl;
    }

    private JScrollPane createDisplayPanel()
    {
        displayTA = new JTextArea();
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroller = new JScrollPane(displayTA);
        scroller.setBorder(BorderFactory.createTitledBorder("List Operations"));
        return scroller;
    }

    private JPanel createControlPanel()
    {
        JPanel controlPnl = new JPanel(new BorderLayout(5, 5));

        JPanel inputPnl = new JPanel(new BorderLayout(5, 5));
        inputPnl.add(new JLabel("Enter a String: "), BorderLayout.WEST);
        inputTF = new JTextField();
        inputTF.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputTF.addActionListener(e -> addElement());   // Enter key = Add
        inputPnl.add(inputTF, BorderLayout.CENTER);

        JPanel btnPnl = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear List");
        JButton quitBtn = new JButton("Quit");

        addBtn.addActionListener(e -> addElement());
        searchBtn.addActionListener(e -> searchElement());
        clearBtn.addActionListener(e -> clearList());
        quitBtn.addActionListener(e -> System.exit(0));

        btnPnl.add(addBtn);
        btnPnl.add(searchBtn);
        btnPnl.add(clearBtn);
        btnPnl.add(quitBtn);

        controlPnl.add(inputPnl, BorderLayout.NORTH);
        controlPnl.add(btnPnl, BorderLayout.SOUTH);
        return controlPnl;
    }

    private String readInput()
    {
        String text = inputTF.getText().trim();
        if (text.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter a String first.");
            return null;
        }
        return text;
    }

    private void addElement()
    {
        String value = readInput();
        if (value == null) return;

        int index = sortedList.add(value);
        displayTA.append("ADD \"" + value + "\" -> inserted at index " + index + "\n");
        displayTA.append("    List: " + sortedList + "\n\n");

        inputTF.setText("");
        inputTF.requestFocus();
    }

    private void searchElement()
    {
        String value = readInput();
        if (value == null) return;

        int index = sortedList.binarySearch(value);

        if (index < sortedList.size() && sortedList.get(index).equals(value))
        {
            displayTA.append("SEARCH \"" + value + "\" -> FOUND \""
                    + sortedList.get(index) + "\" at index " + index + "\n\n");
        }
        else
        {
            displayTA.append("SEARCH \"" + value + "\" -> NOT FOUND. It would be at index "
                    + index + describePosition(index) + "\n\n");
        }

        inputTF.setText("");
        inputTF.requestFocus();
    }

    /** Describes where a missing element would go, e.g. (between "apple" and "kiwi"). */
    private String describePosition(int index)
    {
        if (sortedList.isEmpty())
        {
            return " (the list is empty)";
        }
        if (index == 0)
        {
            return " (before \"" + sortedList.get(0) + "\")";
        }
        if (index == sortedList.size())
        {
            return " (after \"" + sortedList.get(index - 1) + "\")";
        }
        return " (between \"" + sortedList.get(index - 1) + "\" and \""
                + sortedList.get(index) + "\")";
    }

    private void clearList()
    {
        sortedList.clear();
        displayTA.append("CLEAR -> list is now empty\n\n");
        inputTF.requestFocus();
    }
}
