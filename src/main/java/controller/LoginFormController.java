package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.UserService;


import java.io.IOException;

public class LoginFormController {

    public Button btnSignUp;
    @FXML
    private Button btnLogin;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;


    private final UserService userService = new UserService();

//    @FXML
//    void btnLoginOnAction(ActionEvent event) throws IOException {
//
//        if (txtUserName.getText().equals("kavinda") && txtPassword.getText().equals("1234")) {
//            Stage stage = new Stage();
//            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_board_form.fxml"))));
//            stage.show();
//
//
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Invalid Username or Password. Try again!!").show();
//
//        }
//    }
@FXML

void btnLoginOnAction() {

    String username = txtUserName.getText();
    String password = txtPassword.getText();


    if (!isValidGmail(username)) {
        System.out.println("Username must end with @gmail.com");
        return;
    }


    if (!isStrongPassword(password)) {
        System.out.println(
                "Password must contain:\n" +
                        "- Min 8 characters\n" +
                        "- Uppercase & Lowercase letters\n" +
                        "- Number\n" +
                        "- Special character"
        );
        return;
    }

    try {

        boolean success = userService.login(username, password);

        if (success) {
            System.out.println("Login success");
            loginToDashBoard();
        } else {
            System.out.println("Invalid username or password");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private boolean isValidGmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$");
    }

    private boolean isStrongPassword(String password) {
        return password != null &&
                password.matches(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
                );
    }


    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
    Stage stage = new Stage();
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
    stage.show();
    }

    public void loginToDashBoard() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_board_form.fxml"))));
        stage.show();
    }
}
