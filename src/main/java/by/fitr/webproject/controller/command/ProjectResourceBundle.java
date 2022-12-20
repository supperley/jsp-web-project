package by.fitr.webproject.controller.command;


import java.util.ResourceBundle;

public class ProjectResourceBundle{

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(ParameterName.PAGES_PATH);

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    private ProjectResourceBundle() {
    }
}
