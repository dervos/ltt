package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import model.LuggageDAO;
import model.PassengerDAO;

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
import javax.swing.JPanel;
import org.jfree.ui.RefineryUtilities;

public class BarChartStatus extends JPanel {

    public BarChartStatus() {
//        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1200, 670));
        add(chartPanel);
    }

    private CategoryDataset createDataset() {
        
        // legenda name
        final String series1 = "Passengers";
        final String series2 = "Luggage";

        //int totalPassenger = PassengerDAO.readTotalPassenger();
        
        int totalLuggage = LuggageDAO.readTotalLuggage();
        int foundLuggage = LuggageDAO.readFound();
        int missingLuggage = LuggageDAO.readMissing();
        int returnedLuggage = LuggageDAO.readReturned();
        int destroyedLuggage = LuggageDAO.readDestroyed();
        // x as.
        final String category1 = "Passengers";
        final String category2 = "Luggage";
//        final String category3 = "Category 3";
//        final String category4 = "Category 4";
//        final String category5 = "Category 5";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //dataset.addValue(totalPassenger, series1, category1);
//        dataset.addValue(4.0, series1, category2);
//        dataset.addValue(3.0, series1, category3);
//        dataset.addValue(5.0, series1, category4);
//        dataset.addValue(5.0, series1, category5);

        dataset.addValue(totalLuggage, series2, category2);
//        dataset.addValue(readFound, series2, category2);
//        dataset.addValue(readMissing, series2, category2);
//        dataset.addValue(readReturned, series2, category2);
//        dataset.addValue(readDestroyed, series2, category2);

//        dataset.addValue(4.0, series3, category1);
//        dataset.addValue(3.0, series3, category2);
//        dataset.addValue(2.0, series3, category3);
//        dataset.addValue(3.0, series3, category4);
//        dataset.addValue(6.0, series3, category5);
        
        return dataset;
        
    }
    
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Luggage status graph",         // chart title
            "Category",               // domain axis label
            "Amount",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    public static void main(final String[] args) {

        BarChartStatus demo = new BarChartStatus();
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}