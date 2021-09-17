package paint;
import java.util.Stack;

/**
 * A DrawingCommandsInvoker class that contains a commands stack consisting 
 * of DrawingCommands for shapes and shape modifiers. It also contains methods that
 * can add, remove and clear the stack of commands.
 * @author Jessica (ander710)
 */
public class DrawingCommandsInvoker {

	private Stack<DrawingCommands> commands = new Stack<DrawingCommands>();
	
	/**
	 * Executes all commands in the commands stack.
	 */
	public void executeAll() {		
		for(DrawingCommands dc:commands) {
			dc.execute();
		}		
	}
	
	/**
	 * Add the given command to the commands stack.
	 * @param command
	 */
	public void add(DrawingCommands command) {
		this.commands.push(command);
	}
	
	/**
	 * Return the current stack of commands.
	 * @return commands
	 */
	public Stack<DrawingCommands> getCommands() {
		return this.commands;
	}
	
	/**
	 * Clear the current stack of commands.
	 */
	public void clear() {
		this.commands.clear();
	}
	
	/**
	 * Return the latest removed command from the commands stack.
	 * @return DrawingCommand
	 */
	public DrawingCommands remove() {
		return this.commands.pop();
	}
}