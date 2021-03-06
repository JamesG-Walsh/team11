package ca.uwo.csd.cs2212.team11; 

import java.awt.BasicStroke;

import ca.uwo.csd.cs2212.team11.RateLimitExceededException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;



/**
 * Class to initialize a graph object
 * @author Andrew Hall, Dara Amin, James Walsh
 *
 */
public class Graph extends javax.swing.JPanel 
{
	private int [] data;
	private IDs type;
	private int zoom = 1;
	private int offset = 0;
	private int spread = 6;
	private int step = 16;
	private int twentyFive, fifty, seventyFive;
	private int legend = 575;
	private int endOfGraph = 534;

	Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

	private HistoricalFitnessData hfd;
	private int year;
	private int month; //Jan = 1, Dec = 12
	private int dayOfMonth;

	private boolean testFlag;

	/**
	 * Attach all methods in JPanel to our object
	 * @throws RateLimitExceededException 
	 */
	public Graph(boolean testFlag, IDs type, HistoricalFitnessData hfd, int year, int month, int dayOfMonth) throws RateLimitExceededException
	{
		this.testFlag = testFlag;

		this.hfd = hfd;
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;

		if (!testFlag)
		{
			OneDaysWorthOfData odwodCurr = hfd.retrieve2(dayOfMonth, month, year);
		}

		this.type = type;

		//		this.setOpaque(false);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setMinimumSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setMaximumSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		switch(type)
		{
		case HEART_RATE:
			data = plot(getHRData());
			break;
		case CALORIES:
			data = plot(normalizeData(getCaloriesData()));
			break;
		case STEPS:
			data = plot(normalizeData(getStepsData()));
			break;
		case DISTANCE:
			data = plot(normalizeData(getDistanceData()));
			break;
		default:
		}
	}
	/**
	 * Paint the line graph with canned data
	 * @param g 
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);


		if (type == IDs.HEART_RATE)	
		{	
			paintHRGraph(g);	
		}
		else { paintGraph(this.type, g);	}
		//		this.repaint();
		JPanel mouseListenerPanel = new JPanel();
		mouseListenerPanel.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		mouseListenerPanel.setOpaque(false);
		mouseListenerPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if (zoom < 16)
					{
						updateOffset(e.getX(), zoom * 2);
						zoom = zoom * 2;
						step = 16 / zoom;
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3)
				{
					if (zoom > 1)
					{
						updateOffset(e.getX(), zoom / 2);
						zoom = zoom / 2;
						step = 16 / zoom;
					}
				}
				repaint();
			}
		});
		this.add(mouseListenerPanel);
	}

	/**
	 * For testing purposes..
	 * @param args 
	 * @throws RateLimitExceededException 
	 */
	public static void main (String[] args) throws RateLimitExceededException
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graph g = new Graph(true, IDs.CALORIES, new HistoricalFitnessData(), 1, 1, 1);
		frame.add(g);
		frame.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		frame.setVisible(true);
	}

	/**
	*Used to paint the heart rate graph
	* @param g
	*/

	private void paintHRGraph(Graphics g) throws RateLimitExceededException{
		paintHRVerticleScale(g);

		int plotPoint = this.offset;
		int i = 0;
		while(i + step < data.length){
			if ( plotPoint >= 0 && plotPoint + spread <= endOfGraph){
				if ((i/60) != (i+step)/60){ // falls on hour check for scale markers
					if (zoom >= 8 || (i/60+1) %3 == 0)
					{
						int fix = ((i+step) - ((i+step)/60) *60) * spread / step;
						g.setColor(Color.GRAY);
						g.drawLine(plotPoint + fix, SharedData.GRAPH_HEIGHT, plotPoint + fix, SharedData.GRAPH_HEIGHT - 50);
						String time = String.format("%02d:00", i/60 + 1);
						g.drawString(time, plotPoint + fix - 15, SharedData.GRAPH_HEIGHT - 5);
						if ((i/60+1) %12 == 0){
							g.setColor(Color.BLACK);
							g.drawLine(plotPoint + fix, 0, plotPoint + fix, SharedData.GRAPH_HEIGHT);
						}
					}
				}
				g.setColor(Color.RED);
				g.drawLine(plotPoint, SharedData.GRAPH_HEIGHT - data[i], plotPoint + spread, SharedData.GRAPH_HEIGHT- data[i + step]);
			}
			plotPoint += spread;
			i += step;
		}

		g.setColor(Color.BLACK);
		g.drawLine(endOfGraph, 0, endOfGraph, SharedData.GRAPH_HEIGHT);

	}

	/**
	*Used to paint the heart rate veritcal scale
	* @param g
	*/
	private void paintHRVerticleScale(Graphics g) throws RateLimitExceededException
	{
		Graphics2D g2d = (Graphics2D) g.create();

		//draw 68 bpm (base healthy resting HR)
		g2d.setColor(Color.CYAN);
		g2d.setStroke(dashed);
		if (this.testFlag || this.getMaxRestingHR() == -1) //for test mode or unpopulated resting heart rate
		{
			g2d.drawLine(0, SharedData.GRAPH_HEIGHT-68, legend, SharedData.GRAPH_HEIGHT-68);
			g.drawString("68 bpm", legend, SharedData.GRAPH_HEIGHT-63);
		}
		else //live resting heart rate
		{
			g2d.drawLine(0, SharedData.GRAPH_HEIGHT-this.getMaxRestingHR(), legend, SharedData.GRAPH_HEIGHT-this.getMaxRestingHR());
			g.drawString(this.getMaxRestingHR() + " bpm", legend, SharedData.GRAPH_HEIGHT-(this.getMaxRestingHR()-5));
		}

		//draw max cardio line
		g2d.setColor(Color.BLUE.darker());
		g2d.setStroke(dashed);
		if (this.testFlag || this.getMaxCardioHR() == -1) //test mode or unpopulated
		{
			g2d.drawLine(0, SharedData.GRAPH_HEIGHT-getMaxCardioHR(), legend, SharedData.GRAPH_HEIGHT-getMaxCardioHR());
			g.drawString(getMaxCardioHR() + " bpm", legend, SharedData.GRAPH_HEIGHT-(getMaxCardioHR()-5));
		}
		else //live Cardio zone max
		{
			g2d.drawLine(0, SharedData.GRAPH_HEIGHT-getMaxCardioHR(), legend, SharedData.GRAPH_HEIGHT-getMaxCardioHR());
			g.drawString(getMaxCardioHR() + " bpm", legend, SharedData.GRAPH_HEIGHT-(getMaxCardioHR()-5));
		}

		
		g2d.setColor(Color.BLUE);
		g2d.setStroke(dashed);
		g2d.drawLine(0, SharedData.GRAPH_HEIGHT-getMaxFatBurnHR(), legend, SharedData.GRAPH_HEIGHT-getMaxFatBurnHR());
		g.drawString(getMaxFatBurnHR() + " bpm", legend, SharedData.GRAPH_HEIGHT-(getMaxFatBurnHR()-5));

		g.drawString("Peak", endOfGraph + 1, (SharedData.GRAPH_HEIGHT - getMaxCardioHR())/2 + 5);
		g.drawString("FatBurn", endOfGraph + 1, ((SharedData.GRAPH_HEIGHT-getMaxFatBurnHR()) + (SharedData.GRAPH_HEIGHT - getMaxRestingHR())) /2 + 5);
		g.drawString("Cardio", endOfGraph + 1, ((SharedData.GRAPH_HEIGHT-getMaxFatBurnHR()) + (SharedData.GRAPH_HEIGHT - getMaxCardioHR())) /2 + 5);
		g.drawString("Resting", endOfGraph + 1, SharedData.GRAPH_HEIGHT - (getMaxRestingHR() /2));
		//gets rid of the copy
		g2d.dispose();
	}

	/**
	*Used to paint Graphs
	* @param g and type
	*/

	private void paintGraph(IDs type, Graphics g){
		g.drawString(type.name(), 10, 20);

		Graphics2D g2d = (Graphics2D) g.create();

		//draw scale lines at 25, 50 and 75%
		g2d.setColor(Color.CYAN);
		g2d.setStroke(dashed);
		g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .75), SharedData.GRAPH_WIDTH, (int)(SharedData.GRAPH_HEIGHT * .75));
		g2d.setColor(Color.GREEN);
		g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .5), SharedData.GRAPH_WIDTH, (int)(SharedData.GRAPH_HEIGHT * .5));
		g2d.setColor(Color.BLUE);
		g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .25), SharedData.GRAPH_WIDTH, (int)(SharedData.GRAPH_HEIGHT * .25));
		g2d.dispose();

		// label scale lines
		g.drawString(twentyFive + "", legend, (int)(SharedData.GRAPH_HEIGHT*.75));
		g.drawString(fifty + "", legend, (int)(SharedData.GRAPH_HEIGHT*.5));
		g.drawString(seventyFive + "", legend, (int)(SharedData.GRAPH_HEIGHT*.25));

		int plotPoint = this.offset;
		int i = 0;
		while(i + step < data.length)
		{
			if ( plotPoint >= 0 && plotPoint + spread <= endOfGraph)
			{
				if ((i/60) != (i+step)/60)// falls on hour check for scale markers
				{ 
					if (zoom >= 8 || (i/60+1) %3 == 0)
					{
						int fix = ((i+step) - ((i+step)/60) *60) * spread / step;
						g.setColor(Color.GRAY);
						g.drawLine(plotPoint + fix, SharedData.GRAPH_HEIGHT, plotPoint + fix, SharedData.GRAPH_HEIGHT - 50);
						String time = String.format("%02d:00", i/60 + 1);
						g.drawString(time, plotPoint + fix - 15, SharedData.GRAPH_HEIGHT - 5);
						if ((i/60+1) %12 == 0){
							g.setColor(Color.BLACK);
							g.drawLine(plotPoint + fix, 0, plotPoint + fix, SharedData.GRAPH_HEIGHT);
						}
					}
				}
				g.setColor(Color.RED);

				int startAvg = 0;
				int endAvg = 0;
				boolean plottingInvalidPoint = false; //set up flag to track if the point should not be plotted due to not being initialized because of hitting the rate limit or attempting to plot a time that hasn't happened yet

				for (int count = 0; count < step; count++)
				{
					startAvg += data[i+count];
					endAvg += data[i + count + step];
					if(startAvg == -1 || endAvg == -1) //check to see if any of the minutes being plotted haven't been populated with live data
					{
						plottingInvalidPoint = true;
					}
				}
				startAvg = startAvg/step;
				endAvg = endAvg/step;

				if(!plottingInvalidPoint && this.type != IDs.HEART_RATE)
				{
					g.drawLine(plotPoint, SharedData.GRAPH_HEIGHT - startAvg, plotPoint + spread, SharedData.GRAPH_HEIGHT- endAvg);
				}

			}//end if block
			plotPoint += spread;
			i += step;
		}//end of while loop

		g.setColor(Color.BLACK);
		g.drawLine(endOfGraph, 0, endOfGraph, SharedData.GRAPH_HEIGHT);
	}

	/**
	*Used to average out data for plotting
	* @param array of double plots
	*/

	private double[] normalizeData(double[] array)
	{
		/*TODO if(test.Flag)
		{*/
		double maxVal = 0;
		for (int i = 0; i < array.length; i++)
		{
			if(array[i] > maxVal) {	maxVal = array[i];	}
		}
		maxVal = maxVal * 1.1;
		this.twentyFive = (int) Math.round(maxVal * 0.25);
		this.fifty = (int) Math.round(maxVal * 0.5);
		this.seventyFive = (int) Math.round(maxVal * 0.75);
		for (int i = 0; i < array.length; i++){
			array[i] = Math.round(array[i] / maxVal * 200);
		}
		return array;
		/*}
		else
		{
			live method
		}*/
	}

	/**
	*Used to plot on graphs
	* @param array 
	*/

	private int[] plot(double[] array)
	{
		int [] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = (int)Math.round(array[i]);
		}
		return newArray;
	}

	/**
	*Used to update offset for bar graphs
	* @param x, newZoom
	*/

	private void updateOffset(int x, int newZoom){
		if (x > endOfGraph) {	x = endOfGraph;	}
		if (newZoom > this.zoom){		this.offset = this.offset - (x * newZoom/2); 	}
		if (newZoom < this.zoom){		this.offset = this.offset + (x * newZoom);	}
		if (newZoom == 1) {	this.offset = 0;	}
	}

	/**
	*
	* @return int -- max resting heart rate
	*/
	private int getMaxRestingHR() throws RateLimitExceededException
	{
		if(this.testFlag)
		{
			return 114;
		}
		else
		{
			return hfd.retrieve2(dayOfMonth, month, year).getHeartRateDayOfData().getRestingHeartRate();
		}
	}

/**
	*
	* @return int -- max fatburn heart rate
	*/
	private int getMaxFatBurnHR() throws RateLimitExceededException{
		if(this.testFlag)
		{
			return 144;
		}
		else
		{
			return hfd.retrieve2(dayOfMonth, month, year).getHeartRateDayOfData().getFatBurnZoneMaximum();
		}
	}

/**
	*
	* @return int -- max cardio heart rate
	*/
	private int getMaxCardioHR() throws RateLimitExceededException
	{
		if(this.testFlag)
		{
			return 160;
		}
		else
		{
			return hfd.retrieve2(dayOfMonth, month, year).getHeartRateDayOfData().getCardioZoneMaximum();
		}
	}

	/**
	*
	* @return double Array -- get heart rate array from day
	*/

	private double[] getHRData() throws RateLimitExceededException
	{
		if (this.testFlag)
		{
			return SharedData.newBigD;
		}
		else
		{
			int[][] in;
			HeartRateDayOfData hrdod = hfd.retrieve2(dayOfMonth, month, year).getHeartRateDayOfData();
			hrdod.populate(); //Desktop constructor will do this
			in = hrdod.getHeartRateByTheMin();
			return this.convert2Dto1D(in);		
		}
	}
/**
	*
	* @return double Array-- calories data for day
	*/
	private double[] getCaloriesData() throws RateLimitExceededException
	{
		if (this.testFlag == true)
		{
			return SharedData.newBigD;
		}
		else
		{
			double[][] in;
			OneDaysWorthOfData odwod = hfd.retrieve2(dayOfMonth, month, year);
			in = odwod.getCaloriesByTheMin();
			return this.convert2Dto1D(in);		
		}
	}

	/**
	*
	* @return double Array -- steps data for day
	*/

	private double[] getStepsData() throws RateLimitExceededException
	{
		if (this.testFlag == true)
		{
			return SharedData.newBigD;
		}
		else
		{
			int[][] in;
			OneDaysWorthOfData odwod = hfd.retrieve2(dayOfMonth, month, year);
			in = odwod.getStepsByTheMin();
			return this.convert2Dto1D(in);
		}
	}

	/**
	*
	* @return double Array -- distance data for day
	*/

	private double[] getDistanceData() throws RateLimitExceededException
	{
		if (this.testFlag == true)
		{
			return SharedData.newBigD;
		}
		else
		{
			double[][] in;
			OneDaysWorthOfData odwod = hfd.retrieve2(dayOfMonth, month, year);
			in = odwod.getDistanceByTheMin();
			return this.convert2Dto1D(in);			
		}
	}

	/**
	*
	* @return double Array -- used to convert 2d to 1d for graphing
	*/

	private double[] convert2Dto1D(double[][] in)
	{
		double[] ret = new double[1440];

		for(int hour = 0, minOfDay=0 ; hour < 24 ; hour++)
		{
			for(int minOfHour = 0; minOfHour < 60; minOfHour++, minOfDay++)
			{
				ret[minOfDay] = in[hour][minOfHour]; 
			}
		}		
		return ret;
	}

/**
	*
	* @return Type -- Id
	*/

	public IDs getType()
	{

		return this.type;
	}

	/**
	*
	* @return doubleArray -- used to convert 2d to 1d for array
	*/

	private double[] convert2Dto1D(int[][] inArr)
	{
		double[] ret = new double[1440];

		for(int hour = 0, minOfDay=0 ; hour < 24 ; hour++)
		{
			for(int minOfHour = 0; minOfHour < 60; minOfHour++, minOfDay++)
			{
				ret[minOfDay] = inArr[hour][minOfHour]; 
			}
		}		
		return ret;
	}
}
