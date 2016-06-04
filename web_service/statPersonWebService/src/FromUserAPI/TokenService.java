package ru.geekbrains.userapi.service;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrains.userapi.model.Token;
import ru.geekbrains.userapi.model.User;

public class TokenService {
	public static TokenService instance;
	private Map<User, Token> tokens = new HashMap<>();
	
	public static TokenService getInstance(){
		if (instance == null) {
			instance = new TokenService();
		}
		return instance;	
	}
	
	public String newToken(String userName) {
		//По имени пользователя генерируем токен
		return "token";
	}
}
