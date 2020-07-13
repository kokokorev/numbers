package commands.logic;

import com.vk.api.sdk.objects.messages.Message;
import commands.Command;
import commands.UnknownCommand;
import stock.StocksList;

import java.util.Collection;
import java.util.logging.Logger;

/*
 * производит выборку команды из коллекции по первому слову в сообщении
 * */
public class CommandDeterminant {
	private static final Logger logger = Logger.getLogger(CommandDeterminant.class.getName());

	public static Command getCommand(Collection<Command> commands, Message message) {
		String body = message.getBody().toLowerCase();
		logger.info("message body: " + body + "\n");

		if (StocksList.getStocksList() != null) {
			for (int i = 0; i < 30; i++) {
				if (StocksList.getStocksList()[i][0].toLowerCase().equals(body)) {
					logger.info("user chose " + body + "\n");
					StocksList.setStock(i);
					body = "GetStock";
				}
			}
		}

		if ("начать".equals(body) || "start".equals(body)) {
			logger.info("user pressed Start button\n");
			body = "привет";
		}

		for (Command command : commands) {
			if (command.name.equals(body.split(" ")[0])) {
				return command;
			}
		}
		return new UnknownCommand("Unknown");
	}
}