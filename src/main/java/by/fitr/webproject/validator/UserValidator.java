package by.fitr.webproject.validator;

import by.fitr.webproject.entity.user.User;

public interface UserValidator {

    boolean checkLogin(String login);

    boolean checkPassword(String password);

    boolean checkFirstName(String firstName);

    boolean checkLastName(String lastName);

    boolean checkEmail(String email);

    boolean checkUser(User user);

}
