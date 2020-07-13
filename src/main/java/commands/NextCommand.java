package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StocksParsing;
import vkcore.VKManager;

public class NextCommand extends Command {
	private static int page = 1;
	private static int stockFrom = 10;
	private static int stockTo = 20;

	public NextCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage("Следующие акции", getStock(), message.getUserId());
	}

	private String getStock() {
		if (stockTo % 30 == 0) {
			page++;
			stockFrom = 10;
			stockTo = 20;
		}

		String stocksKeyboard = "{\n" +
				"    \"one_time\": true,\n" +
				"    \"buttons\": [\n" +
				"        [\n";

		String[][] stocks = new StocksParsing().getStocks(page);

		for (int i = stockFrom; i < stockTo; i++) {
			if (stocks[i][0].length() >= 40) {
				stocksKeyboard += "\n{\n" +
						"                \"action\": {\n" +
						"                    \"type\": \"text\",\n" +
						"                    \"payload\": \"{\\\"button\\\": \\\"1\\\"}\",\n" +
						"                    \"label\": \"" + "ГОВНО" + "\"\n" +
						"                },\n" +
						"                \"color\": \"secondary\"\n" +
						"            }";
			} else {
				stocksKeyboard += "\n{\n" +
						"                \"action\": {\n" +
						"                    \"type\": \"text\",\n" +
						"                    \"payload\": \"{\\\"button\\\": \\\"1\\\"}\",\n" +
						"                    \"label\": \"" + stocks[i][0] + "\"\n" +
						"                },\n" +
						"                \"color\": \"secondary\"\n" +
						"            }";
			}

			if (i != 9 || i != 19 || i != 29) {
				if (i % 2 != 0 && i != 0) {
					stocksKeyboard += "],\n" +
							"        [";
				} else {
					stocksKeyboard += ",";
				}
			}
		}
		stocksKeyboard += "        {\n" +
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

		stockFrom += 10;
		stockTo += 10;

		System.out.println(stocksKeyboard);

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