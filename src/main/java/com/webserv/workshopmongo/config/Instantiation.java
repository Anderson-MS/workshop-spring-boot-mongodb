package com.webserv.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.webserv.workshopmongo.domain.Post;
import com.webserv.workshopmongo.domain.User;
import com.webserv.workshopmongo.dto.AuthorDTO;
import com.webserv.workshopmongo.repository.PostRepository;
import com.webserv.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
 
	@Autowired
	private UserRepository userReposiroty;
	
	@Autowired
	private PostRepository postReposiroty;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");	
		
		userReposiroty.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("14/11/2020"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO( maria));
		Post post2 = new Post(null, sdf.parse("15/11/2020"), "Bom Dia "," Acordei feliz hoje!", new AuthorDTO( maria));
		
		
		postReposiroty.saveAll(Arrays.asList(post1,post2));
	}
}
