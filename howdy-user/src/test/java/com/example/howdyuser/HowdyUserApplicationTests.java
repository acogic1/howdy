package com.example.howdyuser;

import com.example.howdyuser.User.User;
import com.example.howdyuser.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class HowdyUserApplicationTests {

	@LocalServerPort
	int randomServerPort;
	private Object User;

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testUser() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/register";
		URI uri = new URI(baseUrl);

		String newUserEmail="unitTest@etf.unsa.ba";
		User newUser = new User(newUserEmail,"unitTest1","UnitTest","Ovo je UnitTest");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<User> request = new HttpEntity<>(newUser, headers);

		//Test post
		/*ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);

		Long createdId = result.getBody().getId();

		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getEmail(),newUserEmail);
		Assert.assertEquals(result.getBody().getEmail(),userRepository.findById(createdId).get().getEmail());

		//Test post error
		User errorGenre = new User();
		errorGenre.setUsername(null);
		request = new HttpEntity<>(errorGenre, headers);
		try {
			restTemplate.postForEntity(uri, request, User.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex) {
			Assert.assertEquals(400, ex.getRawStatusCode());
		}
		/*final String basepUrl = "http://localhost:" + randomServerPort + "/users";
		//URI uri = new URI(baseUrl);
		String byIdUrl = basepUrl + "/" + createdId;
		uri = new URI(byIdUrl);

		//Test getById
		result = restTemplate.getForEntity(uri, User.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getEmail(), newUserEmail);

		//Test updateById
		String testUpdateEmail = "UnitTestUpdateUser@email.ba";
		User updateUser = new User(testUpdateEmail,"unitTest1","UnitTest","Ovo je UnitTest");

		request = new HttpEntity<>(updateUser, headers);
		try {
			restTemplate.put(uri, request);
			Assert.assertEquals(userRepository.findById(createdId).get().getEmail(), testUpdateEmail);
		}
		catch (HttpClientErrorException ex) {
			Assert.fail();
		}

		//Test deleteById
		try {
			restTemplate.delete(uri);
			List<User> users = userRepository.findAll();
			Boolean contains = false;
			for (User user : users) {
				if(user.getId() == createdId) {
					contains = true;
					break;
				}
			}
			Assert.assertEquals(false, contains);
		}
		catch (HttpClientErrorException ex) {
			Assert.fail();
		}

		//Test deleteById error id does not exist
		try {
			restTemplate.delete(uri);
			Assert.fail();
		}
		catch (HttpClientErrorException ex) {
			Assert.assertEquals(404, ex.getRawStatusCode());
		}

		//Test getById error
		try {
			result = restTemplate.getForEntity(uri, User.class);
			Assert.fail();
		}
		catch (HttpClientErrorException ex) {
			Assert.assertEquals(404, ex.getRawStatusCode());
		}*/
	}
}
