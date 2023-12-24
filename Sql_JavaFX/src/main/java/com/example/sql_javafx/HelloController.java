package com.example.sql_javafx;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSgnUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    void initialize() {

        loginSgnUpButton.setOnAction(actionEvent -> {

            loginSgnUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signUp.fxml"));
            try{
                loader.load();
            } catch (IOException ex){
                ex.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

        authSignInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if(!(loginText.equals("") && passwordText.equals(""))) {

                if(loginUser(loginText, passwordText)) {

                    authSignInButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("home.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка!");
                alert.setContentText("Ошибка входа! Проверьте данные!");
                alert.show();
            }
        });

    }

    private boolean loginUser(String loginText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dbHandler.getUser(user);

        try {

            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Успешный вход");
                alert.setContentText("Вы успешно вошли в систему!");
                alert.show();
                return true;
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка!");
            alert.setContentText("Авторизация не удалась! Проверьте ваши данные!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
