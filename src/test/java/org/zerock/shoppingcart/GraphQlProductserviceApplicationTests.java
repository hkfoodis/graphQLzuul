package org.zerock.shoppingcart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GraphQlProductserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() throws Exception {

		final String body = "{\"query\" : \"{products { code name price } }\"}";

	    mockMvc
	        .perform(post("/graphql")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(body))
	        .andDo(print())
	        .andExpect(status().isOk());
	}
}
