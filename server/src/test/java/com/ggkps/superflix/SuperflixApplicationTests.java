package com.ggkps.superflix;

import com.ggkps.superflix.controllers.AuthenticationController;
import com.ggkps.superflix.controllers.HomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.ggkps.superflix.entities.Movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class SuperflixApplicationTests {


	@Autowired
	private HomeController controller;

	@Autowired
	private AuthenticationController authController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(authController).isNotNull();
	}

	@Test
	void shouldReturnTokenWhenSendingLoginRequest() throws Exception {
		mockMvc.perform(post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"username\",\"password\":\"password\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		// mockMvc.perform(get("/api/v1/admin/movie/1")
		//		.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6InVzZXJuYW1lIiwiaWF0IjoxNzE1MTk5NjY0LCJleHAiOjE3MTUyMTc2NjR9.yYDd4uDWoN53BlKp2fNV_qJMethWbE0idq6wGfnbqqk"))
		//		.andExpect(status().isOk())
		//		.andExpect(content().string("{\"id\":1,\"duration\":120,\"path\":\"/path/to/movie\",\"visualContent\":null}"));

		mockMvc.perform(get("/api/v1/admin/movie/1")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6InVzZXJuYW1lIiwiaWF0IjoxNzE1MTk5NjY0LCJleHAiOjE3MTUyMTc2NjR9.yYDd4uDWoN53BlKp2fNV_qJMethWbE0idq6wGfnbqqk"))
				.andExpect(status().isForbidden());
	}

	@Test
	void shouldReturnOkWhenSendingLogoutRequest() throws Exception {
		mockMvc.perform(get("/api/v1/auth/logout"))
				.andDo(print())
				.andExpect(content().string("Logout!"));
	}

	@Test
	void shouldReturnWelcomeWhenSendingGetRequest() throws Exception {
		mockMvc.perform(get("/home/"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome to Superflix!"));
	}
}
