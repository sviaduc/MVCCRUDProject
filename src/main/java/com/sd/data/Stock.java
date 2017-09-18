package com.sd.data;

public class Stock {
  //private String id;
  //private String sector;
  private String industry;
  private String name;
  private String symbol;
  //private String marketcap;

  public Stock() {
  }

  
public Stock( String industry, String name, String symbol) {
	super();
	this.industry = industry;
	this.name = name;
	this.symbol = symbol;
}

public String getIndustry() {
	return industry;
}

public void setIndustry(String industry) {
	this.industry = industry;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSymbol() {
	return symbol;
}

public void setSymbol(String symbol) {
	this.symbol = symbol;
}


@Override
public String toString() {
	return "Stock [industry=" + industry + ", name=" + name + ", symbol=" + symbol + "]";
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((industry == null) ? 0 : industry.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
	return result;
}








  
  
}
