package com.sd.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sd.data.Stock;
import com.sd.data.StockDAO;


@Controller
//@SessionAttributes("state")
public class StockController {
	@Autowired
	private StockDAO stockDao;

	public void setStateDao(StockDAO stockDao) {
		this.stockDao = stockDao;
	}

	public StockDAO getStockDao() {
		return stockDao;
	}

	@RequestMapping(path = "GetStockData.do", params = { "symbol" })
	public ModelAndView getStockbySymbol(String symbol) {
		ModelAndView mv = new ModelAndView();
		Stock st = this.stockDao.getStockbySymbol(symbol);
		if (st != null) {
			mv.addObject("stock", st);
		}
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path = "GetStockData.do", params = { "name" }, method = RequestMethod.POST)
	public ModelAndView getStockbyName(@RequestParam("name") String name) {
		ModelAndView mv = new ModelAndView();
		Stock st = stockDao.getStockbyName(name);
		if (st != null) {
			mv.addObject("stock", st);
		}
		mv.setViewName("result.jsp");
		return mv;
	}
	//To list industrys. HyperLinked to stock in industry
	@RequestMapping(path = "GetIndustry.do", params = { "industry" }, method = RequestMethod.GET)
	public List<String> getAllIndustry() {
		System.out.println("*****************");
		List<String> st = stockDao.getAllIndustry();
		return st;
	}
	
	@RequestMapping(path = "NewStock.do", method = RequestMethod.GET)
	public ModelAndView addStock() {
		Stock st = new Stock();
		return new ModelAndView("newStock.jsp", "stock", st);
		
	}

	@RequestMapping(path = "NewStock.do", method = RequestMethod.POST)
	public String addStock(@Valid Stock stock, Errors errors) {
		if (errors.getErrorCount() != 0) {
			return "newStock.jsp";
		}
		stockDao.addStock(stock);
		
		return "result.jsp";
	}
	
	
	
	
	@RequestMapping(path = "DeleteStock.do", method = RequestMethod.GET)
	public ModelAndView deleteStock() {
		Stock st = new Stock();
		return new ModelAndView("deleteStock.jsp", "stock", st);
		
	}
	
	@RequestMapping(path = "DeleteStock.do", method = RequestMethod.POST)
	public String deleteStock(@Valid Stock stock, Errors errors) {
		if (errors.getErrorCount() != 0) {
			return "deleteStock.jsp";
		}
		stockDao.deleteStock(stock);
		//stockDao.updateStock(stock);
	
		System.out.println(stock);
		
		return "result.jsp";
	}
	
	
	@RequestMapping(path = "UpdateStock.do", method = RequestMethod.GET)
	public ModelAndView updateStock(String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateStock.jsp");
		Stock st = stockDao.getStockbyName(name);
		System.out.println(st);
		mv.addObject("stock",st);
		
		return mv;
		
	}
	
	@RequestMapping(path = "UpdateStock.do", method = RequestMethod.POST)
	public String updateStock(@Valid Stock stock, Errors errors) {
		if (errors.getErrorCount() != 0) {
			return "updateStock.jsp";
		}
		stockDao.updateStock(stock);
		
		System.out.println(stock);
		
		return "result.jsp";
	}
	
	

//	@RequestMapping(path = "NewState.do", params = { "abbr" }, method = RequestMethod.POST)
//	public ModelAndView getByAbbreviation(@RequestParam("abbr") String a) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("redirect:stateAdded.do");
//		mv.addObject("state", stockDao.getStockBySymbol(a));
//		return mv;
//	}
//	
//
//	@RequestMapping(path = "NewState.do", method = RequestMethod.POST)
//	public ModelAndView newState(Stock state, RedirectAttributes redir) {
//		stockDao.addStock(state);
//		ModelAndView mv = new ModelAndView();
//		// mv.setViewName("result.jsp");
//		redir.addFlashAttribute("state", state); // add "state" to model for next request
//		mv.setViewName("redirect:stateAdded.do"); // redirect to new mapping
//		return mv;
//	}
//
//	// TODO : Implement another request handler for:
//	// path "stateAdded.do"
//	// method GET
//	// command object : State
//	// return : ModelAndView
//	// view : "result.jsp" or "result" if using InternalResourceViewResolver
//
//	@RequestMapping(path = "moveButton.do", params = "next", method = RequestMethod.GET)
//	public ModelAndView moveNext(@ModelAttribute("state") Stock state) {
//		ModelAndView mv = new ModelAndView();
//		Stock nextState = stockDao.getNextState(state);
//		mv.addObject("state", nextState);
//		mv.setViewName("result.jsp");
//		return mv;
//	}
//
//	@RequestMapping(path = "stateCreated.do", // mapping to handle Redirect
//			method = RequestMethod.GET) // "state" is already in model for
//										// use by result.jsp
//	public ModelAndView created() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		return mv;
//	}
//
}
