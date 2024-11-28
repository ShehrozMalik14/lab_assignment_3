package com.example.lab_assignment_3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;

public class LoginAssignment extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Assignment");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        Image image = new Image("file:/C:/Users/Dell/Desktop/113.2.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: white;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: white;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");
        Label notificationLabel = new Label();
        notificationLabel.setStyle("-fx-text-fill: red;");

        VBox vbox = new VBox(10, imageView, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, exitButton, notificationLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: black; -fx-padding: 20px;");

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (Readmeth(username, password)) {
                Stage welcomeStage = new Stage();
                welcomeStage.setTitle("Welcome");
                welcomeStage.setWidth(300);
                welcomeStage.setHeight(200);

                Label welcomeLabel = new Label("Welcome, " + username + "!");
                welcomeLabel.setStyle("-fx-text-fill: white;");
                StackPane welcomeRoot = new StackPane(welcomeLabel);
                welcomeRoot.setStyle("-fx-background-color: gray;");
                Scene welcomeScene = new Scene(welcomeRoot);
                welcomeStage.setScene(welcomeScene);
                welcomeStage.show();
            } else {
                notificationLabel.setText("Not registered");
            }
        });

        exitButton.setOnAction(event -> primaryStage.close());

        primaryStage.show();
    }

    private static boolean Readmeth(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("lab_assignment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                int usernameIndex = line.indexOf("username:");
                int passwordIndex = line.indexOf("password:");
                if (usernameIndex != -1 && passwordIndex != -1) {
                    String fileUsername = line.substring(usernameIndex + 9, passwordIndex).trim();
                    String filePassword = line.substring(passwordIndex + 9).trim();

                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
