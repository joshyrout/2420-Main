package TP01;

import javax.swing.*;
import java.awt.*;

public class NavigationPanel extends JPanel {
    private GridBagConstraints gbc_panelNavigation;
    private GridBagLayout gbl_panelNavigation;
    private JPanel panelTravel, panelFilters;
    private JLabel labelDeviation, labelOrigin, labelDestination;
    public JComboBox<String> comboBoxOrigin, comboBoxDestination;
    public JCheckBox checkBoxThemeParks, checkBoxNationalParks;
    public JTextField textFieldUserInput;
    public JButton buttonGo;

    public NavigationPanel() {
        setSize(1280, 144);

        gbc_panelNavigation = new GridBagConstraints();
        gbc_panelNavigation.gridwidth = 2;
        gbc_panelNavigation.anchor = GridBagConstraints.NORTH;
        gbc_panelNavigation.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelNavigation.insets = new Insets(0, 0, 5, 5);
        gbc_panelNavigation.gridx = 0;
        gbc_panelNavigation.gridy = 0;

        gbl_panelNavigation = new GridBagLayout();
        gbl_panelNavigation.columnWidths = new int[]{100, 500, 0, 0};
        gbl_panelNavigation.rowHeights = new int[]{64, 0, 0};
        gbl_panelNavigation.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panelNavigation.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};

        buttonGo = new JButton("GO!");

        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 0;

        add(buttonGo, gbc_btnNewButton);
        createPanelTravel();
        createPanelFilters();
    }

    private void createPanelTravel() {
        panelTravel = new JPanel();
        GridBagConstraints gbc_panelTravel = new GridBagConstraints();
        gbc_panelTravel.anchor = GridBagConstraints.NORTHWEST;
        gbc_panelTravel.insets = new Insets(8, 2, 2, 8);
        gbc_panelTravel.gridx = 1;
        gbc_panelTravel.gridy = 0;
        add(panelTravel, gbc_panelTravel);
        GridBagLayout gbl_panelTravel = new GridBagLayout();
        gbl_panelTravel.columnWidths = new int[]{256, 256, 0};
        gbl_panelTravel.rowHeights = new int[]{32, 32, 0};
        gbl_panelTravel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_panelTravel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panelTravel.setLayout(gbl_panelTravel);

        labelOrigin = new JLabel("Start From:");
        GridBagConstraints gbc_labelOrigin = new GridBagConstraints();
        gbc_labelOrigin.anchor = GridBagConstraints.SOUTH;
        gbc_labelOrigin.fill = GridBagConstraints.HORIZONTAL;
        gbc_labelOrigin.insets = new Insets(0, 0, 5, 5);
        gbc_labelOrigin.gridx = 0;
        gbc_labelOrigin.gridy = 0;
        panelTravel.add(labelOrigin, gbc_labelOrigin);

        labelDestination = new JLabel("Finish At:");
        GridBagConstraints gbc_labelDestination = new GridBagConstraints();
        gbc_labelDestination.anchor = GridBagConstraints.SOUTH;
        gbc_labelDestination.insets = new Insets(0, 0, 5, 0);
        gbc_labelDestination.fill = GridBagConstraints.HORIZONTAL;
        gbc_labelDestination.gridx = 1;
        gbc_labelDestination.gridy = 0;
        panelTravel.add(labelDestination, gbc_labelDestination);

        comboBoxOrigin = new JComboBox();
        GridBagConstraints gbc_comboBoxOrigin = new GridBagConstraints();
        gbc_comboBoxOrigin.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxOrigin.anchor = GridBagConstraints.NORTH;
        gbc_comboBoxOrigin.insets = new Insets(0, 0, 0, 5);
        gbc_comboBoxOrigin.gridx = 0;
        gbc_comboBoxOrigin.gridy = 1;
        panelTravel.add(comboBoxOrigin, gbc_comboBoxOrigin);

        comboBoxDestination = new JComboBox();
        GridBagConstraints gbc_comboBoxDestination = new GridBagConstraints();
        gbc_comboBoxDestination.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxDestination.anchor = GridBagConstraints.NORTH;
        gbc_comboBoxDestination.gridx = 1;
        gbc_comboBoxDestination.gridy = 1;
        panelTravel.add(comboBoxDestination, gbc_comboBoxDestination);
    }

    private void createPanelFilters() {
        panelFilters = new JPanel();
        GridBagConstraints gbc_panelFilters = new GridBagConstraints();
        gbc_panelFilters.gridheight = 2;
        gbc_panelFilters.insets = new Insets(0, 0, 5, 0);
        gbc_panelFilters.fill = GridBagConstraints.BOTH;
        gbc_panelFilters.gridx = 2;
        gbc_panelFilters.gridy = 0;
        add(panelFilters, gbc_panelFilters);
        GridBagLayout gbl_panelFilters = new GridBagLayout();
        gbl_panelFilters.columnWidths = new int[]{210, 64, 0};
        gbl_panelFilters.rowHeights = new int[]{12, 12, 0};
        gbl_panelFilters.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_panelFilters.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panelFilters.setLayout(gbl_panelFilters);

        checkBoxThemeParks = new JCheckBox("Theme Parks");
        GridBagConstraints gbc_checkBoxThemeParks = new GridBagConstraints();
        gbc_checkBoxThemeParks.anchor = GridBagConstraints.WEST;
        gbc_checkBoxThemeParks.insets = new Insets(0, 0, 5, 5);
        gbc_checkBoxThemeParks.gridx = 0;
        gbc_checkBoxThemeParks.gridy = 0;
        panelFilters.add(checkBoxThemeParks, gbc_checkBoxThemeParks);

        labelDeviation = new JLabel("Maximum Distance off course");
        GridBagConstraints gbc_labelDeviation = new GridBagConstraints();
        gbc_labelDeviation.anchor = GridBagConstraints.WEST;
        gbc_labelDeviation.insets = new Insets(0, 0, 5, 0);
        gbc_labelDeviation.gridx = 1;
        gbc_labelDeviation.gridy = 0;
        panelFilters.add(labelDeviation, gbc_labelDeviation);

        checkBoxNationalParks = new JCheckBox("National Parks");
        GridBagConstraints gbc_checkBoxNationalParks = new GridBagConstraints();
        gbc_checkBoxNationalParks.anchor = GridBagConstraints.WEST;
        gbc_checkBoxNationalParks.insets = new Insets(0, 0, 0, 5);
        gbc_checkBoxNationalParks.gridx = 0;
        gbc_checkBoxNationalParks.gridy = 1;
        panelFilters.add(checkBoxNationalParks, gbc_checkBoxNationalParks);

        textFieldUserInput = new JTextField();
        GridBagConstraints gbc_textFieldUserInput = new GridBagConstraints();
        gbc_textFieldUserInput.anchor = GridBagConstraints.WEST;
        gbc_textFieldUserInput.gridx = 1;
        gbc_textFieldUserInput.gridy = 1;
        panelFilters.add(textFieldUserInput, gbc_textFieldUserInput);
        textFieldUserInput.setColumns(10);
    }

    public GridBagConstraints getPanelConstraints() {
        return gbc_panelNavigation;
    }

    public GridBagLayout getPanelLayout() {
        return gbl_panelNavigation;
    }

    public void addDestinationToComboBox(String s) {
        comboBoxOrigin.addItem(s);
        comboBoxDestination.addItem(s);
    }
}
