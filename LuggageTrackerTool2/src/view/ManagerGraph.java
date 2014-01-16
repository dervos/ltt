package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import model.LuggageDAO;
import view.ManagerMenu;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ManagerGraph extends JPanel {

    private CategoryDataset dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public ManagerGraph() {
        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        add(chartPanel);
    }

    public void refresh(String firstDate, String lastDate) {
        remove(chartPanel);
        dataset = createDataset(firstDate, lastDate);        
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        add(chartPanel);
    }

    public CategoryDataset createDataset() {
        // row keys...
        final String missingDataset = "Missing";
        final String foundDataset = "Found";
        final String returnedDataset = "Returned";
        final String destroyedDataset = "Destroyed";

        // column keys...
        final String type1 = "";
        final String type2 = "";

        String firstDate = "2014-01-01 00:00:00";
        String lastDate = "2100-01-01 00:00:00";
        int missing=0, found=0, returned =0, destroyed =0;
        try {
            missing = LuggageDAO.readDateMissing(firstDate, lastDate);
            found = LuggageDAO.readDateFound(firstDate, lastDate);
            returned = LuggageDAO.readDateReturned(firstDate, lastDate);
            destroyed = LuggageDAO.readDateDestroyed(firstDate, lastDate);
//            System.out.println("" + m
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

//         create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(missing, missingDataset, type1);

        dataset.addValue(found, foundDataset, type1);

        dataset.addValue(returned, returnedDataset, type1);

        dataset.addValue(destroyed, destroyedDataset, type1);

        return dataset;

    }

    public CategoryDataset createDataset(String firstDate, String lastDate) {
        // row keys...
        final String missingDataset = "Missing";
        final String foundDataset = "Found";
        final String returnedDataset = "Returned";
        final String destroyedDataset = "Destroyed";

        // column keys...
        final String type1 = firstDate;
        final String type2 = lastDate;

        int missing=0, found=0, returned =0, destroyed =0;
        try {
            missing = LuggageDAO.readDateMissing(firstDate, lastDate);
            found = LuggageDAO.readDateFound(firstDate, lastDate);
            returned = LuggageDAO.readDateReturned(firstDate, lastDate);
            destroyed = LuggageDAO.readDateDestroyed(firstDate, lastDate);
//            System.out.println("" + m
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(missing, missingDataset, type1);

        dataset.addValue(found, foundDataset, type1);

        dataset.addValue(returned, returnedDataset, type1);

        dataset.addValue(destroyed, destroyedDataset, type1);

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset a dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "Manager Graph", // chart title
                "Time", // domain axis label
                "Value", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
                0, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{10.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                1, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{6.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                2, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{2.0f, 6.0f}, 0.0f
                )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

}
