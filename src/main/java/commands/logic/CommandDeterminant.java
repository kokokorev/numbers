package commands.logic;

import com.vk.api.sdk.objects.messages.Message;
import commands.Command;
import commands.UnknownCommand;

import java.util.Collection;

/*
 * производит выборку команды из коллекции по первому слову в сообщении
 * */
public class CommandDeterminant {
	public static Command getCommand(Collection<Command> commands, Message message) {
		String body = message.getBody().toLowerCase();

		for (Command command : commands) {
			if (command.name.equals(body.split(" ")[0])) {
				return command;
			}
		}
		return new UnknownCommand("Unknown");
	}
}