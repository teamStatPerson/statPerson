package ru.geekbrains.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.geekbrains.userapi.model.*;
import ru.geekbrains.userapi.database.*;

public class UserService {
	private static Map<Integer, User> users = DatebaseClass.getUsers();

	public UserService() {
		users.put(1, new User(1, "Иванов"));
		users.put(2, new User(2, "Петров"));
		users.put(3, new User(3, "Сидоров"));
	}
	
	public List<User> getAllUsers() {
		return new ArrayList<User> (users.values());
	}
	
	public User getUser(int id) {
		return users.get(id);
	}
	
	public User addUser(User user) {
		user.setId(users.size() + 1);
		users.put(user.getId(), user);
		return user;
	}
	
	public User updateUser(User user) {
		if (user.getId() <= 0) {
			return null;
		}
		users.put(user.getId(), user);
		return user;
	}
	
	public User removeUser(int id) {
		return users.remove(id);
	}
}
