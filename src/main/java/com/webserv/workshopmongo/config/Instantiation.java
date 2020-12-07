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
import com.webserv.workshopmongo.dto.CommentDTO;
import com.webserv.workshopmongo.repository.PostRepository;
import com.webserv.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userReposiroty;
	@Autowired
	private PostRepository postReposiroty;
	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));
		
		/*- CRUD de candidato com os seguintes campos:  
			- Nome, 
			- E-mail, 
			- Idade, 
			- Url linkedin,  */
		
		/*
		 * - Combo múltipla escolha das tecnologias que o programador tem conhecimento. (As opções devem ser as seguintes: C#, Javascript, Nodejs, Angular, React, Ionic, Mensageria, PHP, Laravel) 
		 
		 */
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"),"Analista de Sistemas / Aplicações", "C#, Javascript, Nodejs", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Programador / Desenvolvedor", "Angular, React, Ionic", new AuthorDTO(alex));
		Post post3 = new Post(null, sdf.parse("23/03/2018"), "Analista de Sistemas ", "Mensageria, PHP, Laravel", new AuthorDTO(bob));
		Post post4 = new Post(null, sdf.parse("23/03/2018"), "Analista de Sistemas ", "Mensageria, PHP, Laravel", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO("URL Linkedin : https://www.linkedin.com "+"Idade: 30 ", sdf.parse("21/03/2018"), new AuthorDTO(maria));
		CommentDTO c2 = new CommentDTO("URL Linkedin : https://www.linkedin.com " + "Idade: 29", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("URL Linkedin : https://www.linkedin.com " + "Idade: 31 ", sdf.parse("23/03/2018"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c3));
		post3.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2, post3));

		maria.getPosts().addAll(Arrays.asList(post1, post2,post3));
		userReposiroty.save(maria);
		
		
		
	}
}