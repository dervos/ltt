package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.LuggageDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 */
public class BarChart extends JPanel {
    private ChartPanel chartPanel;

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public BarChart() {
        String firstDate = "2014-01-01 00:00:00";
        String lastDate = "2017-01-01 00:00:00";
        CategoryDataset dataset = createDataset(firstDate, lastDate);
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        add(chartPanel);
    }
    
    public void refresh(String firstDate, String lastDate) {
        remove(chartPanel);
        CategoryDataset dataset = createDataset(firstDate, lastDate);
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        add(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset(String firstDate, String lastDate) {

        // row keys...
        String series1 = "Missing";
        String series2 = "Found";
        String series3 = "Returned";
        String series4 = "Destroyed";

        // column keys...
        String category1 = firstDate.substring(0, 10)+" t/m "+ lastDate.substring(0, 10);

        // getting te values
        int missing = 0, found = 0, returned = 0, destroyed = 0;
        try {
            missing = LuggageDAO.readDateMissing(firstDate, lastDate);
            found = LuggageDAO.readDateFound(firstDate, lastDate);
            returned = LuggageDAO.readDateReturned(firstDate, lastDate);
            destroyed = LuggageDAO.readDateDestroyed(firstDate, lastDate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
//        dataset.addValue(0, "", category1);
        
        dataset.addValue(missing, series1, category1);

        dataset.addValue(found, series2, category1);

        dataset.addValue(returned, series3, category1);

        dataset.addValue(destroyed, series4, category1);
        
//        dataset.addValue(0, "", category3);
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Luggage", // chart title
                "Status", // domain axis label
                "Value", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                false, // tooltips?
                false // URLs?
        );
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.yellow,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        BarChart demo = new BarChart();
        JFrame frame = new JFrame();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(frame);
        demo.setVisible(true);
    }

}
