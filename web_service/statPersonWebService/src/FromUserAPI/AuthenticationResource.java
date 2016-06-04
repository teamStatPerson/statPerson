package ru.geekbrains.userapi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.geekbrains.userapi.service.TokenService;

@Path("/authentication")
public class AuthenticationResource {
	private TokenService tokenService;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			authenticate(username, password);

			tokenService = TokenService.getInstance();
			String token = tokenService.newToken(username);

			//возвращаем токен
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		// Здесь нужно сделать проверку имени и пароля, если не верные, то выбросить эксепшн
	}
}
