package com.ggkps.superflix;

import com.ggkps.superflix.controllers.AuthenticationController;
import com.ggkps.superflix.controllers.TestController;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
	private TestController controller;

	@Autowired
	private AuthenticationController authController;

	@Autowired
	private UserService userService;

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
				.content("{\"username\":\"admin\",\"password\":\"admin\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		/*
		mockMvc.perform(get("/api/v1/admin/movie/1")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwic3ViIjoidXNlcm5hbWUiLCJpYXQiOjE3MTUyODUzNTksImV4cCI6MTcxNTMwMzM1OX0.YXLjIEDwp2K5KNEJWcD1FV4XOHL9cy9qKimSRqGDMz4"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/v1/admin/movie/1")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTcxNTI4NTI3OCwiZXhwIjoxNzE1MzAzMjc4fQ.vU50LheqWGdlgiidtVzJelTV555NnUUyYP2sbDcTPpo"))
				.andExpect(status().isForbidden());
		 */
	}

	@Test
	void shouldCreateMovieWhenSendingCreateMovieRequest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE1Mjg3NTM4LCJleHAiOjE3MTU0Njc1Mzh9.A07K5W7G_2JAxPFTdv2rn0crnnaPF4nce5Y62WswqdE";
		token = token.trim();
		Optional<User> user = userService.getUserFromToken(token);
		if (user.isEmpty()) {
			System.out.println("User not found");
		}


		mockMvc.perform(get("/api/v1/admin/movie")
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
		;

		mockMvc.perform(post("/api/v1/admin/movie")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"duration\":120,\"path\":\"path\",\"title\":\"title\",\"description\":\"description\",\"category\":\"category\",\"creator\":\"creator\",\"release_at\":\"2021-03-08\"}")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
		;

		mockMvc.perform(post("/api/v1/admin/movie")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"duration\":120,\"path\":\"path\",\"title\":\"title\",\"description\":\"description\",\"category\":\"category\",\"creator\":\"creator\",\"release_at\":\"2021-03-08\"}")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
		;
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
