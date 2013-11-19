package main;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import connectivity.DatabaseManager;
import connectivity.QueryManager;

import model.User;
import view.MainFrame;

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
    private DatabaseManager databaseManager;
    private QueryManager queryManager;
    // The main window
    private JFrame mainWindow;
    // Admin User
    private static final User admin = User.getAdmin();
    // LuggageTrackerTool2 singleton
    private static final LuggageTrackerTool2 instance = new LuggageTrackerTool2();

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
        // create and initialize the connectivity
        databaseManager = new DatabaseManager();
        databaseManager.openConnection();
        queryManager = new QueryManager(databaseManager);
    }

    /**
     *
     */
    public void startup() {
        mainWindow = new MainFrame(NAME);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        mainWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        /**
         * Make the window closing [x] button on the frame active
         */
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                shutdown();
            }
        });

        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new view.MainMenu());

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

    /**
     *
     */
    public void exit() {
        mainWindow.setVisible(false);
        shutdown();
    }

    private void shutdown() {
        mainWindow.dispose();
        databaseManager.closeConnection();
    }

    public static LuggageTrackerTool2 getInstance() {
        return instance;
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
                    applicatie.startup();
                } catch (Exception e) {
                    System.err.println("Application"
                            + applicatie.getClass().getName() + "failed to launch.");
                    System.err.println("Message: " + e.getMessage());
                }
            }
        });
    }

}
