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
    try {
        boolean success = userService.login(
                txtUserName.getText(),
                txtPassword.getText()
        );

        if (success) {
            System.out.println("Login success");
        } else {
            System.out.println("Invalid credentials");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}



    public void btnSignUpOnAction(ActionEvent actionEvent) {
    }
}
