package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StocksParsing;
import vkcore.VKManager;

import java.io.IOException;

public class HelloCommand extends Command {
	private String hello = "Привет! 👾\n" +
			"Выбери название компании из списка, чтобы посмотреть ее котировки 📊\n" +
			"Нажми \"следующие\", что бы я показал остальные 👉\n\n";

	public HelloCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage(hello, getStock(), message.getUserId());
	}

	private String getStock() {
		String stocksKeyboard = "{\n" +
				"    \"one_time\": true,\n" +
				"    \"buttons\": [\n" +
				"        [\n";

		String[][] stocks = new StocksParsing().getStocks(1);

		for (int i = 0; i < 10; i++) {
			stocksKeyboard += "\n{\n" +
					"                \"action\": {\n" +
					"                    \"type\": \"text\",\n" +
					"                    \"payload\": \"{\\\"button\\\": \\\"1\\\"}\",\n" +
					"                    \"label\": \"" + stocks[i][0] + "\"\n" +
					"                },\n" +
					"                \"color\": \"secondary\"\n" +
					"            }";

			if (i != 9) {
				if (i % 2 != 0 && i != 0) {
					stocksKeyboard += "],\n" +
							"        [";
				} else {
					stocksKeyboard += ",";
				}
			}
		}
		stocksKeyboard += "],\n" +
				"        [{\n" +
				"                \"action\": {\n" +
				"                    \"type\": \"text\",\n" +
				"                    \"payload\": \"{\\\"button\\\": \\\"1\\\"}\",\n" +
				"                    \"label\": \"следующие\"\n" +
				"                },\n" +
				"                \"color\": \"secondary\"\n" +
				"            }\n" +
				"        ]\n" +
				"    ]\n" +
				"}";

		return stocksKeyboard;
	}

	private String getEmoji(String percentage) {
		String emoji = "📈";

		for(String sign : percentage.split("")) {
			if (sign.equals("-")) {
				emoji = "📉";
			}
		}
		return emoji;
	}
}