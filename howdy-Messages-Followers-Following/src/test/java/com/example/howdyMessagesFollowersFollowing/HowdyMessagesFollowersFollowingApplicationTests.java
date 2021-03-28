package com.example.howdyMessagesFollowersFollowing;

import com.example.howdyMessagesFollowersFollowing.Message.Message;
import com.example.howdyMessagesFollowersFollowing.Message.MessageRepository;
import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.example.howdyMessagesFollowersFollowing.Subscription.SubscriptionRepository;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.junit.Assert;
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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class HowdyMessagesFollowersFollowingApplicationTests {

	@LocalServerPort
	int randomServerPort;


	@Autowired
	UserRepository userRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	MessageRepository messageRepository;


	@Test
	public void testUser() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/users";
		URI uri = new URI(baseUrl);

		String newUsername="unitTest";
		User newUser = new User(newUsername);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<User> request = new HttpEntity<>(newUser, headers);

		//Test post
		ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);

		Long createdId = result.getBody().getId();

		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getUsername(),newUsername);
		Assert.assertEquals(result.getBody().getUsername(),userRepository.findById(createdId).get().getUsername());

		//Test post error
		User errorUser = new User();
		errorUser.setUsername(null);
		request = new HttpEntity<>(errorUser, headers);
		try {
			restTemplate.postForEntity(uri, request, User.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex) {
			Assert.assertEquals(400, ex.getRawStatusCode());
		}

		String byIdUrl = baseUrl + "/" + createdId;
		uri = new URI(byIdUrl);

		//Test getById
		result = restTemplate.getForEntity(uri, User.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getUsername(), newUsername);

		//Test updateById
		String testUpdateUsername = "UnitTestUpdateUsername";
		User updateUser = new User(testUpdateUsername);

		request = new HttpEntity<>(updateUser, headers);
		try {
			restTemplate.put(uri, request);
			Assert.assertEquals(userRepository.findById(createdId).get().getUsername(), testUpdateUsername);
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
		}
	}

	@Test
	public void testSubscription() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/subscriptions";
		URI uri = new URI(baseUrl);

		String newUsername="unitTest";
		String newUsername1="unitTest1";
		User newUser = new User(newUsername);
		User newUser1 = new User(newUsername1);
		userRepository.save(newUser);
		userRepository.save(newUser1);

		Subscription newSubscription=new Subscription(newUser,newUser1);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Subscription> request = new HttpEntity<>(newSubscription, headers);

		//Test post
		ResponseEntity<Subscription> result = restTemplate.postForEntity(uri, request, Subscription.class);

		Long createdId = result.getBody().getId();

		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getId_follower().getUsername(),newUser.getUsername());
		Assert.assertEquals(result.getBody().getId_follower().getUsername(),subscriptionRepository.findById(createdId).get().getId_follower().getUsername());

		//Test post error
		Subscription errorSubscription = new Subscription();
		errorSubscription.setId_follower(null);
		request = new HttpEntity<>(errorSubscription, headers);
		try {
			restTemplate.postForEntity(uri, request, Subscription.class);
			Assert.fail();
		}
		catch(HttpServerErrorException ex) {
			Assert.assertEquals(500, ex.getRawStatusCode());
		}

		String byIdUrl = baseUrl + "/" + createdId;
		uri = new URI(byIdUrl);

		//Test getById
		result = restTemplate.getForEntity(uri, Subscription.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getId_follower().getUsername(), newUser.getUsername());

		//Test updateById
		String testUpdateUsername = "UnitTestUpdateUsername";
		User updateUser = new User(testUpdateUsername);
		userRepository.save(updateUser);


		Subscription updateSubscription = new Subscription(updateUser,newUser1);

		request = new HttpEntity<>(updateSubscription, headers);
		try {
			restTemplate.put(uri, request);
			Assert.assertEquals(subscriptionRepository.findById(createdId).get().getId_follower().getUsername(), updateUser.getUsername());
		}
		catch (HttpClientErrorException ex) {
			Assert.fail();
		}

		//Test deleteById
		try {
			restTemplate.delete(uri);
			List<Subscription> subscriptions = subscriptionRepository.findAll();
			Boolean contains = false;
			for (Subscription subscription : subscriptions) {
				if(subscription.getId() == createdId) {
					contains = true;
					break;
				}
			}
			Assert.assertEquals(false, contains);
		}
		catch (HttpServerErrorException ex) {
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
			result = restTemplate.getForEntity(uri, Subscription.class);
			Assert.fail();
		}
		catch (HttpClientErrorException ex) {
			Assert.assertEquals(404, ex.getRawStatusCode());
		}
	}

	@Test
	public void testMessage() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/messages";
		URI uri = new URI(baseUrl);

		String newUsername="unitTest";
		String newUsername1="unitTest1";
		User newUser = new User(newUsername);
		User newUser1 = new User(newUsername1);
		userRepository.save(newUser);
		userRepository.save(newUser1);
		String content="Unit Test poruka";
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();

		Message newMessage=new Message(newUser,newUser1,date,content);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Message> request = new HttpEntity<>(newMessage, headers);

		//Test post
		ResponseEntity<Message> result = restTemplate.postForEntity(uri, request, Message.class);

		Long createdId = result.getBody().getId();

		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getContent(),content);
		Assert.assertEquals(result.getBody().getContent(),messageRepository.findById(createdId).get().getContent());

		//Test post error
		Message errorMessage = new Message();
		errorMessage.setContent(null);
		request = new HttpEntity<>(errorMessage, headers);
		try {
			restTemplate.postForEntity(uri, request, Message.class);
			Assert.fail();
		}
		catch(HttpServerErrorException ex) {
			Assert.assertEquals(500, ex.getRawStatusCode());
		}

		String byIdUrl = baseUrl + "/" + createdId;
		uri = new URI(byIdUrl);

		//Test getById
		result = restTemplate.getForEntity(uri, Message.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(result.getBody().getContent(), content);

		//Test updateById
		String testUpdateContent = "UnitTestUpdate poruka";
		Message updateMessage = new Message(newUser,newUser1,date,testUpdateContent);

		request = new HttpEntity<>(updateMessage, headers);
		try {
			restTemplate.put(uri, request);
			Assert.assertEquals(messageRepository.findById(createdId).get().getContent(), testUpdateContent);
		}
		catch (HttpClientErrorException ex) {
			Assert.fail();
		}

		//Test deleteById
		try {
			restTemplate.delete(uri);
			List<Message> messages = messageRepository.findAll();
			Boolean contains = false;
			for (Message message : messages) {
				if(message.getId() == createdId) {
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
			result = restTemplate.getForEntity(uri, Message.class);
			Assert.fail();
		}
		catch (HttpClientErrorException ex) {
			Assert.assertEquals(404, ex.getRawStatusCode());
		}
	}

}
