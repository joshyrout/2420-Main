package TP01;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import edu.princeton.cs.algs4.*;
import java.util.*;
import java.util.List;
import java.util.Vector;

public class ParkJourneyGUI {

    private static List<Park> parkList;
    private List<Locations> locations;
    private JFrame frame;
    private JPanel panelMain, panelRoute, panelMap;
    private NavigationPanel panelNavigation;
    private JLabel  labelMap, labelRoute;
    private BufferedImage img = null;
    private JTable tableRoute;
    private JScrollPane scrollPaneRoute;
    private ParkJourney parkJourney;
    private String filename = "resources/National Parks.csv";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ParkJourneyGUI window = new ParkJourneyGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ParkJourneyGUI() {
        initialize();
    }

    private static List<Park> readPark(String fileName) {
        In in = new In(fileName);
        List<Park> p = new ArrayList<>();
        int count = 0;
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            double lat = Double.parseDouble(line[2]);
            double lon = Double.parseDouble(line[3]);
            Point2D point = new Point2D(lat, lon);
            p.add(new Park(line[0] + " " + line[1], count++, ParkType.National, point));
        }
        return p;
    }
    
    private void createJourney(Iterable<Park> ip) {
        mapReset();
        DefaultTableModel tableModel;
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Park");
        Vector<Vector<String>> data = new Vector<>();
        for (Park p : ip) {
            locations.add(new Locations(p.getLocation()));
            // data of the table
            Vector<String> vector = new Vector<>();
            vector.add(p.getName());
            data.add(vector);
        }
        for (Locations l : locations)
            labelMap.add(l);
         tableModel = new DefaultTableModel(data, columnNames);
        tableRoute.setModel(tableModel);
        scrollPaneRoute = new JScrollPane(tableRoute);
        panelRoute.add(scrollPaneRoute, BorderLayout.CENTER);
        tableRoute.setFillsViewportHeight(true);
        tableRoute.setAutoCreateRowSorter(true);

        panelRoute.validate();
        panelRoute.repaint();
        scrollPaneRoute.repaint();
        tableRoute.repaint();
        labelMap.validate();
        labelMap.repaint();
    }

    private void mapReset() {
        labelMap.removeAll();
        if (panelRoute.getComponentCount() > 1)
            panelRoute.remove(scrollPaneRoute);
        labelMap.validate();
        labelMap.repaint();
        locations = new ArrayList<>();
        tableRoute = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelRoute.repaint();
        panelMap.repaint();
    }

    private void initializeParkInfo() {
        parkList = readPark(filename);
        for (Park p : parkList)
            panelNavigation.addDestinationToComboBox(p.getName());
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        parkJourney = new ParkJourney(filename);
        frame = new JFrame();
        frame.setSize(new Dimension(1280, 720));
        frame.setResizable(false);
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMain = new JPanel();
        frame.getContentPane().add(panelMain, BorderLayout.CENTER);
        GridBagLayout gbl_panelMain = new GridBagLayout();
        gbl_panelMain.columnWidths = new int[]{1024, 256};
        gbl_panelMain.rowHeights = new int[]{104, 420, 0};
        gbl_panelMain.columnWeights = new double[]{0.0, 0.0};
        gbl_panelMain.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panelMain.setLayout(gbl_panelMain);

        panelNavigation = new NavigationPanel();
        panelMain.add(panelNavigation, panelNavigation.getPanelConstraints());
        panelNavigation.setLayout(panelNavigation.getPanelLayout());
        initializeParkInfo();
        panelNavigation.buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelNavigation.checkBoxNationalParks.isSelected();
                panelNavigation.checkBoxThemeParks.isSelected();
                String orig = panelNavigation.comboBoxOrigin.getSelectedItem().toString();
                String dest = panelNavigation.comboBoxDestination.getSelectedItem().toString();

//                String user = panelNavigation.textFieldUserInput.getText();
//                StringBuilder sb = new StringBuilder();
//                if (user != "") {
//                    char[] chars = user.toCharArray();
//                    for (char c : chars) {
//                        if (c >= '0' && c <= '9')
//                            sb.append(c);
//                    }
//                }
//                int input = 0;
//                if (sb.toString() == "" || sb.toString() == null)
//                    input = 30;
//                else {
//                    try {
//                        input = Integer.parseInt(sb.toString());
//                    } catch (NumberFormatException exc) {
//                        StdOut.println("input {" + sb.toString() + "}");
//                        StdOut.println("Invalid string");
//                    }
//                }
//                if (input == 0)
//                    input = 30;


                Iterable<Park> it = parkJourney.shortestPath(orig, dest);
                createJourney(it);
            }
        });

        panelMap = new JPanel();
        panelMap.setSize(1024, 576);
        GridBagConstraints gbc_panelMap = new GridBagConstraints();
        gbc_panelMap.anchor = GridBagConstraints.WEST;
        gbc_panelMap.fill = GridBagConstraints.VERTICAL;
        gbc_panelMap.insets = new Insets(0, 0, 0, 5);
        gbc_panelMap.gridx = 0;
        gbc_panelMap.gridy = 1;
        panelMain.add(panelMap, gbc_panelMap);

        labelMap = new JLabel("Map Missing!");
        labelMap.setSize(panelMap.getSize());
        panelMap.add(labelMap);

        panelRoute = new JPanel();
        panelRoute.setForeground(Color.RED);
        panelRoute.setBackground(Color.RED);
        panelRoute.setSize(256, 576);
        GridBagConstraints gbc_panelRoute = new GridBagConstraints();
        gbc_panelRoute.insets = new Insets(0, 0, 0, 5);
        gbc_panelRoute.fill = GridBagConstraints.BOTH;
        gbc_panelRoute.gridx = 1;
        gbc_panelRoute.gridy = 1;
        panelMain.add(panelRoute, gbc_panelRoute);
        panelRoute.setLayout(new BorderLayout(0, 0));

        labelRoute = new JLabel("Route");
        labelRoute.setHorizontalAlignment(SwingConstants.CENTER);
        panelRoute.add(labelRoute, BorderLayout.NORTH);

        try {
            img = ImageIO.read(new File("resources/Map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg;
        ImageIcon imageIcon;
        if (img != null) {
            dimg = img.getScaledInstance(labelMap.getWidth(), labelMap.getHeight(), Image.SCALE_FAST);
            imageIcon = new ImageIcon(dimg);
            labelMap.setIcon(imageIcon);
            labelMap.setText(null);
        }
    }
}
