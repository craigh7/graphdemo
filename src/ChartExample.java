import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.StringUtils;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;  

public class ChartExample extends JPanel implements ActionListener{
	
		private static JFrame frame;
		JButton thebutton;
		
		public ChartExample(JFrame frame) {
			this.frame = frame;
		}


		private void getValues(Graphics g) throws IOException{
			

			
			String fileName = "C:\\Users\\Craig\\eclipse-workspace\\Graph\\sample.las";
	                
			String content = new String(new String(Files.readAllBytes( Paths.get(fileName))));
			
			content = content.substring(content.lastIndexOf("~A") + 2);
			
			String[] lines = content.split("\\r?\\n");
			
			LinkedList<Vector<String>> coordsList=new LinkedList<Vector<String>>();
			
			
			for (String line: lines) {
				
				
				String[] oneline=line.split(",");
				for (String coord : oneline) {
					if (oneline.length>1) {
						System.out.println(oneline[0]+ " " + oneline[1]);
						drawCoord(oneline[0], oneline[1], g);
					}
				}
			}
			
			//return coordsList;
		}
			
		public static void drawCoord(String xcoord, String ycoord, Graphics g) {
			int x = 20 + (int)(Math.round(Double.parseDouble(xcoord)))  ;
			int y = 520 - (int)(Math.round(Double.parseDouble(ycoord )) ) * 10;
			g.drawLine(x, y, x, y);
		}
		
		 public void paintComponent(Graphics g) {
			 
		     //vertical line
		     g.setColor(Color.black);
		     g.drawLine(20, 20, 20, 520);
		 
		     //horizontal line
		     g.setColor(Color.black);
		     g.drawLine(20, 520, 850, 520);
		     try {
				getValues(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		    
		     
		  }
		 
		  public static void main(String[] args) {
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    
		    JFrame frame = new JFrame("Draw Line");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setBackground(Color.white);
		    frame.setSize(900, 600);
				
		    ChartExample panel = new ChartExample(frame);
		 
		    frame.add(panel);
		 
		    frame.setVisible(true);
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			thebutton.setText("I've been clicked");
			
			
		}

		
}