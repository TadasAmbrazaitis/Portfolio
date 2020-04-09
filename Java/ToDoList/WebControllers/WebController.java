package WebControllers;

import com.google.gson.Gson;
import ds.ToDoList;
import ds.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Tadas Ambrazaitis
 */
@Controller
public class WebController {
    
    ToDoList tdl = new ToDoList();
    
    @RequestMapping (value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String usLogin (@RequestBody String user) throws Exception{
        Gson parser = new Gson();
        User entered = parser.fromJson(user, User.class);
        if (tdl.login(entered.getLogin(), entered.getPassword()) != null){
            entered.setId(tdl.getLoggedIn().getId());
            return parser.toJson(entered);
        }
        return "Incorrect Login / Password";
    }
    
    @RequestMapping (value = "prlist", method = RequestMethod.GET)
    @ResponseBody
    public String prList (){
        Gson parser = new Gson();
        return parser.toJson(tdl.getAllUserProjects(tdl.getAllProjectIdsByLoggedInUser()));
    }
    
    @RequestMapping (value = "tlist", method = RequestMethod.GET)
    @ResponseBody
    public String tList (@RequestParam("id") int projectId){
        Gson parser = new Gson();
        return parser.toJson(tdl.getAllProjectTasks(projectId));
    }
    
    @RequestMapping (value = "complete", method = RequestMethod.GET)
    @ResponseBody
    public String completeTask (@RequestParam("id") int taskId){
        tdl.completeTask(taskId);
        Gson parser = new Gson();
        return parser.toJson(taskId);
    }
    
}
