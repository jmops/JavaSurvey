package com.example.javaSurvey.user;

import com.example.javaSurvey.constant.Const;
import com.example.javaSurvey.exception.UserCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository userRepository;

    public User findOne(Long id){
        var optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User findOneByName(String name){
        return userRepository.findByName(name);
    }
    public User insertOne(User user) throws UserCreationException {
        if(user.getName() != null && user.getPassword() != null){
            if(!user.isUsernameValid())
                throw new UserCreationException("The length of the username needs to be between: "
                        + Const.MINUSERNAMELENGTH + " and " + Const.MAXUSERNAMELENGTH + " characters.\n Valid characters are: a-z & A-Z & 0-9");
            else if(!user.isEmailValid())
                throw new UserCreationException("Invalid email!");
            else if(!user.isPasswordComplexEnough())
                throw new UserCreationException("The length of the password needs to be between: "
                        + Const.MINPASSWORDLENGTH + " and " + Const.MAXPASSWORDLENGTH +
                        " characters. With at least one digit, one upper and lower case letter, and one special letter." +
                        "\nValid characters are: " + Const.VALIDCHARACTERS);
        }else throw new UserCreationException("You need a username and a password!");

        user.hashPassword();
        return userRepository.save(user);
    }
}
