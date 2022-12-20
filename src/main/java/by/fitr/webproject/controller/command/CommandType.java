package by.fitr.webproject.controller.command;

import by.fitr.webproject.controller.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DELETE_USER(new DeleteUserCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    DEFAULT(new DefaultCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command of(String strCommand){
            CommandType current;

        if (strCommand == null) {
            current = CommandType.DEFAULT;
            return current.command;
        }
            try {
                current = CommandType.valueOf(strCommand.toUpperCase());
                return current.command;
            }catch (IllegalArgumentException e){
                current = CommandType.DEFAULT;
               return current.command;
            }

    }

    public Command getCommand() {
        return command;
    }
}
