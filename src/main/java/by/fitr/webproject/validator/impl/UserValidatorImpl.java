package by.fitr.webproject.validator.impl;

import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.validator.UserValidator;

import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final String INCORRECT_VALUE_PARAMETER = " - incorrect value";
    private static final String LOGIN_REGEX = "[\\p{Alpha}][\\p{Alpha}\\d]{3,29}";
    private static final String PASSWORD_REGEX = "[\\p{Alpha}][\\p{Alpha}\\d]{7,29}";
    private static final String FIRST_NAME_REGEX = "[\\p{Upper}][\\p{Lower}]{1,20}";
    private static final String LAST_NAME_REGEX = "[\\p{Upper}][\\p{Lower}]{1,20}";
    private static final String EMAIL_REGEX = "(([\\p{Alpha}\\d._]+){5,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2,3})";

    private static final UserValidator instance = new UserValidatorImpl();
    public static UserValidator getInstance(){
        return instance;
    }

    private UserValidatorImpl() {
    }

    @Override
    public boolean checkLogin(String login) {
        return login != null && Pattern.matches(LOGIN_REGEX, login);
    }

    @Override
    public boolean checkPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }

    @Override
    public boolean checkFirstName(String firstName) {
        return firstName != null && Pattern.matches(FIRST_NAME_REGEX, firstName);
    }

    @Override
    public boolean checkLastName(String lastName) {
        return lastName != null && Pattern.matches(LAST_NAME_REGEX, lastName);
    }

    @Override
    public boolean checkEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    @Override
    public boolean checkUser(User user){
        boolean isValid = true;
        if (!checkLogin(user.getLogin())){
            user.setLogin(user.getLogin() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if(!checkPassword(user.getPassword())){
            user.setPassword(user.getPassword() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if(!checkEmail(user.getEmail())){
            user.setEmail(user.getEmail() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if(!checkFirstName(user.getFirstName())){
            user.setFirstName(user.getFirstName() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if(!checkLastName(user.getLastName())){
            user.setLastName(user.getLastName() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }
}
