package com.exam.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.rest.client.RestTemplateClient;
import com.exam.utils.Geo;

@Service
public class ServiceImpl implements IService {

	@Autowired
	protected RestTemplateClient restClient;

	@Override
	public User[] getLondonUsers() {
		User[] users = restClient.newRequest(HttpMethod.GET, "/city/{city}/users").withUriVariables("London")
				.withContentType(MediaType.APPLICATION_JSON).withResponseAs(User[].class).getResponseEntity().getBody();
		return users;
	}

	@Override
	public User[] getAllUsers() {
		User[] allUsers = restClient.newRequest(HttpMethod.GET, "/users").withContentType(MediaType.APPLICATION_JSON)
				.withResponseAs(User[].class).getResponseEntity().getBody();
		return allUsers;
	}

	@Override
	public User[] getLondonWithInFiftyMilesUsers() {
		double lat1 = 51.509854;
		double lon1 = -0.118081;

		User[] allUsers = getAllUsers();

		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < allUsers.length; i++) {
			double distance = Geo.distance(lat1, lon1, allUsers[i].getLatitude(), allUsers[i].getLongitude());
			if (distance <= 50.0) {
				userList.add(allUsers[i]);
			}
		}
		User[] londonUsers = new User[userList.size()];
		// ArrayList to Array Conversion
		for (int i = 0; i < userList.size(); i++)
			londonUsers[i] = userList.get(i);

		return londonUsers;
	}

}
