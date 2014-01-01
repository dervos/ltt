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

public class BarChartMonthStatus2015 extends JPanel{

    public BarChartMonthStatus2015() {
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

        int numFoundJanuary2015 = LuggageDAO.readFoundJanuary2015();
        int numMissingJanuary2015 = LuggageDAO.readMissingJanuary2015();
        int numReturnedJanuary2015 = LuggageDAO.readReturnedJanuary2015();
        int numDestroyedJanuary2015 = LuggageDAO.readDestroyedJanuary2015();
        
        int numFoundFebruary2015 = LuggageDAO.readFoundFebruary2015();
        int numMissingFebruary2015 = LuggageDAO.readMissingFebruary2015();
        int numReturnedFebruary2015 = LuggageDAO.readReturnedFebruary2015();
        int numDestroyedFebruary2015 = LuggageDAO.readDestroyedFebruary2015();
        
        int numFoundMarch2015 = LuggageDAO.readFoundMarch2015();
        int numMissingMarch2015 = LuggageDAO.readMissingMarch2015();
        int numReturnedMarch2015 = LuggageDAO.readReturnedMarch2015();
        int numDestroyedMarch2015 = LuggageDAO.readDestroyedMarch2015();
        
        int numFoundApril2015 = LuggageDAO.readFoundApril2015();
        int numMissingApril2015 = LuggageDAO.readMissingApril2015();
        int numReturnedApril2015 = LuggageDAO.readReturnedApril2015();
        int numDestroyedApril2015 = LuggageDAO.readDestroyedApril2015();
        
        int numFoundMay2015 = LuggageDAO.readFoundMay2015();
        int numMissingMay2015 = LuggageDAO.readMissingMay2015();
        int numReturnedMay2015 = LuggageDAO.readReturnedMay2015();
        int numDestroyedMay2015 = LuggageDAO.readDestroyedMay2015();
        
        int numFoundJune2015 = LuggageDAO.readFoundJune2015();
        int numMissingJune2015 = LuggageDAO.readMissingJune2015();
        int numReturnedJune2015 = LuggageDAO.readReturnedJune2015();
        int numDestroyedJune2015 = LuggageDAO.readDestroyedJune2015();
        
        int numFoundJuly2015 = LuggageDAO.readFoundJuly2015();
        int numMissingJuly2015 = LuggageDAO.readMissingJuly2015();
        int numReturnedJuly2015 = LuggageDAO.readReturnedJuly2015();
        int numDestroyedJuly2015 = LuggageDAO.readDestroyedJuly2015();
        
        int numFoundAugust2015 = LuggageDAO.readFoundAugust2015();
        int numMissingAugust2015 = LuggageDAO.readMissingAugust2015();
        int numReturnedAugust2015 = LuggageDAO.readReturnedAugust2015();
        int numDestroyedAugust2015 = LuggageDAO.readDestroyedAugust2015();
        
        int numFoundSeptember2015 = LuggageDAO.readFoundSeptember2015();
        int numMissingSeptember2015 = LuggageDAO.readMissingSeptember2015();
        int numReturnedSeptember2015 = LuggageDAO.readReturnedSeptember2015();
        int numDestroyedSeptember2015 = LuggageDAO.readDestroyedSeptember2015();
        
        int numFoundOctober2015 = LuggageDAO.readFoundOctober2015();
        int numMissingOctober2015 = LuggageDAO.readMissingOctober2015();
        int numReturnedOctober2015 = LuggageDAO.readReturnedOctober2015();
        int numDestroyedOctober2015 = LuggageDAO.readDestroyedOctober2015();
        
        int numFoundNovember2015 = LuggageDAO.readFoundNovember2015();
        int numMissingNovember2015 = LuggageDAO.readMissingNovember2015();
        int numReturnedNovember2015 = LuggageDAO.readReturnedNovember2015();
        int numDestroyedNovember2015 = LuggageDAO.readDestroyedNovember2015();
        
        int numFoundDecember2015 = LuggageDAO.readFoundDecember2015();
        int numMissingDecember2015 = LuggageDAO.readMissingDecember2015();
        int numReturnedDecember2015 = LuggageDAO.readReturnedDecember2015();
        int numDestroyedDecember2015 = LuggageDAO.readDestroyedDecember2015();
                
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

        dataset.addValue(numFoundJanuary2015, series1, category1);
        dataset.addValue(numMissingJanuary2015, series2, category1);
        dataset.addValue(numReturnedJanuary2015, series3, category1);
        dataset.addValue(numDestroyedJanuary2015, series4, category1);

        dataset.addValue(numFoundFebruary2015, series1, category2);
        dataset.addValue(numMissingFebruary2015, series2, category2);
        dataset.addValue(numReturnedFebruary2015, series3, category2);
        dataset.addValue(numDestroyedFebruary2015, series4, category2);
        
        dataset.addValue(numFoundMarch2015, series1, category3);
        dataset.addValue(numMissingMarch2015, series2, category3);
        dataset.addValue(numReturnedMarch2015, series3, category3);
        dataset.addValue(numDestroyedMarch2015, series4, category3);
        
        dataset.addValue(numFoundApril2015, series1, category4);
        dataset.addValue(numMissingApril2015, series2, category4);
        dataset.addValue(numReturnedApril2015, series3, category4);
        dataset.addValue(numDestroyedApril2015, series4, category4);
        
        dataset.addValue(numFoundMay2015, series1, category5);
        dataset.addValue(numMissingMay2015, series2, category5);
        dataset.addValue(numReturnedMay2015, series3, category5);
        dataset.addValue(numDestroyedMay2015, series4, category5);
        
        dataset.addValue(numFoundJune2015, series1, category6);
        dataset.addValue(numMissingJune2015, series2, category6);
        dataset.addValue(numReturnedJune2015, series3, category6);
        dataset.addValue(numDestroyedJune2015, series4, category6);
        
        dataset.addValue(numFoundJuly2015, series1, category7);
        dataset.addValue(numMissingJuly2015, series2, category7);
        dataset.addValue(numReturnedJuly2015, series3, category7);
        dataset.addValue(numDestroyedJuly2015, series4, category7);
        
        dataset.addValue(numFoundAugust2015, series1, category8);
        dataset.addValue(numMissingAugust2015, series2, category8);
        dataset.addValue(numReturnedAugust2015, series3, category8);
        dataset.addValue(numDestroyedAugust2015, series4, category8);
        
        dataset.addValue(numFoundSeptember2015, series1, category9);
        dataset.addValue(numMissingSeptember2015, series2, category9);
        dataset.addValue(numReturnedSeptember2015, series3, category9);
        dataset.addValue(numDestroyedSeptember2015, series4, category9);
        
        dataset.addValue(numFoundOctober2015, series1, category10);
        dataset.addValue(numMissingOctober2015, series2, category10);
        dataset.addValue(numReturnedOctober2015, series3, category10);
        dataset.addValue(numDestroyedOctober2015, series4, category10);
        
        dataset.addValue(numFoundNovember2015, series1, category11);
        dataset.addValue(numMissingNovember2015, series2, category11);
        dataset.addValue(numReturnedNovember2015, series3, category11);
        dataset.addValue(numDestroyedNovember2015, series4, category11);
        
        dataset.addValue(numFoundDecember2015, series1, category12);
        dataset.addValue(numMissingDecember2015, series2, category12);
        dataset.addValue(numReturnedDecember2015, series3, category12);
        dataset.addValue(numDestroyedDecember2015, series4, category12);
        
        return dataset;

    }

        //      * Creates a sample chart.*/
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Luggage Status Month Overview 2015", // chart title
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
        BarChartMonthStatus2015 demo = new BarChartMonthStatus2015();
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
   