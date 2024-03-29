package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author reintjehard
 */
public class MainMenu extends JPanel {

    private JPanel passengerTab, luggageTab, accountTab, managementTab, managerSettingsTab, ManagerLuggageGraph;
    private view.InformationPanel informationPanel;
    private view.BarChart managerBarChart;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        String rights = main.LuggageTrackerTool2.getInstance().getCurrentUser().getPrivileges();

        switch (rights) {
            case "Manager":
                addManagerTabs();
                break;
            case "Admin":
                addAllTabs();
                break;
            case "Account Manager":
                addAccountManagerTabs();
                break;
            case "Service Employee":
                addServiceEmployeeTabs();
                break;

        }

    }

    private void addManagerTabs() {
        managementTab = new view.ManagerMenu();
        managerSettingsTab = new view.ManagerMenu();
        managerBarChart = new view.BarChart();
        jTabbedPane.addTab("Manager Pane", managementTab);
        jTabbedPane.addTab("Manager Graph", managerBarChart);

    }

    private void addAllTabs() {
        managementTab = new view.ManagerMenu();
        passengerTab = new view.Passenger();
        luggageTab = new view.Luggage();
        accountTab = new view.AccountManagement();
        informationPanel = new view.InformationPanel();
        managerBarChart = new view.BarChart();
        informationPanel.setVisible(true);
        add(informationPanel, BorderLayout.PAGE_END);
        jTabbedPane.addTab("Passenger", passengerTab);
        jTabbedPane.addTab("Luggage", luggageTab);
        jTabbedPane.addTab("Account Management", accountTab);
        jTabbedPane.addTab("Manager Pane", managementTab);
        jTabbedPane.addTab("Manager Graph", managerBarChart);
    }

    private void addServiceEmployeeTabs() {
        passengerTab = new view.Passenger();
        luggageTab = new view.Luggage();
        informationPanel = new view.InformationPanel();
        informationPanel.setVisible(true);
        add(informationPanel, BorderLayout.PAGE_END);
        jTabbedPane.addTab("Passenger", passengerTab);
        jTabbedPane.addTab("Luggage", luggageTab);
    }

    private void addAccountManagerTabs() {
        passengerTab = new view.Passenger();
        luggageTab = new view.Luggage();
        accountTab = new view.AccountManagement();

        jTabbedPane.addTab("Passenger", passengerTab);
        jTabbedPane.addTab("Luggage", luggageTab);
        jTabbedPane.addTab("Account Management", accountTab);
    }

    public view.Passenger getPassengerTab() {
        return (Passenger) passengerTab;
    }

    public view.Luggage getLuggageTab() {
        return (Luggage) luggageTab;
    }

    public JPanel getAccountTab() {
        return accountTab;
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public InformationPanel getInformationPanel() {
        return informationPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(254, 223, 162));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Main menu"));
        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane.setPreferredSize(new java.awt.Dimension(400, 650));
        jTabbedPane.setRequestFocusEnabled(false);
        add(jTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables

    public view.BarChart getManagerBarChart() {
        return managerBarChart;
    }

}
