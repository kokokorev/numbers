package stock;

/*
 * хранилище страницы, чтобы можно было получить данные по компании в GetStockCommand
 * */
public class StocksList {
	private static String[][] stocksList;
	private static String stock = "";

	public static String[][] getStocksList() {
		return stocksList;
	}

	public static void setStocksList(String[][] stocks) {
		stocksList = stocks;
	}

	public static String getStock() {
		return stock;
	}

	public static void setStock(int line) {
		stock = "";
		stock += "\"" + stocksList[line][0] + "\"\n\n";

		stock += "Последняя сделка:\n" + stocksList[line][1] + " 💸\n\n";

		String emoji = "📈";
		for (String sign : stocksList[line][2].split("")) {
			if (sign.equals("-")) {
				emoji = "📉";
			}
		}
		stock += "Изменение цены:\n" + stocksList[line][2] + emoji + "\n\n";

		stock += "Открытие:\n" + stocksList[line][3] + " 🔓\n\n";
		stock += "Максимальная:\n" + stocksList[line][4] + " 🐘\n\n";
		stock += "Минимальная:\n" + stocksList[line][5] + " 🐁\n\n";
		stock += "Закрытие:\n" + stocksList[line][6] + " 🔒\n\n";
		stock += "Объем:\n" + stocksList[line][7] + " 📊\n\n";
		stock += "Время обновления:\n" + stocksList[line][8] + " ⌚\n\n";
	}
}
