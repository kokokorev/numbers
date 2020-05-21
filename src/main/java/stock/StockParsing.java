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

		int stockIndex = 0;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 9; j++) {
				if (stockIndex % 10 == 0) {
					stockIndex++;
				}
				if (td.get(stockIndex).text().equals("")) {
					stock[i][j] = "-";
					stockIndex++;
				} else {
					stock[i][j] = getName(td.get(stockIndex).text());
					stockIndex++;
				}
			}
		}
		return stock;
	}

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
