package ca.utoronto.utm.paint;

/**
 * Visitor design pattern
 * 
 * Every object inheriting Visitable can be visited by a Visitor object
 * 
 * @author gagneal1
 */
public interface Visitable {

	public void accept(Visitor visitor);

}
