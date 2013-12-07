package main;

import connectivity.DatabaseManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;

import view.MainMenu;
import view.SideBar;
import view.SeachBar;

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

    public boolean authenticate(String username, char[] password) {
        model.User tempUser = null;
        model.UserDAO userDAO = new model.UserDAO();
        try {
            tempUser = userDAO.readByUsername(username);
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
     *
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

        mainMenu = new MainMenu();

        mainWindow.getContentPane().setLayout(new BorderLayout(10, 10));
        this.searchBar = new SeachBar();
        this.sideBar = new SideBar();
        addPanel(this.searchBar, BorderLayout.NORTH);
        addPanel(this.sideBar, BorderLayout.LINE_START);
        addPanel(this.mainMenu, BorderLayout.CENTER);

        createJFTFMask(this.sideBar.getRegPassengerControl().getDateOfBirthControl(), "yyyy-MM-dd", "####-##-##");

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

    public void shutdown() {
        if (mainWindow != null) {
            mainWindow.dispose();
        }
        if (loginWindow != null) {
            loginWindow.dispose();
        }
//        databaseManager.closeConnection();
    }

    public static LuggageTrackerTool2 getInstance() {
        return instance;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public model.User getCurrentUser() {
        return user;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
    
    public SideBar getSideBar(){
        return this.sideBar;
    }
    
    public SeachBar getSearchBar(){
        return this.searchBar;
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
