package stock;

public class StockList {
	private static String[][] stockList;
	private static String stock = "";

	public static String[][] getStockList() {
		return stockList;
	}

	public static void setStockList(String[][] stocks) {
		stockList = stocks;
	}

	public static String getStock() {
		return stock;
	}

	public static void setStock(int line) {
		stock += "\"" + stockList[line][0] + "\"\n";
		stock += stockList[line][1] + "\n";
		stock += stockList[line][2] + "\n";
		stock += stockList[line][3] + "\n";
		stock += stockList[line][4] + "\n";
		stock += stockList[line][5] + "\n";
		stock += stockList[line][6] + "\n";
		stock += stockList[line][7] + "\n";
		stock += stockList[line][8] + "\n";
	}
}
