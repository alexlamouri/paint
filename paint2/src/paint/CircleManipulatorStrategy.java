package ca.utoronto.utm.paint;
import javafx.scene.input.MouseEvent;

class CircleManipulatorStrategy extends ShapeManipulatorStrategy {
	private CircleCommand circleCommand;
	
	public CircleManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x1=circleCommand.getCenter().x, y1=circleCommand.getCenter().y;
		int x2=(int)e.getX(), y2=(int)e.getY();
		
		int radius = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		this.circleCommand.setRadius(radius);
	}

	@Override
	public void mousePressed(MouseEvent e) {
			Point center = new Point((int)e.getX(), (int)e.getY());
			this.circleCommand=new CircleCommand(center, 0);
			this.addCommand(circleCommand);
	}
}
