package ds;

import java.sql.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable {

    private String DB_URL = ("jdbc:mysql://localhost/java");
    private String USER = "root";
    private String PASS = "";
    private Connection con = null;

    private User loggedIn = null;

    public void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromDB() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User login(String login, String password) throws Exception {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, login, pass FROM user WHERE login=? AND pass=?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                User u = new User(id, login, password);
                loggedIn = u;
                return u;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
        return null;
    }

    public User registerUser(String login, String password, String name, String surname, String title) {
        User newUser = new User(login, password, name, surname, title);
        if (title == null) {
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO user"
                        + "(id, login, pass, vardas, pavarde, pavadinimas) VALUES"
                        + "(NULL, ?, ?, ?, ?, ?)");
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setString(4, surname);
                ps.setString(5, "");
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
            disconnectFromDB();
            return newUser;
        }
        if (title != null) {
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO user"
                        + "(id, login, pass, vardas, pavarde, pavadinimas) VALUES"
                        + "(NULL, ?, ?, ?, ?, ?)");
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, "");
                ps.setString(4, "");
                ps.setString(5, title);
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
            disconnectFromDB();
            return newUser;
        }

        return null;
    }

    public void deleteUser(int id) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM user "
                    + "WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> forReturn = new ArrayList();

        if (loggedIn != null) {

            connectToDB();
            try {
                Statement sta = con.createStatement();
                ResultSet rs = sta.executeQuery("Select * from user");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String login = rs.getString(2);
                    String pass = rs.getString(3);
                    String name = rs.getString(4);
                    String surname = rs.getString(5);
                    String title = rs.getString(6);
                    User user = new User(login, pass, name, surname, title);
                    forReturn.add(user);
                    user.setId(id);
                }
                rs.close();
                sta.close();
            } catch (SQLException ex) {
            }
            disconnectFromDB();

            return forReturn;
        }
        return forReturn;
    }

    public Project addProject(String title) {
        if (loggedIn != null && title.length() > 3) {
            Project newProject = new Project(title);
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO project"
                        + "(id, title) VALUES"
                        + "(NULL, ?)");
                ps.setString(1, title);
                ps.executeUpdate();

            } catch (SQLException ex) {
            }
            disconnectFromDB();

        }
        return null;
    }

    public int getProjectIdByTitle(String title) {
        connectToDB();
        try {
            PreparedStatement pa = con.prepareStatement("SELECT * FROM project WHERE title=?");
            pa.setString(1, title);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                int projectid = rs.getInt(1);
                return projectid;
            }
        } catch (SQLException ex) {
        }
        disconnectFromDB();
        return 0;
    }

    public void addProjectMember(int projectId, int userId) {
        if (loggedIn != null) {
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO user_project"
                        + "(userid, projectid, creator) VALUES"
                        + "(?, ?, ?)");
                ps.setInt(1, userId);
                ps.setInt(2, projectId);
                ps.setInt(3, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
            disconnectFromDB();
        }
    }

    public void removeProjectMember(int projectId, int userId) {
        if (loggedIn != null) {
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM user_project WHERE userid=? AND projectid=?");
                ps.setInt(1, userId);
                ps.setInt(2, projectId);
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
            disconnectFromDB();
        }
    }

    public void addProjectCreator(int projectId) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_project"
                    + "(userid, projectid, creator) VALUES"
                    + "(?, ?, ?)");
            ps.setInt(1, loggedIn.getId());
            ps.setInt(2, projectId);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public void deleteProject(int id) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM project "
                    + "WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public Task addTaskToProject(int projectId, String title) {
        if (loggedIn != null && title.length() > 3) {
            connectToDB();
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO task"
                        + "(projectid, taskid, title) VALUES"
                        + "(?, NULL, ?)");
                ps.setInt(1, projectId);
                ps.setString(2, title);
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
            disconnectFromDB();

        }
        return null;
    }

    public int[] getAllProjectIdsByLoggedInUser() {
        int[] prid = new int[100];
        int i = 0;
        if (loggedIn != null) {
            connectToDB();
            try {
                PreparedStatement pa = con.prepareStatement("SELECT projectid FROM user_project WHERE userid=?");
                pa.setInt(1, loggedIn.getId());
                ResultSet rs = pa.executeQuery();
                while (rs.next()) {
                    prid[i] = rs.getInt(1);
                    i++;
                }
            } catch (SQLException ex) {
            }
            disconnectFromDB();
        }
        return prid;
    }

    public ArrayList<Project> getAllUserProjects(int prid[]) {
        ArrayList<Project> forReturn = new ArrayList();
        if (loggedIn != null) {

            connectToDB();
            try {
                PreparedStatement pa = con.prepareStatement("SELECT * FROM project WHERE id=?");
                PreparedStatement pb = con.prepareStatement("SELECT creator FROM user_project WHERE projectid=? AND userid=?");
                for (int i = 0; i < prid.length; i++) {
                    pa.setInt(1, prid[i]);
                    pb.setInt(1, prid[i]);
                    pb.setInt(2, loggedIn.getId());
                    ResultSet rs = pa.executeQuery();
                    ResultSet rb = pb.executeQuery();
                    while (rs.next() && rb.next()) {
                        int id = rs.getInt(1);
                        String title = rs.getString(2);
                        int manager = rb.getInt(1);
                        Project project = new Project(title);
                        forReturn.add(project);
                        project.setId(id);
                        if (manager == 1) {
                            project.setManager("Yes");
                        } else {
                            project.setManager("No");
                        }

                    }
                }

            } catch (SQLException ex) {
            }
            disconnectFromDB();
            return forReturn;
        }
        return new ArrayList();
    }

    public ArrayList<Task> getAllProjectTasks(int projectid) {
        ArrayList<Task> forReturn = new ArrayList();
        if (loggedIn != null) {
            connectToDB();
            try {
                PreparedStatement pa = con.prepareStatement("SELECT * FROM task WHERE projectid=?");
                pa.setInt(1, projectid);
                ResultSet rs = pa.executeQuery();
                while (rs.next()) {
                    int prid = rs.getInt(1);
                    int taskid = rs.getInt(2);
                    String title = rs.getString(3);
                    int completed = rs.getInt(4);
                    Task task = new Task(title);
                    forReturn.add(task);
                    task.setId(taskid);
                    if (completed == 1) {
                        task.setCompleted("Yes");
                    } else {
                        task.setCompleted("No");
                    }
                }

            } catch (SQLException ex) {
            }
            disconnectFromDB();
            return forReturn;
        }

        return new ArrayList();
    }

    public void editProjectInfo(int id, String title) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE project "
                    + "SET title=?"
                    + "WHERE id=?");
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public void editTaskInfo(int id, String title) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE task "
                    + "SET title=?"
                    + "WHERE taskid=?");
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public void completeTask(int id) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE task SET completed=? WHERE taskid=?");
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public void deleteTask(int id) {
        connectToDB();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM task WHERE taskid=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        disconnectFromDB();
    }

    public int getProjectCreator(int projectid) {
        if (loggedIn != null) {
            connectToDB();
            try {
                PreparedStatement pa = con.prepareStatement("SELECT creator, userid FROM user_project WHERE projectid=?");
                pa.setInt(1, projectid);
                ResultSet rs = pa.executeQuery();
                while (rs.next()) {
                    int creator = rs.getInt(1);
                    int userid = rs.getInt(2);
                    if (creator == 1) {
                        return userid;
                    }
                }
            } catch (SQLException ex) {
            }
            disconnectFromDB();
        }
        return 0;
    }

    public User getLoggedIn() {
        return loggedIn;
    }
}
