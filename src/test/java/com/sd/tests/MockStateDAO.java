package com.sd.tests;


import java.util.ArrayList;
import java.util.List;

import data.State;
import data.StateDAO;

public class MockStateDAO implements StockDAO {

	List<Stock> states;
	public MockStateDAO() {
		states = new ArrayList<>();
		loadStates();
	}

	public void loadStates(){
		states.clear();
		states.add(new Stock("MI", "Michigan", "Lansing", "1.0", "2.0"));
		states.add(new Stock("CO", "Colorado", "Denver", "3", "4"));
	}

	@Override
	public Stock getStateByName(String name) {
		Stock s = null;
		for (Stock state : states) {
			if (state.getName().equalsIgnoreCase(name)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public Stock getStateByAbbreviation(String abbrev) {
		Stock s = null;
		for (Stock state : states) {
			if (state.getAbbreviation().equalsIgnoreCase(abbrev)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public void addState(Stock state) {
		states.add(state);
	}

}
