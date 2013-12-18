package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
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

public class BarChartStatus extends JPanel{

    public BarChartStatus() {
//        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1200, 670));
        add(chartPanel);
    }

    private static CategoryDataset createDataset() {
        // row keys...
        String series1 = "Found";
        String series2 = "Missing";
        String series3 = "Returned";
        String series4 = "Destroyed";

        int numFound = LuggageDAO.readFound();
        int numMissing = LuggageDAO.readMissing();
        int numReturned = LuggageDAO.readReturned();
        int numDestroyed = LuggageDAO.readDestroyed();
                
        // column keys...
        String category1 = "Found";
        String category2 = "Missing";
        String category3 = "Returned";
        String category4 = "Destroyed";
//        String category5 = "Week 5";
       
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        
         
       dataset.addValue(numFound, series1, category1);
//        dataset.addValue(4.0, series1, category2);
//        dataset.addValue(3.0, series1, category3);
//        dataset.addValue(5.0, series1, category4);
//        dataset.addValue(5.0, series1, category5);

        dataset.addValue(numMissing, series2, category2);
//        dataset.addValue(7.0, series2, category2);
//        dataset.addValue(6.0, series2, category3);
//        dataset.addValue(8.0, series2, category4);
//        dataset.addValue(4.0, series2, category5);
        
        dataset.addValue(numReturned, series3, category3);
//        dataset.addValue(3.0, series3, category2);
//        dataset.addValue(2.0, series3, category3);
//        dataset.addValue(3.0, series3, category4);
//        dataset.addValue(6.0, series3, category5);

          dataset.addValue(numDestroyed, series4, category4);
//        dataset.addValue(3.0, series3, category2);
//        dataset.addValue(2.0, series3, category3);
//        dataset.addValue(3.0, series3, category4);
//        dataset.addValue(6.0, series3, category5);

        return dataset;

    }

        //      * Creates a sample chart.*/
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Luggage Status Overview", // chart title
                "Date", // domain axis label
                "Amount", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.orange,
                0.0f, 0.0f, new Color(0,0, 0));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 00, 64));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(00, 64, 0));
        GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                Math.PI / 6.0));
        return chart;

    }

    public static void main(String[] args) {
        BarChartStatus demo = new BarChartStatus();
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
   