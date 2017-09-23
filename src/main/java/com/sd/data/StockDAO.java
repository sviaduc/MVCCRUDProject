package com.sd.data;

import java.util.List;

public interface StockDAO {
  //public Stock getStockbyId(String Id);
  public Stock getStockbyName(String name);
  public Stock getStockbySymbol(String symbol);
  //public Stock getAllStocksbySector(String sector);
  public Stock addStock(Stock stock);
  public Stock updateStock(Stock stock);
  public void deleteStock(String symbol);
  public List<String> getAllIndustry();
  public Stock getAllStocksbyIndustry(String industry);
  public List<Stock> getAllStocks();
 
}
