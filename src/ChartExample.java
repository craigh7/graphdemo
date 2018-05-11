import java.awt.BorderLayout;
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
import javax.swing.filechooser.FileSystemView;

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
	private JFrame frame;
	private String[] coord = {};
	
	public ChartExample(JFrame frame) {
		this.frame = frame;
		
	}
		

		public ChartExample() {
		// TODO Auto-generated constructor stub
	}


		private void getValues(File file) throws IOException{
			

			
			//String fileName = "C:\\Users\\Craig\\eclipse-workspace\\Graph\\sample.las";
	                
			String content = new String(new String(Files.readAllBytes(file.toPath())));
			
			content = content.substring(content.lastIndexOf("~A") + 2);
			
			String[] lines = content.split("\\r?\\n");
			
			LinkedList<Vector<String>> coordsList=new LinkedList<Vector<String>>();
			this.coord=lines;
			
			/*
			for (String line: lines) {
				
				
				String[] oneline=line.split(",");
				for (String coord : oneline) {
					if (oneline.length>1) {
						System.out.println(oneline[0]+ " " + oneline[1]);
						drawCoord(oneline[0], oneline[1]);
						
					}
				}
			}
			*/
			
			//return coordsList;
		}
			/*
		public static void drawCoord(String xcoord, String ycoord) {
			int x = 20 + (int)(Math.round(Double.parseDouble(xcoord)))  ;
			int y = 520 - (int)(Math.round(Double.parseDouble(ycoord )) ) * 10;
			g.drawLine(x, y, x, y);
		}
		*/
		 public void paintComponent(Graphics g) {
			 
			 
		     //vertical line
		     g.setColor(Color.black);
		     g.drawLine(20, 20, 20, 520);
		 
		     //horizontal line
		     g.setColor(Color.black);
		     g.drawLine(20, 520, 850, 520);
		     
		     
		     String[] coord = this.coord;
		     
		     if (coord.length>0) 
		     {
		    	 for (String line: coord) {
						
						
						String[] oneline=line.split(",");
						for (String xy : oneline) {
							if (oneline.length>1) {
								int x = 20 + (int)(Math.round(Double.parseDouble(oneline[0])))  ;
								int y = 520 - (int)(Math.round(Double.parseDouble(oneline[1] )) ) * 10;
								g.drawLine(x,y,x,y);
								
							}
						}
					}
		     }
		  }
		 
	  public static void main(String[] args) {
		  
		  ChartExample chartExample = new ChartExample();
		  chartExample.go();
	  }
		  
	    public void go()
	    {
	    	JFrame.setDefaultLookAndFeelDecorated(true);
		    
		    
		    JFrame frame = new JFrame("Draw Line");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setBackground(Color.white);
		    frame.setSize(900, 600);
		    
		    JButton button = new JButton("Load File");
		    button.addActionListener(this);
		    ChartExample panel = new ChartExample(frame);
		    panel.setVisible(true);
		    frame.add(BorderLayout.NORTH, panel);
		    frame.add(BorderLayout.NORTH, button);
		    //frame.add(BorderLayout.SOUTH, button);
		 
		    frame.setVisible(true);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			// int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				
				try {
					getValues(selectedFile);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
		}
}