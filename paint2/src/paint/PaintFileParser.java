package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author gagneal1
 *
 */
public class PaintFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage = ""; // error encountered during parse
	private PaintModel paintModel; 
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart = Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd = Pattern.compile("^EndPaintSaveFile$");
	private Pattern pEmptyFile = Pattern.compile("");
	
	private Pattern pColor = Pattern.compile("^color:(\\d+),(\\d+),(\\d+)$");
	private Pattern pFilled = Pattern.compile("^filled:(true|false)$");

	// Only accept positive Center and Radius for Circle
	private Pattern pCircleStart = Pattern.compile("^Circle$");
	private Pattern pCenter = Pattern.compile("^center:\\((\\d+),(\\d+)\\)$");
	private Pattern pRadius = Pattern.compile("^radius:(\\d+)$");
	private Pattern pCircleEnd = Pattern.compile("^EndCircle$");
	// Assume Circle Center coordinates are always positive
	
	// Only accept positive Points for Rectangle
	private Pattern pRectangleStart = Pattern.compile("^Rectangle$");
	private Pattern pP1 = Pattern.compile("^p1:\\((\\d+),(\\d+)\\)$");
	private Pattern pP2 = Pattern.compile("^p2:\\((\\d+),(\\d+)\\)$");
	private Pattern pRectangleEnd = Pattern.compile("^EndRectangle$");
	// Assume Rectangle Points coordinates are always positive
	
	// Only accept positive Points for Squiggle
	private Pattern pSquiggleStart = Pattern.compile("^Squiggle$");
	private Pattern pPointsStart = Pattern.compile("^points$");
	private Pattern pPoint = Pattern.compile("^point:\\((\\d+),(\\d+)\\)$");
	private Pattern pPointsEnd = Pattern.compile("^endpoints$");
	private Pattern pSquiggleEnd = Pattern.compile("^EndSquiggle$");
	// Assume Squiggle Points coordinates are always positive
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg) {
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		this.paintModel = paintModel;
		this.errorMessage = "";
		
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
	
		try {	
			int state = 0; Matcher m; String l;
		
			this.lineNumber = 0;
			
			while ((l = inputStream.readLine()) != null) {
				
				// Skip empty lines
				if (l.isEmpty()) {break;}
				
				// Remove whitespace
				l = l.replaceAll("\\s", "");
		
				this.lineNumber++;
				System.out.println(lineNumber+" "+ l +" "+state);

				switch(state) {
					case 0:
						m = pFileStart.matcher(l);
						
						if (m.matches()) {
							state = 1;
							break;
						}
						
						error("\nExpected start of Paint Save File");
						return false;
						
					case 1: // Looking for the start of a new object or end of the save file
						
						m = pCircleStart.matcher(l);
						
						if (m.matches()) {
							circleCommand = new CircleCommand(new Point(0,0), 0);
							state = 2; 
							break;
						}
						
						m = pRectangleStart.matcher(l);
						
						if (m.matches()) {
							rectangleCommand = new RectangleCommand(new Point(0,0), new Point(0,0));
							state = 7; 
							break;
						}
						
						m = pSquiggleStart.matcher(l);
						
						if (m.matches()) {
							squiggleCommand = new SquiggleCommand();
							state = 12; 
							break;
						}
						
						m = pFileEnd.matcher(l);
						
						if (m.matches()) {
							state = 17; 
							break;
						}
						
						error("\nExpected start of new Object or end of Paint Save File");
						return false;
				
				// Start Circle -----------------------------------------------------------
						
					case 2: 
						
						m = pColor.matcher(l);
						
						if (m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							// Check if parsed colors are in RGB format
							if ((0 <= r && r <= 255) && (0 <= g && g <= 255) && (0 <= b && b <= 255)) {
								Color color = Color.rgb(r, g, b);
								circleCommand.setColor(color);
								state = 3; 
								break;
							}
							
							error("\nExpected 0 <= r,g,b <= 255");
							return false;
							
						}
						
						error("\nExpected color in color:r,g,b format");
						return false;

					case 3:
						
						m = pFilled.matcher(l);
						
						if (m.matches()) {
							//
							boolean filled = Boolean.parseBoolean(m.group(1));
							circleCommand.setFill(filled);
							state = 4; 
							break;
						}
						
						error("\nExpected filled in boolean format");
						return false;
						
					case 4:
						
						m = pCenter.matcher(l);
						
						if (m.matches()) {
							int x = Integer.parseInt(m.group(1));
							int y = Integer.parseInt(m.group(2));
							Point center = new Point(x,y);
							circleCommand.setCenter(center);
							state = 5; 
							break;
						}
						
						error("\nExpected center in center:(x,y) format");
						return false;
						
					case 5:
						
						m = pRadius.matcher(l);
						
						if (m.matches()) {
							int radius = Integer.parseInt(m.group(1));
							circleCommand.setRadius(radius);
							state = 6; 
							break;
						}
						
						error("\nExpected radius in radius:r format");
						System.out.println("Wrong radius");
						return false;
				
					case 6:
						
						m = pCircleEnd.matcher(l);
						
						if (m.matches()) {
							paintModel.addCommand(circleCommand);
							circleCommand = null;
							state = 1; 
							break;
						}
						
						error("\nExpected end of Circle object");
						return false;	
					
				// End Circle -----------------------------------------------------------
						
				// Start Rectangle ------------------------------------------------------
						
					case 7: 
						
						m = pColor.matcher(l);
						
						if (m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							Color color = Color.rgb(r, g, b);
							rectangleCommand.setColor(color);
							state = 8; 
							break;
						}
						
						error("\nExpected color in color:r,g,b format");
						return false;

					case 8:
						
						m = pFilled.matcher(l);
						
						if (m.matches()) {
							boolean filled = Boolean.parseBoolean(m.group(1));
							rectangleCommand.setFill(filled);
							state = 9; 
							break;
						}
						
						error("\nExpected filled in boolean format");
						return false;
						
					case 9:
						
						m = pP1.matcher(l);
						
						if (m.matches()) {
							int x = Integer.parseInt(m.group(1));
							int y = Integer.parseInt(m.group(2));
							Point p1 = new Point(x,y);
							rectangleCommand.setP1(p1);
							state = 10; 
							break;
						}
						
						error("\nExpected p1 in p1:(x,y) format");
						return false;
						
					case 10:
						
						m = pP2.matcher(l);
						
						if (m.matches()) {
							int x = Integer.parseInt(m.group(1));
							int y = Integer.parseInt(m.group(2));
							Point p2 = new Point(x,y);
							rectangleCommand.setP2(p2);
							state = 11; 
							break;
						}
						
						error("\nExpected p2 in p2:(x,y) format");
						return false;
				
					case 11:
						
						m = pRectangleEnd.matcher(l);
						
						if (m.matches()) {
							paintModel.addCommand(rectangleCommand);
							rectangleCommand = null;
							state = 1; 
							break;
						}
						
						error("\nExpected end of Rectangle object");
						return false;	
					
				// End Rectangle ------------------------------------------------------
						
				// Start Squiggle ------------------------------------------------------
						
					case 12: 
						
						m = pColor.matcher(l);
						
						if (m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							Color color = Color.rgb(r, g, b);
							squiggleCommand.setColor(color);
							state = 13; 
							break;
						}
						
						error("\nExpected color in color:r,g,b format");
						return false;

					case 13:
						
						m = pFilled.matcher(l);
						
						if (m.matches()) {
							boolean filled = Boolean.parseBoolean(m.group(1));
							squiggleCommand.setFill(filled);
							state = 14; 
							break;
						}
						
						error("\nExpected filled in boolean format");
						return false;
						
					case 14:
						
						m = pPointsStart.matcher(l);
						
						if (m.matches()) {
							state = 15; 
							break;
						}
						
						error("\nExpected start of Point list");
						return false;
						
					case 15:
						
						m = pPoint.matcher(l);
						
						if (m.matches()) {
							int x = Integer.parseInt(m.group(1));
							int y = Integer.parseInt(m.group(2));
							Point p = new Point(x,y);
							squiggleCommand.add(p);
							break;
						}
						
						m = pPointsEnd.matcher(l);
						
						if (m.matches()) {
							state = 16; 
							break;
						}
						
						error("\nExpected Point in point:(x,y) format or end of Point list");
						return false;
				
					case 16:
						
						m = pSquiggleEnd.matcher(l);
						
						if (m.matches()) {
							paintModel.addCommand(squiggleCommand);
							squiggleCommand = null;
							state = 1; 
							break;
						}
						
						error("\nExpected end of Squiggle object");
						return false;	
					
				// End Squiggle ------------------------------------------------------
				
				// Accepting state
					case 17:
						m = pEmptyFile.matcher(l);
						
						if (m.matches()) {
							break;
						}
						
						error("\nExpected nothing after End of File");
						return false;	
							
				} 
				
			} 
			
		}  
		
		catch (Exception e) {}
		
		return true;
		
	} 
	
}
