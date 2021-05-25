package br.edu.ifsp.point.services;

import br.edu.ifsp.point.converters.UserVOConverter;
import br.edu.ifsp.point.exceptions.InvalidCredentials;
import br.edu.ifsp.point.exceptions.UserNotFoundException;
import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.vo.UserVO;
import br.edu.ifsp.point.repositories.UserRepository;
import br.edu.ifsp.point.utils.PasswordUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserVO> findAll(){
        return UserVOConverter.convertUsersToVO(userRepository.findAll());
    }

    public UserVO findById(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado na base de dados"));

        return UserVOConverter.convertUserToVO(user);
    }

    public UserVO login(UserVO userVO) {
        User user = userRepository.findByEmail(userVO.getEmail());

        if(user == null) {
            throw new UserNotFoundException("Usuário não encontrado na base de dados");
        }

        if(!PasswordUtils.checkPassword(userVO.getSenha(), user.getPassword())) {
            throw new InvalidCredentials("Credenciais inválidas");
        }

        return UserVOConverter.convertUserToVO(user);

    }

    public UserVO save(UserVO userVO) {

        userVO.setSenha(PasswordUtils.encrypt(userVO.getSenha()));

        User user = userRepository.save(UserVOConverter.convertVOToUser(userVO));

        return UserVOConverter.convertUserToVO(user);
    }

    public UserVO update(Long id, UserVO userVO) {

        UserVO userFound = this.findById(id);

        userFound.setNome(userVO.getNome());
        userFound.setEmail(userVO.getEmail());
        userFound.setSenha(PasswordUtils.encrypt(userVO.getSenha()));

        User user = userRepository.save(UserVOConverter.convertVOToUser(userFound));

        return UserVOConverter.convertUserToVO(user);
    }

    public void deleteById(Long id) throws ConstraintViolationException {

        this.findById(id);

        userRepository.deleteById(id);
    }

}
