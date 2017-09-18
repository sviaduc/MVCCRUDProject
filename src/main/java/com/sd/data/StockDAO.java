package com.sd.data;

import java.util.List;

public interface StockDAO {
  //public Stock getStockbyId(String Id);
  public Stock getStockbyName(String name);
  public Stock getStockbySymbol(String symbol);
  public List<String> getAllIndustry();
  public Stock getAllStocksbyIndustry(String industry);
  //public Stock getAllStocksbySector(String sector);
  public void addStock(Stock stock);
  public void updateStock(Stock stock);
  public void deleteStock(Stock stock);
 
}
