package com.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.exam.model.User;
import com.exam.service.IService;
import com.exam.utils.Geo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GeoAppTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private IService service;

  private String userStr ="[\n" + 
  		"    {\n" + 
  		"        \"id\": 266.0,\n" + 
  		"        \"first_name\": \"Ancell\",\n" + 
  		"        \"last_name\": \"Garnsworthy\",\n" + 
  		"        \"email\": \"agarnsworthy7d@seattletimes.com\",\n" + 
  		"        \"ip_address\": \"67.4.69.137\",\n" + 
  		"        \"latitude\": 51.655396,\n" + 
  		"        \"longitude\": 0.0572553\n" + 
  		"    },\n" + 
  		"    {\n" + 
  		"        \"id\": 322.0,\n" + 
  		"        \"first_name\": \"Hugo\",\n" + 
  		"        \"last_name\": \"Lynd\",\n" + 
  		"        \"email\": \"hlynd8x@merriam-webster.com\",\n" + 
  		"        \"ip_address\": \"109.0.153.166\",\n" + 
  		"        \"latitude\": 51.67108,\n" + 
  		"        \"longitude\": 0.8078532\n" + 
  		"    },\n" + 
  		"    {\n" + 
  		"        \"id\": 554.0,\n" + 
  		"        \"first_name\": \"Phyllys\",\n" + 
  		"        \"last_name\": \"Hebbs\",\n" + 
  		"        \"email\": \"phebbsfd@umn.edu\",\n" + 
  		"        \"ip_address\": \"100.89.186.13\",\n" + 
  		"        \"latitude\": 51.548943,\n" + 
  		"        \"longitude\": 0.3860497\n" + 
  		"    }\n" + 
  		"]";


  @Test
  public void testGetDistanceInMiles() {
    double lat1 = 51.509854;
    double lat2 = 51.752012;

    double lon1 = -0.118081;
    double lon2 = -1.257657;
    double distance = Geo.distance(lat1, lon1, lat2, lon2);
    assertEquals(44.857631870283484, distance);
  }

  /**
   * 
   * @throws Exception
   */
  @Test
  public void testUsersInLondonWithInFiftyMiles() throws Exception {
    double lat1 = 51.509854;
    double lon1 = -0.118081;

    BDDMockito.given(service.getLondonWithInFiftyMilesUsers())
        .willReturn(getTestUsers());

    MvcResult result = mvc.perform(get("/londonUsers"))
        .andReturn();
    User[] users = new ObjectMapper().readValue(result.getResponse()
        .getContentAsString(), User[].class);
    List<User> londonUsers = new ArrayList<User>();
    for (int i = 0; i < users.length; i++) {
      double distance = Geo.distance(lat1, lon1, users[i].getLatitude(), users[i].getLongitude());
      if (distance <= 50.0) {
        londonUsers.add(users[i]);
      }
    }
    assertEquals(3, londonUsers.size());
  }


  /**
   * 
   * @return
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  public User[] getTestUsers() throws JsonMappingException, JsonProcessingException {
    return new ObjectMapper().readValue(userStr, User[].class);
  }


}
