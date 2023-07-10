package main.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entities.User;
import main.exceptions.NotFoundException;
import main.exceptions.UnauthorizedException;
import main.payloads.AuthenticationSuccessfullPayload;
import main.payloads.UserLoginPayload;
import main.payloads.UserPayload;
import main.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder bcrypt;

	// Versione 1
//	@PostMapping("/register")
//	public User register(@RequestBody UserPayload body) {
//		return new User();
//	}

	// Versione 2 con ResponseEntity e validazione
//	@PostMapping("/register")
//	public ResponseEntity<User> register(@RequestBody @Validated UserPayload body) {
//		User createdUser = userService.create(body);
//		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//	}

	// Versione 3 con ResponseEntity e validazione e BCrypt
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserPayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));
		User createdUser = userService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
//	
	// Versione 4 con ResponseEntity e validazione e BCrypt e invio email di
	// conferma
//	@PostMapping("/register")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User saveUser(@RequestBody @Validated UserPayload body) {
//		RestTemplate restTemplate = new RestTemplate();
//
//		body.setPassword(bcrypt.encode(body.getPassword()));
//		User createdUser = userService.create(body);
//
//		HttpEntity<User> requestBody = new HttpEntity<User>(createdUser);
//		restTemplate.postForObject("http://localhost:3002/auth/register", requestBody, User.class);
//
//		return createdUser;
//
//	}
	// Versione 1
//	@PostMapping("/login")
//	public String login(@RequestBody UserLoginPayload body) {
//		User u = new User("John", "Doe", "john@doe.com", "1234");
//		String token = JWTTools.createToken(u);
//		return token;
//	}

	// Versione 3 con ResponseEntity, personalizzazione del token e validazione
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UserLoginPayload body) {
//
//		User u = new User("John", "Doe", "john@doe.com", "johndoe1", "1234");
//		String token = JWTTools.createToken(u);
//
//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//	}

	// Versione 3 con ResponseEntity, personalizzazione del token, validazione e
	// controllo della password
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UserLoginPayload body)
//			throws NotFoundException {
//		User user = userService.findByEmail(body.getEmail());
//		if (!body.getPassword().matches(user.getPassword()))
//			throw new UnauthorizedException("Credenziali non valide");
//		String token = JWTTools.createToken(user);
//
//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//	}

	// Versione 4 con ResponseEntity, personalizzazione del token, validazione,
	// controllo della password con BCrypt
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UserLoginPayload body)
			throws NotFoundException {
		User user = userService.findByEmail(body.getEmail());

		String plainPW = body.getPassword();
		String hashedPW = user.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");
		String token = JWTTools.createToken(user);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
