package com.marcioeduca.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcioeduca.workshopmongo.domain.Post;
import com.marcioeduca.workshopmongo.repository.PostRepository;
import com.marcioeduca.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
		
	public Post findById(String id) {
		Post user = repo.findById(id).get();
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
}
