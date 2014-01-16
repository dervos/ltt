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

public class BarChartMonthStatus2014 extends JPanel{

    public BarChartMonthStatus2014() {
//        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(800, 570));
        add(chartPanel);
    }

    private static CategoryDataset createDataset() {
        // row keys...
        String series1 = "Found";
        String series2 = "Missing";
        String series3 = "Returned";
        String series4 = "Destroyed";

        int numFoundJanuary2014 = LuggageDAO.readFoundJanuary2014();
        int numMissingJanuary2014 = LuggageDAO.readMissingJanuary2014();
        int numReturnedJanuary2014 = LuggageDAO.readReturnedJanuary2014();
        int numDestroyedJanuary2014 = LuggageDAO.readDestroyedJanuary2014();
        
        int numFoundFebruary2014 = LuggageDAO.readFoundFebruary2014();
        int numMissingFebruary2014 = LuggageDAO.readMissingFebruary2014();
        int numReturnedFebruary2014 = LuggageDAO.readReturnedFebruary2014();
        int numDestroyedFebruary2014 = LuggageDAO.readDestroyedFebruary2014();
        
        int numFoundMarch2014 = LuggageDAO.readFoundMarch2014();
        int numMissingMarch2014 = LuggageDAO.readMissingMarch2014();
        int numReturnedMarch2014 = LuggageDAO.readReturnedMarch2014();
        int numDestroyedMarch2014 = LuggageDAO.readDestroyedMarch2014();
        
        int numFoundApril2014 = LuggageDAO.readFoundApril2014();
        int numMissingApril2014 = LuggageDAO.readMissingApril2014();
        int numReturnedApril2014 = LuggageDAO.readReturnedApril2014();
        int numDestroyedApril2014 = LuggageDAO.readDestroyedApril2014();
        
        int numFoundMay2014 = LuggageDAO.readFoundMay2014();
        int numMissingMay2014 = LuggageDAO.readMissingMay2014();
        int numReturnedMay2014 = LuggageDAO.readReturnedMay2014();
        int numDestroyedMay2014 = LuggageDAO.readDestroyedMay2014();
        
        int numFoundJune2014 = LuggageDAO.readFoundJune2014();
        int numMissingJune2014 = LuggageDAO.readMissingJune2014();
        int numReturnedJune2014 = LuggageDAO.readReturnedJune2014();
        int numDestroyedJune2014 = LuggageDAO.readDestroyedJune2014();
        
        int numFoundJuly2014 = LuggageDAO.readFoundJuly2014();
        int numMissingJuly2014 = LuggageDAO.readMissingJuly2014();
        int numReturnedJuly2014 = LuggageDAO.readReturnedJuly2014();
        int numDestroyedJuly2014 = LuggageDAO.readDestroyedJuly2014();
        
        int numFoundAugust2014 = LuggageDAO.readFoundAugust2014();
        int numMissingAugust2014 = LuggageDAO.readMissingAugust2014();
        int numReturnedAugust2014 = LuggageDAO.readReturnedAugust2014();
        int numDestroyedAugust2014 = LuggageDAO.readDestroyedAugust2014();
        
        int numFoundSeptember2014 = LuggageDAO.readFoundSeptember2014();
        int numMissingSeptember2014 = LuggageDAO.readMissingSeptember2014();
        int numReturnedSeptember2014 = LuggageDAO.readReturnedSeptember2014();
        int numDestroyedSeptember2014 = LuggageDAO.readDestroyedSeptember2014();
        
        int numFoundOctober2014 = LuggageDAO.readFoundOctober2014();
        int numMissingOctober2014 = LuggageDAO.readMissingOctober2014();
        int numReturnedOctober2014 = LuggageDAO.readReturnedOctober2014();
        int numDestroyedOctober2014 = LuggageDAO.readDestroyedOctober2014();
        
        int numFoundNovember2014 = LuggageDAO.readFoundNovember2014();
        int numMissingNovember2014 = LuggageDAO.readMissingNovember2014();
        int numReturnedNovember2014 = LuggageDAO.readReturnedNovember2014();
        int numDestroyedNovember2014 = LuggageDAO.readDestroyedNovember2014();
        
        int numFoundDecember2014 = LuggageDAO.readFoundDecember2014();
        int numMissingDecember2014 = LuggageDAO.readMissingDecember2014();
        int numReturnedDecember2014 = LuggageDAO.readReturnedDecember2014();
        int numDestroyedDecember2014 = LuggageDAO.readDestroyedDecember2014();
                
        // column keys...
        String category1 = "January";
        String category2 = "February";
        String category3 = "March";
        String category4 = "April";
        String category5 = "May";
        String category6 = "June";
        String category7 = "July";
        String category8 = "August";
        String category9 = "September";
        String category10 = "October";
        String category11 = "November";
        String category12 = "December";
       
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(numFoundJanuary2014, series1, category1);
        dataset.addValue(numMissingJanuary2014, series2, category1);
        dataset.addValue(numReturnedJanuary2014, series3, category1);
        dataset.addValue(numDestroyedJanuary2014, series4, category1);

        dataset.addValue(numFoundFebruary2014, series1, category2);
        dataset.addValue(numMissingFebruary2014, series2, category2);
        dataset.addValue(numReturnedFebruary2014, series3, category2);
        dataset.addValue(numDestroyedFebruary2014, series4, category2);
        
        dataset.addValue(numFoundMarch2014, series1, category3);
        dataset.addValue(numMissingMarch2014, series2, category3);
        dataset.addValue(numReturnedMarch2014, series3, category3);
        dataset.addValue(numDestroyedMarch2014, series4, category3);
        
        dataset.addValue(numFoundApril2014, series1, category4);
        dataset.addValue(numMissingApril2014, series2, category4);
        dataset.addValue(numReturnedApril2014, series3, category4);
        dataset.addValue(numDestroyedApril2014, series4, category4);
        
        dataset.addValue(numFoundMay2014, series1, category5);
        dataset.addValue(numMissingMay2014, series2, category5);
        dataset.addValue(numReturnedMay2014, series3, category5);
        dataset.addValue(numDestroyedMay2014, series4, category5);
        
        dataset.addValue(numFoundJune2014, series1, category6);
        dataset.addValue(numMissingJune2014, series2, category6);
        dataset.addValue(numReturnedJune2014, series3, category6);
        dataset.addValue(numDestroyedJune2014, series4, category6);
        
        dataset.addValue(numFoundJuly2014, series1, category7);
        dataset.addValue(numMissingJuly2014, series2, category7);
        dataset.addValue(numReturnedJuly2014, series3, category7);
        dataset.addValue(numDestroyedJuly2014, series4, category7);
        
        dataset.addValue(numFoundAugust2014, series1, category8);
        dataset.addValue(numMissingAugust2014, series2, category8);
        dataset.addValue(numReturnedAugust2014, series3, category8);
        dataset.addValue(numDestroyedAugust2014, series4, category8);
        
        dataset.addValue(numFoundSeptember2014, series1, category9);
        dataset.addValue(numMissingSeptember2014, series2, category9);
        dataset.addValue(numReturnedSeptember2014, series3, category9);
        dataset.addValue(numDestroyedSeptember2014, series4, category9);
        
        dataset.addValue(numFoundOctober2014, series1, category10);
        dataset.addValue(numMissingOctober2014, series2, category10);
        dataset.addValue(numReturnedOctober2014, series3, category10);
        dataset.addValue(numDestroyedOctober2014, series4, category10);
        
        dataset.addValue(numFoundNovember2014, series1, category11);
        dataset.addValue(numMissingNovember2014, series2, category11);
        dataset.addValue(numReturnedNovember2014, series3, category11);
        dataset.addValue(numDestroyedNovember2014, series4, category11);
        
        dataset.addValue(numFoundDecember2014, series1, category12);
        dataset.addValue(numMissingDecember2014, series2, category12);
        dataset.addValue(numReturnedDecember2014, series3, category12);
        dataset.addValue(numDestroyedDecember2014, series4, category12);
        
        return dataset;

    }

        //      * Creates a sample chart.*/
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Luggage Status Month Overview 2014", // chart title
                "Month", // domain axis label
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
        BarChartMonthStatus2014 demo = new BarChartMonthStatus2014();
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
   