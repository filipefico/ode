package nucleo.comuns.visao.old;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import ode.nucleo.cih.NucleoWindow;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Iframe;

public class WindowTeste extends NucleoWindow {

	private static final long serialVersionUID = -5371489403146522708L;

	@Override
	protected String getTituloWindow() {
		return "Teste";
	}

	@Override
	public void onCreateWindow() {
		// /** The dataset. */
		// DefaultValueDataset data = new DefaultValueDataset(20.0);
		//
		// /** The meter plot (dial). */
		// MeterPlot meterplot = new MeterPlot(data);
		//
		// /** The meter chart (dial). */
		// JFreeChart meterchart = new JFreeChart("Meter Chart",
		// JFreeChart.DEFAULT_TITLE_FONT,
		// meterplot, false);
		//		   
		// //meterplot.setDialShape(DialShape.PIE);

//		MeterPlot plot = new MeterPlot(new DefaultValueDataset(80.0));
//		plot.setDialShape(DialShape.PIE);
//		plot.addInterval(new MeterInterval("asgasgasdgasdgasdgHigh", new Range(0.0, 50.0)));
//		plot.addInterval(new MeterInterval("High", new Range(50.01, 80.0)));
//		plot.addInterval(new MeterInterval("High", new Range(80.01, 100.0)));
//		plot.setDialOutlinePaint(Color.white);
//		
//		JFreeChart chart = new JFreeChart("Meter Chart 2",
//				JFreeChart.DEFAULT_TITLE_FONT, plot, false);
		
		MeterPlot meterplot  =   new  MeterPlot(new DefaultValueDataset(80.0));
        meterplot.setRange( new  Range( 0.0D , 200.0D));
        meterplot.addInterval( new  MeterInterval( " Normal " ,  new  Range( 0.0D , 35D), Color.lightGray,  new  BasicStroke( 2.0F ),  new  Color( 0 ,  255 ,  0 ,  64 )));
        meterplot.addInterval( new  MeterInterval( " Warning " ,  new  Range(35D, 50D), Color.lightGray,  new  BasicStroke( 2.0F ),  new  Color( 255 ,  255 ,  0 ,  64 )));
        meterplot.addInterval( new  MeterInterval( " Critical " ,  new  Range(50D, 100D), Color.lightGray,  new  BasicStroke( 2.0F ),  new  Color( 255 ,  0 ,  0 ,  128 )));
        meterplot.addInterval( new  MeterInterval( " Super Critical " ,  new  Range(100D, 200D), Color.lightGray,  new  BasicStroke( 2.0F ),  new  Color( 0 ,  0 ,  255 ,  128 )));
        
       
        meterplot.setNeedlePaint(Color.darkGray);
        meterplot.setDialBackgroundPaint(Color.white);
        meterplot.setDialOutlinePaint(Color.gray);
        meterplot.setDialShape(DialShape.CHORD);
        meterplot.setMeterAngle( 260 );
        meterplot.setTickLabelsVisible( true );
        meterplot.setTickLabelFont( new  Font( " Dialog " ,  1 ,  10 ));
        meterplot.setTickLabelPaint(Color.darkGray);
        meterplot.setTickSize(5D);
        meterplot.setTickPaint(Color.lightGray);
        meterplot.setValuePaint(Color.black);
        meterplot.setValueFont( new  Font( " Dialog " ,  1 ,  14 ));
        JFreeChart chart  =   new  JFreeChart( " Meter Chart 1 " , JFreeChart.DEFAULT_TITLE_FONT, meterplot,  true );

        
		


		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			
			ChartUtilities.writeChartAsJPEG(outputStream, chart, 300, 300);

			Media arquivo = new AMedia("alexandre.jpeg", "jpeg", "image/jpeg",
					outputStream.toByteArray());
			
			Iframe iframe = new Iframe();

			iframe.setWidth("300px");
			iframe.setHeight("300px");
			iframe.setContent(arquivo);
			iframe.setParent(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

}