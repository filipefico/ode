package nucleo.comuns.visao.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import nucleo.comuns.util.Cor;

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


public class NucleoGraficoIndicador extends Iframe {

	private static final long serialVersionUID = 2117099372127882882L;

	public NucleoGraficoIndicador(List<NucleoGraficoIndicadorIntervalo> intervalos,
			BigDecimal valor) {
		super();

		BigDecimal minimo = null;
		BigDecimal maximo = null;

		this.setWidth("250px");
		this.setHeight("250px");

		MeterPlot meterplot = new MeterPlot(new DefaultValueDataset(valor
				.doubleValue()));

		for (NucleoGraficoIndicadorIntervalo intervalo : intervalos) {
			if (intervalo.getLimiteInferior().compareTo(
					intervalo.getLimiteSuperior()) <= 0) {
				meterplot.addInterval(new MeterInterval(
						recuperarLegenda(intervalo.getCor()), new Range(
								intervalo.getLimiteInferior().doubleValue(),
								intervalo.getLimiteSuperior().doubleValue()),
						Color.lightGray, new BasicStroke(2.0F), Cor
								.recuperarColor(intervalo.getCor())));
				
				if (minimo == null) {
					minimo = intervalo.getLimiteInferior();
				} else if (intervalo.getLimiteInferior().compareTo(minimo) < 0) {
					minimo = intervalo.getLimiteInferior();
				}
				
				if (maximo == null) {
					maximo = intervalo.getLimiteSuperior();
				} else if (intervalo.getLimiteSuperior().compareTo(maximo) > 0) {
					maximo = intervalo.getLimiteSuperior();
				}
			} else {
				meterplot.addInterval(new MeterInterval(
						recuperarLegenda(intervalo.getCor()), new Range(
								intervalo.getLimiteSuperior().doubleValue(),
								intervalo.getLimiteInferior().doubleValue()),
						Color.lightGray, new BasicStroke(2.0F), Cor
								.recuperarColor(intervalo.getCor())));
				
				if (minimo == null) {
					minimo = intervalo.getLimiteSuperior();
				} else if (intervalo.getLimiteSuperior().compareTo(minimo) < 0) {
					minimo = intervalo.getLimiteSuperior();
				}
				
				if (maximo == null) {
					maximo = intervalo.getLimiteInferior();
				} else if (intervalo.getLimiteInferior().compareTo(maximo) > 0) {
					maximo = intervalo.getLimiteInferior();
				}
			}
		}

		meterplot
				.setRange(new Range(minimo.doubleValue(), maximo.doubleValue()));

		// define a unidade que será exibida ao lado da nota no gráfico
		meterplot.setUnits("");
		
		meterplot.setNeedlePaint(Color.darkGray);
		meterplot.setDialBackgroundPaint(Color.white);
		meterplot.setDialOutlinePaint(Color.gray);
		meterplot.setDialShape(DialShape.CHORD);
		meterplot.setMeterAngle(260);
		meterplot.setTickLabelsVisible(true);
		meterplot.setTickLabelFont(new Font(" Dialog ", 1, 10));
		meterplot.setTickLabelPaint(Color.darkGray);
		meterplot.setTickSize(1D);
		meterplot.setTickPaint(Color.lightGray);
		meterplot.setValuePaint(Color.black);
		meterplot.setValueFont(new Font(" Dialog ", 1, 14));
		JFreeChart chart = new JFreeChart(" ", JFreeChart.DEFAULT_TITLE_FONT,
				meterplot, true);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			ChartUtilities.writeChartAsJPEG(outputStream, chart, 220, 220);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Media arquivo = new AMedia("indicador.jpeg", "jpeg", "image/jpeg",
				outputStream.toByteArray());

		this.setContent(arquivo);

	}

	/**
	 * 
	 * @param cor
	 * @return
	 */
	public String recuperarLegenda(String cor) {

		if (Cor.COR_VERMELHO.equals(cor)) {
			return "Urgente";
		}
		if (Cor.COR_AZUL.equals(cor)) {
			return "Ótimo";
		}
		if (Cor.COR_AMARELO.equals(cor)) {
			return "Atenção";
		}
		if (Cor.COR_VERDE.equals(cor)) {
			return "Bom";
		}

		return "Indefinido";

	}

}
