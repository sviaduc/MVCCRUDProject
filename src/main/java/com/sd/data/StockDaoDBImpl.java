package com.sd.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StockDaoDBImpl implements StockDAO {
	private static String url = "jdbc:mysql://localhost:3306/watchlistdb";
	private String user = "watchlistdb";
	private String pass = "watchlistdb";
	
	

	public StockDaoDBImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Stock getStockbyName(String searchName) {
		Stock stock = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass); // Search query
			String sql = "SELECT name, symbol, industry"
					+ " FROM stock WHERE symbol = ?";
			PreparedStatement stmt = conn.prepareStatement(sql); // Need this stuff to connect and read through DB
			stmt.setString(1, searchName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { // if rows meet query criteria then they are read in...
				String name = rs.getString(1);
				String symbol = rs.getString(2);
				String industry = rs.getString(3);
				// ...build an object
				stock = new Stock(name, symbol, industry);

				// and would add to a list if this method was one of the methods below
			}
			rs.close(); // close resources
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock; // return whatever object we wanted
	}
	@Override
	public List<Stock> getAllStocks() {
		List<Stock> stocks = new ArrayList<Stock>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass); // Search query
			String sql = "SELECT name, symbol, industry"
					+ " FROM stock";
			PreparedStatement stmt = conn.prepareStatement(sql); // Need this stuff to connect and read through DB
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { // if rows meet query criteria then they are read in...
				String name = rs.getString(1);
				String symbol = rs.getString(2);
				String industry = rs.getString(3);
				// ...build an object
				Stock stock = new Stock(name, symbol, industry);
				stocks.add(stock);
				// and would add to a list if this method was one of the methods below
			}
			System.out.println(stocks.size());
			rs.close(); // close resources
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stocks; // return whatever object we wanted
	}


	@Override
	public Stock getStockbySymbol(String searchSymbol) {
			Stock stock = null;
			try {
				Connection conn = DriverManager.getConnection(url, user, pass); // Search query
				String sql = "SELECT name, symbol, industry"
						+ " FROM stock WHERE symbol = ?";
				PreparedStatement stmt = conn.prepareStatement(sql); // Need this stuff to connect and read through DB
				stmt.setString(1, searchSymbol);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) { // if rows meet query criteria then they are read in...
					String name = rs.getString(1);
					String symbol = rs.getString(2);
					String industry = rs.getString(3);
					// ...build an object
					stock = new Stock(name, symbol, industry);

					// and would add to a list if this method was one of the methods below
				}
				rs.close(); // close resources
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return stock; // return whatever object we wanted
		}

	@Override
	public Stock addStock(Stock stock) {
	    	Connection conn = null;
	    	  try {
	    	    conn = DriverManager.getConnection(url, user, pass);
	    	    conn.setAutoCommit(false); // START TRANSACTION
	    	    String sql = " INSERT INTO stock (name, symbol, industry)"
	    	                 + " VALUES (?,?,?)";
	    	    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    	    stmt.setString(1, stock.getName());
	    	    stmt.setString(2, stock.getSymbol());
	    	    stmt.setString(3, stock.getIndustry());
	    	    
	    	    int updateCount = stmt.executeUpdate(); // what does this mean?
	    	    if (updateCount == 1) {
	    	      ResultSet keys = stmt.getGeneratedKeys();
	    	      if (keys.next()) {
	    	        String newSymbolId = keys.getString(2);
	    	        stock.setSymbol(newSymbolId);
	    	      }
	    	    } else {
	    	      stock = null;
	    	    }
	    	    conn.commit(); // COMMIT TRANSACTION
	    	  } catch (SQLException sqle) {
	    	    sqle.printStackTrace();
	    	    if (conn != null) {
	    	      try { conn.rollback(); }
	    	      catch (SQLException sqle2) {
	    	        System.err.println("Error trying to rollback");
	    	      }
	    	    }
	    	    throw new RuntimeException("Error inserting stock " + stock);
	    	  }
	    	  return stock;
	    	

		
	}

	@Override
	public Stock updateStock(Stock stock) {
			Connection conn = null;
	  	  try {
	  	    conn = DriverManager.getConnection(url, user, pass);
	  	    conn.setAutoCommit(false); // START TRANSACTION
	  	  String sql = " INSERT INTO stock (name, symbol, industry)"
	                 + " VALUES (?,?,?)";
	  	    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	  	    		stmt.setString(1, stock.getName());
	  	    		stmt.setString(2, stock.getSymbol());
	  	    		stmt.setString(3, stock.getIndustry());
	  	    
	  	    
	  	    int updateCount = stmt.executeUpdate(); // what does this mean?
	  	    conn.commit(); // COMMIT TRANSACTION
	  	    stmt.close();
	  	    conn.close();
	  	  } catch (SQLException sqle) {
	  	    sqle.printStackTrace();
	  	    if (conn != null) {
	  	      try { conn.rollback(); }
	  	      catch (SQLException sqle2) {
	  	        System.err.println("Error trying to rollback");
	  	      }
	  	    }
	  	    throw new RuntimeException("Error updating stock " + stock);
	  	  }
	  	  return stock;
		}


	@Override
	public void deleteStock(String symbol){
			Connection conn = null;
	  	  try {
	  	    conn = DriverManager.getConnection(url, user, pass);
	  	    conn.setAutoCommit(false); // START TRANSACTION
	  	    String sql = " DELETE from stock where symbol = ? ";
	  	    PreparedStatement stmt = conn.prepareStatement(sql);
	  	    stmt.setString(1, symbol);
	  	    stmt.executeUpdate();
	  	 
	  	    conn.commit();
	  	    conn.close();// COMMIT TRANSACTION
	  	  } catch (SQLException sqle) {
	  	    sqle.printStackTrace();
	  	    if (conn != null) {
	  	      try { conn.rollback(); }
	  	      catch (SQLException sqle2) {
	  	        System.err.println("Error trying to rollback");
	  	      }
	  	    }
	  	    throw new RuntimeException("Error inserting stock " + symbol);
	  	  }
	  	 
		}
	
	@Override
	public List<String> getAllIndustry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock getAllStocksbyIndustry(String industry) {
		// TODO Auto-generated method stub
		return null;
	}

}
