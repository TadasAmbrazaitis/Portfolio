/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.ToDoList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField lLogin, lPass, rLogin, rPass, rName, rSurname, rTitle;
    ToDoList tdl = null;

    public void close(ActionEvent as) {
        Platform.exit();
    }

    public void setToDoList(ToDoList t) {
        tdl = t;
    }

    public void loginUser(ActionEvent ae) throws Exception {
        if (tdl != null) {
            String login = lLogin.getText();
            String pass = lPass.getText();
            if (tdl.login(login, pass) != null) {
                openProjectManager();
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Incorrect Login and/or Password");
                al.setContentText("Click OK and try again");
                al.showAndWait();
            }
        }
        lLogin.setText("");
        lPass.setText("");

    }

    public void registerUser(ActionEvent ae) throws Exception {
        if (tdl != null) {
            String login = rLogin.getText();
            String pass = rPass.getText();
            String name = rName.getText();
            String surname = rSurname.getText();
            String title = rTitle.getText();
            if (login.trim().length() <= 3 || pass.trim().length() <= 3) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Login and/or Password too short");
                al.setContentText("Login and Password must have at least 4 symbols");
                al.showAndWait();
            } else {
                if (title.trim().length() > 0 && name.trim().length() == 0
                        && surname.trim().length() == 0) {
                    tdl.registerUser(login, pass, null, null, title);
                } else if (title.trim().length() == 0 && name.trim().length() > 0
                        && surname.trim().length() > 0) {
                    tdl.registerUser(login, pass, name, surname, null);
                }
                rLogin.setText("");
                rPass.setText("");
                rName.setText("");
                rSurname.setText("");
                rTitle.setText("");
            }
        }
    }

    public void openProjectManager() throws Exception {
        FXMLLoader load = new FXMLLoader(getClass().getResource("Projects.fxml"));
        Parent root = load.load();

        ProjectsController col = load.getController();
        col.setToDoList(tdl);

        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setTitle("Projects");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
