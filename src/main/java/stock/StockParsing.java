package stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StockParsing {
	private String[][] stock = new String[30][9];

	public String[][] getStock(int pageNumber) throws IOException {
		Document doc = Jsoup.connect("https://www.finam.ru/quotes/stocks/?pageNumber=" + pageNumber).get();

		Elements td = doc.select("td");

		int stockIndex = 1;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 9; j++) {
				if (!td.get(stockIndex).text().equals("")) {
					stock[i][j] = td.get(stockIndex).text();
					stockIndex++;
				} else {
					j--;
					stockIndex++;
				}
			}
		}
		return stock;
	}
}
