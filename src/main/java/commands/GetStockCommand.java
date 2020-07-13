package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StocksList;
import vkcore.VKManager;

public class GetStockCommand extends Command {
	public GetStockCommand(String name) {
		super(name);
	}
	private String deleteKeyboard = "{\n" +
			"    \"one_time\": true,\n" +
			"    \"buttons\": []\n" +
			"}";

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage(getStock(), deleteKeyboard, message.getUserId());
	}

	private String getStock() {
		String stock = "";

		for (int i = 0; i < 9; i++) {
			stock = StocksList.getStock();
		}
		return stock;
	}
}