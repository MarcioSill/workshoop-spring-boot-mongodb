package com.marcioeduca.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcioeduca.workshopmongo.domain.User;
import com.marcioeduca.workshopmongo.dto.UserDTO;
import com.marcioeduca.workshopmongo.repository.UserRepository;
import com.marcioeduca.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
		
	}
	
	
	/*   UpDate correção
	 * Olá Marcio... ainda será preciso realizar mais uma correção no código para o Spring 2.x.x. Pois na verdade repo.findByid(obj.getId()) irá retornar um 'Optional<User>' e não um 'User'. Para extrair o 'User' do 'Optional<User>' chame a função '.get()' do próprio 'Optional<>'.

O código ficaria assim:



public User update(User entity) {

     User newObj = repo.findById(entity.getId()).get();

     updateData(newObj, entity);

     return repo.save(newObj);

}
	 */
	public User upDate(User obj) {
		User newObj = repo.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
