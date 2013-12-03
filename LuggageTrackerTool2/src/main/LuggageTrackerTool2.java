package main;

import connectivity.DatabaseManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.MainMenu;

/**
 *
 * @author Reinhard van Apeldoorn
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
        
        mainMenu = new view.MainMenu();

        mainWindow.getContentPane().setLayout(new BorderLayout(10, 10));
        addPanel(new view.SeachBar(), BorderLayout.NORTH);
        addPanel(new view.SideBar(), BorderLayout.LINE_START);
        addPanel(mainMenu, BorderLayout.CENTER);

        mainWindow.setVisible(true);
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
