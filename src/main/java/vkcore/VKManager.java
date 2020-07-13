package vkcore;

/*
 * здесь бот отвечает конкретному пользователю
 * */
public class VKManager {
	public static VKConnect vkConnect;

	static {
		try {
			vkConnect = new VKConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg, String keyboard, int peerId) {
		if (msg == null) {
			System.out.println("null");
			return;
		}

		try {
			vkConnect.getVk().messages().send(vkConnect.getActor()).peerId(peerId).message(msg).unsafeParam("keyboard", keyboard).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}