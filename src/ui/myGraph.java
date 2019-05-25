package ui;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import model.zhifang;

public class myGraph
{
    private List<zhifang> date;

    public myGraph(List<zhifang> date)
    {
        this.date = date;
    }

    public void create()
    {

        // 柱状图数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 添加数据
        for (zhifang dd : date)
            dataset.addValue(dd.getKuchun(), "", dd.getYuefen() + "月份");

        // 生成的柱状图
        JFreeChart chart = ChartFactory.createBarChart3D("图书入库统计图表", "月份", // X轴的标签
                "册数", // Y轴的标签
                dataset, // 图标显示的数据集合
                PlotOrientation.VERTICAL, // 图像的显示形式（水平或者垂直）
                false, // 是否显示子标题
                false, // 是否生成提示的标签
                false); // 是否生成URL链接

        /*
         * 处理图形上的乱码
         */

        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 18));

        // 获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();

        // 获取X轴的对象
        CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();

        // 获取Y轴的对象
        NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();

        // 处理X轴上的乱码
        categoryAxis3D.setTickLabelFont(new Font("黑体", Font.BOLD, 10));

        // 处理X轴外的乱码
        categoryAxis3D.setLabelFont(new Font("黑体", Font.BOLD, 10));

        // 处理Y轴上的乱码
        numberAxis3D.setTickLabelFont(new Font("黑体", Font.BOLD, 10));

        // 处理Y轴外的乱码
        numberAxis3D.setLabelFont(new Font("黑体", Font.BOLD, 10));

        // 自定义Y轴上显示的刻度，以10作为1格
        numberAxis3D.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis3D.setTickUnit(unit);

        // 获取绘图区域对象
        BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();

        // 设置柱形图的宽度
        barRenderer3D.setMaximumBarWidth(0.07);

        // 在图形上显示数字
        barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRenderer3D.setBaseItemLabelsVisible(true);
        barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 10));

        // 柱状图和纵轴紧靠
        categoryAxis3D.setLowerMargin(0.0);

        categoryAxis3D.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

        File file = new File("直方图.png");
        try
        {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
