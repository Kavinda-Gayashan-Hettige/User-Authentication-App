package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    @FXML
    private Button logout;

    @FXML
    private Label FirstName;

    private String username;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FirstName.setText("Welcome Guest");
    }


    public  void setUserName(String username) {
        this.username = username;

        if (username != null && !username.isEmpty()) {

            FirstName.setText("Welcome " + username);
        }
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))
        ));
        stage.show();

        ((Stage) logout.getScene().getWindow()).close();
    }
}
