package ca.utoronto.utm.paint;

import java.io.PrintWriter;

/**
 * Implementation of the Visitor design pattern
 * 
 * SaveVisitor is given a PrintWriter
 * 
 * SaveVisitor visits a PaintCommand and saves the command to the PrintWriter
 * 
 * @author gagneal1
 */
public class SaveVisitor implements Visitor {
	
	private PrintWriter SaveWriter;

	SaveVisitor(PrintWriter writer) {
		this.SaveWriter = writer;
	}

	/**
	 * Visit a CircleCommand and save the CircleCommand to the PrintWriter
	 * 
	 * @param CircleCommand 
	 */
	 public void visit(CircleCommand command) {
			
			String saveString = "";
			
			saveString += "Circle\n";
			
			int r = (int) Math.round(command.getColor().getRed() * 255);
			int g = (int) Math.round(command.getColor().getGreen() * 255);
			int b = (int) Math.round(command.getColor().getBlue() * 255);
			
			saveString+= "\tcolor:"+r+","+g+","+b+"\n";
			
			saveString +="\tfilled:"+ command.isFill() +"\n";
			
			saveString += "\tcenter:(" + command.getCenter().x + "," + command.getCenter().y + ")\n";
			
			saveString += "\tradius:" + command.getRadius() + "\n";
			
			saveString += "End Circle\n";
			
			SaveWriter.write(saveString);
		
	  } 
	  
	 /**
		 * Visit a RectangleCommand and save the RectangleCommand to the PrintWriter
		 * 
		 * @param RectangleCommand 
		 */
	  public void visit(RectangleCommand command) {
	  
			String saveString = "";
			  
			saveString += "Rectangle\n";
				
			int r = (int) Math.round(command.getColor().getRed() * 255);
			int g = (int) Math.round(command.getColor().getGreen() * 255);
			int b = (int) Math.round(command.getColor().getBlue() * 255);
			
			saveString+= "\tcolor:"+r+","+g+","+b+"\n";
				
			saveString +="\tfilled:"+ command.isFill() +"\n";
				
			saveString += "\tp1:(" + command.getP1().x + "," + command.getP1().y + ")\n";
			  
			saveString += "\tp2:(" + command.getP2().x + "," + command.getP2().y + ")\n";
			  
			saveString += "End Rectangle\n";
				
			SaveWriter.write(saveString);;
	  
	  }	

	  /**
		 * Visit a SquiggleCommand and save the SquiggleCommand to the PrintWriter
		 * 
		 * @param SquiggleCommand 
		 */
	  public void visit(SquiggleCommand command) {
		  
			String saveString = "";
					  
			saveString += "Squiggle\n";
				
			int r = (int) Math.round(command.getColor().getRed() * 255);
			int g = (int) Math.round(command.getColor().getGreen() * 255);
			int b = (int) Math.round(command.getColor().getBlue() * 255);
			
			saveString+= "\tcolor:"+r+","+g+","+b+"\n";
				
			saveString +="\tfilled:"+ command.isFill() +"\n";
				
			saveString += "\tpoints\n";
			  
			for(int i = 0; i< command.getPoints().size() - 1; i++) {
				Point p = command.getPoints().get(i);
				saveString += "\t\tpoint:(" + p.x + "," + p.y + ")\n";
			}
			  
			saveString += "\tend points\n";
			  
			saveString += "End Squiggle\n";
				
			SaveWriter.write(saveString);
		  
	  }


}
