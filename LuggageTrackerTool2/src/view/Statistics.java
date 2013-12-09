/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Paint;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.RefineryUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

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
 *
 * @author Lesley
 */
public class Statistics {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    String[] weeks = {"Week 1", "Week 2", "Week 3", "Week 4", "Week 5"};
    int[] luggageLost = {0, 2, 2, 5, 7, 8};
    int[] luggageFound = {0, 1, 2, 3, 1, 8};
    int[] luggageDone = {20, 17, 16, 12, 12};

    public Statistics(String windowTitle) {
        this.dataset = new DefaultCategoryDataset();
    }

    public void addSeries(int[] rowValue, String seriesName, String[] colName) {
        for (int i = 0; i < rowValue.length; i++) {
            this.dataset.addValue(rowValue[i], seriesName, colName[i]);
        }
    }

    public void createList() {
        Statistics statisticsList = new Statistics("Monthly overview of exam status");

        statisticsList.addSeries(luggageLost, "Passed", weeks);
        statisticsList.addSeries(luggageFound, "Failed", weeks);
        statisticsList.addSeries(luggageDone, "Todo", weeks);
    }

    public void BarChartDemo1(String title) {


        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Demo 1", // chart title
                "Category", // domain axis label
                "Value", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );
        return chart;
    }

    private void setContentPane(ChartPanel chartPanel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
