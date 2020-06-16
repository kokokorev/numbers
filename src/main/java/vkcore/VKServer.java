package vkcore;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/*
 * запуск "сервера" для подключения к группе и отправки комманд на обработку
 * */
public class VKServer {
	private static final Logger logger = Logger.getLogger(VKServer.class.getName());
	public static VKConnect vkConnect;

	static {
		try {
			vkConnect = new VKConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NullPointerException, ApiException, InterruptedException {
		System.out.println("Done. Server running.");

		/* каждые 300 мс бот пытается получить сообщение */
		while (true) {
			Thread.sleep(300);
			try {
				Message message = vkConnect.getMessage();
				if (message != null) {
					logger.info("message received:\n" + message + "\n");
					ExecutorService exec = Executors.newCachedThreadPool();
					exec.execute(new Messenger(message));
				}
			} catch (ClientException e) {
				System.out.println("Problems. Reconnecting...");
				final int RECONNECT_TIME = 10000;
				Thread.sleep(RECONNECT_TIME);

			}
		}
	}
}
