package br.edu.ifsp.point.converters;

import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class UserVOConverter {

    public static  User convertVOToUser(UserVO userVO){

        User user = new User();

        user.setId(userVO.getKey());
        user.setName(userVO.getNome());
        user.setEmail(userVO.getEmail());
        user.setPassword(userVO.getSenha());

        return user;
    }

    public static UserVO convertUserToVO(User user){

        UserVO userVO = new UserVO();

        userVO.setKey(user.getId());
        userVO.setNome(user.getName());
        userVO.setEmail(user.getEmail());
        userVO.setSenha(user.getPassword());

        return userVO;
    }

    public static List<UserVO> convertUsersToVO(List<User> users) {

        List<UserVO> usersVO = new ArrayList<>();

        for (User user: users) {
            usersVO.add(UserVOConverter.convertUserToVO(user));
        }

        return usersVO;
    }

}
