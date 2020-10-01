package ar.com.gestor.stats.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StatsControllerTest {
	
	private static String API_URL = "/42i/gestor/stats/v1";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void isWorking() throws Exception {

		mockMvc.perform(get(API_URL+"/isOk").contentType("application/json"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("isWorking"));

	}
	
	@Test
	public void getTopLocality() throws Exception {
		
		mockMvc.perform(get(API_URL+"/topLocality").contentType("application/json"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].name").value("Manhattan"));
	}
	
	@Test
	public void shearchNeighBirdHoods() throws Exception {
		
		mockMvc.perform(get(API_URL+"/searchNeightBirdHood?locality="+"Manhattan").contentType("application/json"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].name").value("Soho"))
				.andExpect(jsonPath("$.[1].name").value("Greenwich Village"));
	}
	
	@Test
	public void shearchNeighBirdHoods_withNoExistentLocality() throws Exception {
		
		mockMvc.perform(get(API_URL+"/searchNeightBirdHood?locality="+"xxx").contentType("application/json"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getFutherAwayLocations() throws Exception {
		
		mockMvc.perform(get(API_URL+"/futherAwayLocations").contentType("application/json"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].name").value("San Justo"))
				.andExpect(jsonPath("$.[1].name").value("Palermo"));
	}


}
