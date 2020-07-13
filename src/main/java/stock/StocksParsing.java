package stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/*
 * получаю таблицу со значениями с сайта и каждый элемент закидываю в массив строк,
 * который отправляю храниться в StockList, чтобы в команде GetStockCommand получить
 * данные по конкретной компании
 * */
public class StocksParsing {
	private String[][] stocks = new String[30][9];

	public String[][] getStocks(int pageNumber) {
		try {
			Document doc = Jsoup.connect("https://www.finam.ru/quotes/stocks/?pageNumber=" + pageNumber).get();

			Elements td = doc.select("td");

			int stockIndex = 0;
			for (int i = 0; i < 30; i++) {
				for (int j = 0; j < 9; j++) {
					if (stockIndex % 10 == 0) {
						stockIndex++;
					}
					if (td.get(stockIndex).text().equals("")) {
						stocks[i][j] = "-";
						stockIndex++;
					} else {
						stocks[i][j] = getName(td.get(stockIndex).text());
						stockIndex++;
					}
				}
			}

			StocksList.setStocksList(stocks);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stocks;
	}

	/*
	 * убираю лишную фразу про потенциал из строки с назване компании
	 *  */
	private String getName(String inputString) {
		String outputString = "";
		for (String tmp : inputString.split(" ")) {
			if (tmp.indexOf("Потенциал") == -1) {
				outputString += tmp + " ";
			} else {
				break;
			}
		}
		outputString = outputString.substring(0, outputString.length() - 1);
		return outputString;
	}
}
