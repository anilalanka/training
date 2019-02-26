package controllers;
import static play.data.validation.Constraints.*;

import com.avaje.ebean.Ebean;
import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class Login extends Controller {

    static Form<User> userForm = Form.form(User.class);

    public static Result login() {
        return ok(login.render(userForm));
    }
    @play.db.ebean.Transactional
    public static Result authenticate(){
        Form<User> boundForm =  userForm.bindFromRequest();
        if(boundForm.hasErrors()){
            return badRequest(login.render(boundForm));
        }else{
            User user = boundForm.get();
            User user1 = Ebean.find(User.class, user.name);
            if(user1 == null){
                return ok("username not registered");
            }
            else if((user1.pwd).equals(user.pwd)){
                return ok(loggedin.render(user1));
            }
            else{
                return ok("Invalid password");
            }

        }
    }@play.db.ebean.Transactional
    public static Result delete(){
        List<User> ulist = Ebean.find(User.class).findList();
        Ebean.deleteAll(ulist);
        return ok("Database cleared!");
    }

}
