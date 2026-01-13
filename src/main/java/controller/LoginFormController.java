package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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


    @FXML
    void btnLoginOnAction() {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (!isValidGmail(username)) {
            System.out.println("Username must end with @gmail.com");
            return;
        }

        if (!isStrongPassword(password)) {
            System.out.println("Password requirements not met");
            return;
        }

        try {
            boolean success = userService.login(username, password);

            if (success) {
                System.out.println("Login success");


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dash_board_form.fxml"));
                Parent root = loader.load();


                DashBoardFormController controller = loader.getController();
                controller.setUserName(username);


                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.close();


                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

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
