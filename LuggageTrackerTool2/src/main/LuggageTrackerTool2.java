package main;

import connectivity.DatabaseManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Luggage;
import model.Passenger;
import model.LuggageDAO;
import view.MainMenu;
import view.SeachBar;
import view.SideBar;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 * @version 1
 */
public final class LuggageTrackerTool2 {

    // Define frame width, height and name
    /**
     *
     */
    public static final int FRAME_WIDTH = 755;

    /**
     *
     */
    public static final int FRAME_HEIGHT = 480;

    /**
     *
     */
    public static final String NAME = "Luggage Tracker Tool";

    // Database Manager
    private static final DatabaseManager databaseManager = new connectivity.DatabaseManager();
    // The main window
    private JFrame mainWindow;
    
    // LuggageTrackerTool2 singleton
    private static final LuggageTrackerTool2 instance = new LuggageTrackerTool2();
    // Login frame
    private JFrame loginWindow;

    private model.User user;
    private MainMenu mainMenu;
    private SideBar sideBar;
    private SeachBar searchBar;

    private model.Passenger selectedPassenger;
    private model.Luggage selectedLuggage;

    /**
     *
     */
    public LuggageTrackerTool2() {
    }

    /**
     *
     */
    public void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
    }

    /**
     * 
     */
    public void login() {
        shutdown();
        loginWindow = new JFrame("Login");
        loginWindow.setSize(478, 302);

        loginWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                exit();
            }
        });

        loginWindow.getContentPane().add(new view.LTTLogin());
        loginWindow.setVisible(true);
    }

    /**
     * Authenticates the user's login credentials.
     * @param username
     * @param password
     * @return 
     */
    public boolean authenticate(String username, char[] password) {
        model.User tempUser = null;
        try {
            tempUser = model.UserDAO.readByUsername(username);
            System.out.println(tempUser);
            if (tempUser != null) {
                char[] userpass = tempUser.getPassword().toCharArray();
                if (userpass.length == password.length) {
                    for (int i = 0; i < userpass.length; i++) {
                        if (userpass[i] != password[i]) {
                            return false;
                        }
                    }
                    System.out.println("Authenticated user " + tempUser.getUsername());
                    user = tempUser;
                    return true;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        return false;
    }

    /**
     * Adds GUI JPanels, sidebar, searchbar, mainmenu/ tabs
     */
    public void startup() {
        if (loginWindow != null) {
            loginWindow.setVisible(false);
            loginWindow.dispose();
        }
        mainWindow = new view.MainFrame();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /**
         * Make the window closing [x] button on the frame active
         */
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                exit();
            }
        });
        removePastDateLuggage();
        mainMenu = new MainMenu();

        mainWindow.getContentPane().setLayout(new BorderLayout(10, 10));
        this.searchBar = new SeachBar();
        this.sideBar = new SideBar();
        addPanel(this.searchBar, BorderLayout.NORTH);
        addPanel(this.sideBar, BorderLayout.LINE_START);
        addPanel(this.mainMenu, BorderLayout.CENTER);
        mainWindow.getContentPane().setBackground(new Color(156, 9, 9));
        mainWindow.setVisible(true);
    }

    /**
     *
     * @param jftf The JFormattedTextField to set the mask on
     * @param format The DateFormat format example; yyyy-MM-dd
     * @param mask String which represents format in the textfield IE ####-##-##
     * The example above shows the dashes '-' in the textfield
     */
    public void createJFTFMask(JFormattedTextField jftf, String format, String mask) {
        DateFormat df = new SimpleDateFormat(format);
        DateFormatter dfer = new DateFormatter(df);
        DefaultFormatterFactory factory = new DefaultFormatterFactory(dfer);
        jftf.setFormatterFactory(factory);

        try {
            MaskFormatter mft = new MaskFormatter(mask);
            mft.install(jftf);
        } catch (java.text.ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param panel
     */
    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    /**
     * 
     * @param panel
     * @param layout 
     */
    public void addPanel(JPanel panel, Object layout) {
        mainWindow.getContentPane().add(panel, layout);
    }

    /**
     * 
     */
    public void exit() {
        if (mainWindow != null) {
            mainWindow.setVisible(false);
        }
        if (loginWindow != null) {
            loginWindow.setVisible(false);
        }
        user = null;
        shutdown();
    }

    /**
     * Disposes of the GUI.
     */
    public void shutdown() {
        if (mainWindow != null) {
            mainWindow.dispose();
        }
        if (loginWindow != null) {
            loginWindow.dispose();
        }
//        databaseManager.closeConnection();
    }

    /**
     * 
     * @return 
     */
    public static LuggageTrackerTool2 getInstance() {
        return instance;
    }

    /**
     * 
     * @return 
     */
    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    /**
     * 
     * @return 
     */
    public model.User getCurrentUser() {
        return user;
    }

    /**
     * 
     * @return 
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * 
     * @return 
     */
    public SideBar getSideBar() {
        return this.sideBar;
    }

    /**
     * 
     * @return 
     */
    public SeachBar getSearchBar() {
        return this.searchBar;
    }

    /**
     * 
     * @return 
     */
    public Passenger getSelectedPassenger() {
        return selectedPassenger;
    }

    /**
     * 
     * @param selectedPassenger 
     */
    public void setSelectedPassenger(Passenger selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }

    /**
     * 
     * @return 
     */
    public Luggage getSelectedLuggage() {
        return selectedLuggage;
    }

    /**
     * 
     * @param selectedLuggage 
     */
    public void setSelectedLuggage(Luggage selectedLuggage) {
        this.selectedLuggage = selectedLuggage;
    }

    /**
     * 
     * @param selection 
     */
    public void updatePassengerInformationPanel(int selection) {
        try {
            selectedPassenger = model.PassengerDAO.readById(selection);
            getMainMenu().getInformationPanel().setPassengerLabel(selectedPassenger);
        } catch (SQLException ex) {
//            getMainMenu().getInformationPanel().clearPassengerLabels();
        }
    }

    /**
     * 
     * @param selection 
     */
    public void updateLuggageInformationPanel(int selection) {
        try {
            selectedLuggage = model.LuggageDAO.readById(selection);
            getMainMenu().getInformationPanel().setLuggageLabel(selectedLuggage);
        } catch (SQLException ex) {
//            getMainMenu().getInformationPanel().clearLuggageLabels();
        }
    }

    /**
     * Connects luggage to passenger, performs SQL update query.
     * @throws CustomException 
     */
    public void connectLuggageToPassenger() throws CustomException {
        if (selectedLuggage != null & selectedPassenger != null) {
            selectedLuggage.setPassenger(selectedPassenger.getPassengerid());
            try {
                model.LuggageDAO.update(selectedLuggage);
            } catch (SQLException ex) {
                System.err.println("Cannot connect luggage to person");
                System.err.println(ex.getMessage());
            }
        } else {
            throw new CustomException("You need to have a passenger and a luggage piece selected.");
        }
    }

    /**
     * Removes luggage which has been missing over 2 months
     */
    public void removePastDateLuggage() {
        Date date = new Date();
        try {
            List<Luggage> allLuggage = LuggageDAO.readAll();
            for (Luggage l : allLuggage) {
                if (addMonthsToDate(l.getDateAdded(), 2).before(date) && l.getLuggagestatus() == Status.Missing) {
                    LuggageDAO.delete(l.getLuggageid());
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Returns result of date + months
     * @param date the date to calculate from
     * @param months amount of months to add.
     * @return 
     */
    public Date addMonthsToDate(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final LuggageTrackerTool2 applicatie = LuggageTrackerTool2.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    applicatie.initialize();
                    applicatie.login();
//                    applicatie.startup();
                } catch (Exception e) {
                    System.err.println("Application"
                            + applicatie.getClass().getName() + " failed to launch.");
                    System.err.println("Message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

}
