package com.sd.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
@Component
public class StockDAOImpl implements StockDAO {
  private static final String FILE_NAME="/WEB-INF/MOCK_DATA.txt";
  private List<Stock> stocks = new ArrayList<>();
 
  /*
   * Use Autowired to have Spring inject an instance
   * of a WebApplicationContext into this object after
   * creation.  We will use the WebApplicationContext to
   * retrieve an ServletContext so we can read from a 
   * file.
   */
  @Autowired 
  private WebApplicationContext wac;

  /*
   * The @PostConstruct method is called by Spring after 
   * object creation and dependency injection
   */
  @PostConstruct
  public void init() {
    // Retrieve an input stream from the servlet context
    // rather than directly from the file system
    try (
        InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
      ) {
      String line = buf.readLine();
      while ((line = buf.readLine()) != null) {
        String[] tokens = line.split("	");
        String id = tokens[0];
        String sector = tokens[1];
        String industry = tokens[2];
        String name = tokens[3];
        String symbol = tokens[4];
        String marketcap = tokens[5];
        stocks.add(new Stock(name, symbol, industry));
      }
    } catch (Exception e) {
      System.err.println(e);
    }
   
  }

  @Override
  public Stock getStockbySymbol(String symbol) {
	  
    Stock s = null;
    for (Stock stock : stocks) {
		if (stock.getSymbol().equalsIgnoreCase(symbol)) {
			s = stock;
			break;
		}
	}
	return s;
   
  }
  @Override
  public Stock getStockbyName(String name) {
    Stock s = null;
    for (Stock stock : stocks) {
    		if (stock.getName().equalsIgnoreCase(name)) {	
    		s = stock;
			break;
		}
	}
	return s;
  }

  
  
  @Override
  public void addStock(Stock stock) {
	  stocks.add(stock); 
  }
  
  @Override
  public void deleteStock(Stock stock) {
	  Stock s =getStockbySymbol(stock.getSymbol());
	  stocks.remove(s);
  }
  
  @Override
  public void updateStock(Stock stock) {
	 // Stock s =getStockbySymbol(stock.getSymbol());
	  stock.setName(stock.getName());  
	  stock.setSymbol(stock.getSymbol());  
	  stock.setIndustry(stock.getIndustry());  
  }

@Override
public Stock getAllStocksbyIndustry(String industry) {
    Stock s = null;
	for (Stock stock : stocks) {
		if (stock.getIndustry().equals(industry)) {	
		s = stock;
		break;
		}
	}
	return s;
}


@Override
public List<String> getAllIndustry() {
	List<String> list = new ArrayList<>();
	for (Stock stock : stocks) {
		list.add(stock.getIndustry());
	}
	return list;
}




  

}

