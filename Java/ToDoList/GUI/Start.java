/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.ToDoList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ToDoList todo = new ToDoList();
        FXMLLoader load = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = load.load();
        LoginController col = load.getController();
        col.setToDoList(todo);
        Scene scene = new Scene(root);
        primaryStage.setTitle("ToDoList");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
