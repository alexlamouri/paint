package paint;

/**
 * A StrategyFactory class that contains a view and paint panel and a method that returns a specific strategy
 * based on button clicks from the ShapeChooserPanel.
 * @author Alexandre (gagneal1)
 */
public class StrategyFactory {

	private View view;
	private PaintPanel paintpanel;
	
	/**
	 * Construct a new StrategyFactory with the given paint panel.
	 * @param paintpanel
	 */
	StrategyFactory(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	
	/**
	 * Returns appropriate Shape strategy based on the String shapeType from a given Shape button.
	 * @param shapeType
	 * @return Strategy
	 */
	public Strategy getShapeStrategy(String shapeType) {
		if (shapeType == "Oval") {
			return new OvalStrategy(paintpanel);
		} else if (shapeType == "Circle") {
			return new CircleStrategy(paintpanel);
		} else if (shapeType == "Rectangle") {
			return new RectangleStrategy(paintpanel);
		} else if (shapeType == "Square") {
			return new SquareStrategy(paintpanel);
		} else if (shapeType == "Squiggle") {
			return new SquiggleStrategy(paintpanel);
		} else if (shapeType == "Line") {
			return new LineStrategy(paintpanel);
		} else if (shapeType == "Polyline") {
			return new PolylineStrategy(paintpanel);
		} else {
			return null;
		}	
	}
}
