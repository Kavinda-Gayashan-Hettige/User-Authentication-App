package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class RegisterFormController {

    public AnchorPane mainPane;
    public Button btnlogin;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    private final UserService userService = new UserService();

    @FXML
    void btnRegisterOnAction() throws Exception {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();


        if (!isValidGmail(username)) {
            showAlert(Alert.AlertType.ERROR,
                    "Invalid Email",
                    "Username must end with @gmail.com");
            return;
        }


        if (!isStrongPassword(password)) {
            showAlert(Alert.AlertType.ERROR,
                    "Weak Password",
                    "Password must contain:\n" +
                            "• Minimum 8 characters\n" +
                            "• Uppercase letter\n" +
                            "• Lowercase letter\n" +
                            "• Number\n" +
                            "• Special character");
            return;
        }


        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR,
                    "Password Mismatch",
                    "Passwords do not match");
            return;
        }


        try {
            boolean saved = userService.register(username, password);

            if (saved) {
                showAlert(Alert.AlertType.INFORMATION,
                        "Success",
                        "Registration successful 🎉");
                clearFields();




            } else {
                showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "User already exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,
                    "System Error",
                    "Something went wrong");
        }
    }


    private boolean isValidGmail(String email) {
        return email != null &&
                email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$");
    }


    private boolean isStrongPassword(String password) {
        return password != null &&
                password.matches(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
                );
    }


    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public void btnloginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();
    }
}
