package br.edu.ifsp.point.services;

import br.edu.ifsp.point.exceptions.handler.UserNotFoundException;
import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.repositories.UserRepository;
import br.edu.ifsp.point.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado na base de dados"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {

        User userFound = findById(id);

        return userRepository.save(userFound);
    }

    public void deleteById(Long id) {

        findById(id);

        userRepository.deleteById(id);
    }

}
