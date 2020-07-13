package commands;

import com.vk.api.sdk.objects.messages.Message;
import vkcore.VKManager;

public class UnknownCommand extends Command {
	private String deleteKeyboard = "{\n" +
			"    \"one_time\": true,\n" +
			"    \"buttons\": []\n" +
			"}";

	public UnknownCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage("Неизвестная команда.", deleteKeyboard, message.getUserId());
	}
}