package com.example.sql_javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button signUpBtn;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;

    @FXML
    void initialize() {

        signUpBtn.setOnAction(actionEvent -> {

            signUpNewUser();

        });
    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = signUpName.getText();
        String lastname = signUpLastName.getText();
        String username = login_field.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String gender = (signUpCheckBoxMale.isSelected()) ? "Мужсой" : "Женский";

        if(signUpCheckBoxMale.isSelected() && signUpCheckBoxFemale.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Выберите только один из чекбоксов!");
            alert.show();
            return;
        }

        User user = new User(firstname, lastname, username, password, location, gender);



        dbHandler.signUpUser(user);
    }

}
