/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.Project;
import ds.Task;
import ds.ToDoList;
import ds.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ProjectsController implements Initializable {

    @FXML
    private TableView projectTable;
    @FXML
    private TableView userTable;
    @FXML
    private TableView taskTable;
    @FXML
    private TableView MembersTable;
    @FXML
    private TextField paTitle, peTitle, taTitle, teTitle, pId;

    int selectedProjectId;
    ToDoList tdl = null;

    public void setToDoList(ToDoList t) {
        tdl = t;
        fillTable();
    }

    public void addProject(ActionEvent ae) {
        String title = paTitle.getText();
        tdl.addProject(title);
        int projectid = tdl.getProjectIdByTitle(title);
        tdl.addProjectCreator(projectid);
        fillTable();
        paTitle.setText("");
    }

    public void deleteProject(ActionEvent ab) throws Exception {
        Project project = (Project) projectTable.getSelectionModel().getSelectedItem();
        if (tdl != null) {
            if (tdl.getLoggedIn().getId() == tdl.getProjectCreator(project.getId())) {
                tdl.deleteProject(project.getId());
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("You are not the project manager");
                al.setContentText("Contact your project manager");
                al.showAndWait();
            }
            fillTable();
        }
    }

    public void editProject(ActionEvent aeb) throws Exception {
        Project project = (Project) projectTable.getSelectionModel().getSelectedItem();
        if (tdl != null) {
            String title = peTitle.getText();
            if (tdl.getLoggedIn().getId() == tdl.getProjectCreator(project.getId())) {
                tdl.editProjectInfo(project.getId(), title);
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("You are not the project manager");
                al.setContentText("Contact your project manager");
                al.showAndWait();
            }
            fillTable();
            peTitle.setText("");
        }
    }

    public void addTask(ActionEvent az) {
        Project project = (Project) projectTable.getSelectionModel().getSelectedItem();
        String title = taTitle.getText();
        tdl.addTaskToProject(project.getId(), title);
        taskTable.getItems().clear();
        taskTable.getItems().addAll(tdl.getAllProjectTasks(selectedProjectId));
        taTitle.setText("");
    }

    public void addUser(ActionEvent az) {
        User user = (User) userTable.getSelectionModel().getSelectedItem();
        int projectId = Integer.parseInt(pId.getText());
        if (tdl.getLoggedIn().getId() == tdl.getProjectCreator(projectId)) {
            tdl.addProjectMember(projectId, user.getId());
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("You are not the project manager");
            al.setContentText("Contact your project manager");
            al.showAndWait();
        }
        fillTable();
        pId.setText("");
    }

    public void removeUser(ActionEvent az) {
        User user = (User) userTable.getSelectionModel().getSelectedItem();
        int projectId = Integer.parseInt(pId.getText());
        if (tdl.getLoggedIn().getId() == tdl.getProjectCreator(projectId)) {
            tdl.removeProjectMember(projectId, user.getId());
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("You are not the project manager");
            al.setContentText("Contact your project manager");
            al.showAndWait();
        }
        fillTable();
        pId.setText("");
    }

    public void updateTaskTable(MouseEvent me) {
        if (me.getClickCount() == 2) {
            Project selected = (Project) projectTable.getSelectionModel().getSelectedItem();
            taskTable.getItems().clear();
            selectedProjectId = selected.getId();
            taskTable.getItems().addAll(tdl.getAllProjectTasks(selectedProjectId));
        }
    }

    public void editTask(ActionEvent ab) throws Exception {
        Task task = (Task) taskTable.getSelectionModel().getSelectedItem();
        if (tdl != null) {
            String title = teTitle.getText();
            tdl.editTaskInfo(task.getId(), title);
            taskTable.getItems().clear();
            taskTable.getItems().addAll(tdl.getAllProjectTasks(selectedProjectId));
            teTitle.setText("");
        }
    }

    public void completeTask(ActionEvent az) {
        Task task = (Task) taskTable.getSelectionModel().getSelectedItem();
        tdl.completeTask(task.getId());
        taskTable.getItems().clear();
        taskTable.getItems().addAll(tdl.getAllProjectTasks(selectedProjectId));
    }

    public void deleteTask(ActionEvent az) {
        Task task = (Task) taskTable.getSelectionModel().getSelectedItem();
        tdl.deleteTask(task.getId());
        taskTable.getItems().clear();
        taskTable.getItems().addAll(tdl.getAllProjectTasks(selectedProjectId));
    }

    public void fillTable() {
        if (tdl != null) {
            projectTable.getItems().clear();
            projectTable.getItems().addAll(tdl.getAllUserProjects(tdl.getAllProjectIdsByLoggedInUser()));
            userTable.getItems().clear();
            userTable.getItems().addAll(tdl.getAllUsers());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<String, Project> column1 = new TableColumn<>("Project ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<String, Project> column2 = new TableColumn<>("Project Title");
        column2.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<String, Project> column3 = new TableColumn<>("Manager");
        column3.setCellValueFactory(new PropertyValueFactory<>("manager"));

        projectTable.getColumns().clear();
        projectTable.getColumns().add(column1);
        projectTable.getColumns().add(column2);
        projectTable.getColumns().add(column3);

        TableColumn<String, Task> column4 = new TableColumn<>("Task Title");
        column4.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<String, Task> column5 = new TableColumn<>("Completed");
        column5.setCellValueFactory(new PropertyValueFactory<>("completed"));

        taskTable.getColumns().clear();
        taskTable.getColumns().add(column4);
        taskTable.getColumns().add(column5);

        TableColumn<String, User> column6 = new TableColumn<>("Name");
        column6.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, User> column7 = new TableColumn<>("Surname");
        column7.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<String, User> column8 = new TableColumn<>("Title");
        column8.setCellValueFactory(new PropertyValueFactory<>("title"));

        userTable.getColumns().clear();
        userTable.getColumns().add(column6);
        userTable.getColumns().add(column7);
        userTable.getColumns().add(column8);
    }

}
