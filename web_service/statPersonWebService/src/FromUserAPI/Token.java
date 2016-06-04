package ru.geekbrains.userapi.model;

import java.util.Date;

public class Token {
	static final int TIME_TO_LIVE_MIN = 60;
	
	private Date beginDate;
	private String token;
	
	public Token(User user) {
		//генерируем рандом строку из имени
		this.token = user.getName();
		this.beginDate = new Date();
	}
}
