package commands;

import java.util.HashSet;

public class Commands {
	private static HashSet<Command> commands = new HashSet<Command>();

	static {
		commands.add(new UnknownCommand("unknown"));

		// TODO: добавить комманды
		commands.add(new TestCommand("привет"));
//		commands.add(new Weather("weather"));
	}

	public static HashSet<Command> getCommands() {
		return commands;
	}

	public static void addCommand(Command command) {
		commands.add(command);
	}
}