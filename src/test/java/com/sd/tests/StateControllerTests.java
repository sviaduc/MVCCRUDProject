package com.sd.tests;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import controllers.StateController;
import data.State;

@RunWith(SpringJUnit4ClassRunner.class)//Run tests using this special Spring class
@ContextConfiguration(locations = { "StateControllerTests-context.xml" })//...with this Spring config file
@WebAppConfiguration //...and treat it like a WibApp (something that TomCat could run)
public class StateControllerTests {
	MockMvc mockMvc; //Need one of these to "perform()" requests

	@Autowired
	WebApplicationContext wc;  //This got created ehen I tild Spring to treat this like a WebApp
								//It's where all Springs beans live(Controllers,DAO's, any..
	@Autowired
	StateController controller; //Spring created a controller becaise I annotated my class with @Controller..
								//Heres how I get ahold of that object
	
	MockStateDAO mockDAO;		//This is a field

	@Before
	public void setUp() {
		mockDAO = wc.getBean(MockStateDAO.class); //This MockStateDAO was created in aSteateContrillerTests-contex

		// TODO - uncomment below to add a Mock object, which we control
		 controller.setStateDao(mockDAO);
		
		// TODO - build the MockMvc object with MockMvcBuilders
		 mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	@After
	public void tearDown(){
		mockDAO.loadStates(); //cleanup
	}

	@Test
	public void test_GET_GetStateData_do_with_valid_name_param_returns_State() {
		try {
			MvcResult result = mockMvc.perform(get("/GetStateData.do").param("name", "Colorado")) //end of perform(). can chain params
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("result.jsp", mv.getViewName());
			ModelMap map = mv.getModelMap(); //what is in the model and available to the JSP?
			assertNotNull(map.get("state"));

			State st = (State) map.get("state");
			assertEquals("Colorado", st.getName());
			assertEquals("CO", st.getAbbreviation());
			assertEquals("3", st.getLatitude());
			assertEquals("4", st.getLongitude());
			// TODO - test other fields

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test_GET_GetStateData_do_with_invalid_name_param_does_not_add_State_to_model() {
		try {
			MvcResult result = mockMvc.perform(get("/GetStateData.do").param("name", "TotallyInvalid")) //end of perform(). can chain params
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("result.jsp", mv.getViewName());
			ModelMap map = mv.getModelMap(); //what is in the model and available to the JSP?
			assertNull(map.get("state"));

			State st = (State) map.get("state");
			assertEquals("Colorado", st.getName());
			assertEquals("CO", st.getAbbreviation());
			assertEquals("3", st.getLatitude());
			// TODO - test other fields

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test_GET_GetStateData_do_with_valid_abbr_param_adds_State_to_model() {
		try {
			MvcResult res = mockMvc.perform(get("/GetStateData.do").param("abbr", "MI"))
			.andExpect(status().isOk()).andReturn();
			//.andExpect(status().is3xxRedirection());
			ModelAndView modelAndView = res.getModelAndView();
			assertEquals("result.jsp", modelAndView.getViewName());
			State st = (State)modelAndView.getModelMap().get("state");
			assertNotNull(st);
			assertEquals("Michigan", st.getName());
			
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void test_GET_GetStateData_do_with_invalid_abbr_param_does_not_add_State_to_model() {
		// TODO - complete this and remove fail("implement");
		//fail("implement");
	}

	@Test
	public void test_POST_NewState_do_adds_state_and_returns_resultJSP() {
		try {
		mockMvc.perform(post("/NewState.do").param("name", "MyState").param("capitol","Cap").param("abbreviation","Abbrev")
				.param("latitude","LAT").param("longitude", "LONG").andReturn());
		assertEquals("results.jsp"), result.getModelAndView().getViewName());
			
			//check that the mock objects list for the state
			State state = this.mockDAO.getStateByName("MyState");
			assertNotNull(state);
			assertThat(state, 
					allOf(
					hasProperty("name", is("MyState")),
					hasProperty("capitol", is("CAP")),
					hasProperty("abbreviation", is("Abbrev")),
					hasProperty("Lattitude", is("LAT")),
					hasProperty("Longitude", is("LONG")))
					);
					
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
		// TODO - complete this and remove fail("implement");
		//fail("implement");
		
		// check the mock object's list directly for the new state
	}
	
	// TODO - change previous test according to directions
	
//	@Test
//	public void test_GET_stateAdded_do_returns_resultJSP_view() {
//		// TODO - complete this and remove fail("implement");
//		//fail("implement");
//	}
}
