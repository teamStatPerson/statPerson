package ru.geekbrains.userapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.geekbrains.userapi.model.User;
import ru.geekbrains.userapi.service.UserService;

@Path("/users")
public class UserResource {
	
	UserService userService = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("userId") int userId) {
		return userService.getUser(userId);
	}
	
}
