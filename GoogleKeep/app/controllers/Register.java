package controllers;
import static play.data.validation.Constraints.*;

import com.avaje.ebean.Ebean;
import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class Register extends Controller {

    static Form<User> userForm = Form.form(User.class);


    public static Result toRegister(){
        return ok(register.render(userForm));
    }
    @play.db.ebean.Transactional
    public static Result registeredNow(){
        Form<User> boundForm =  userForm.bindFromRequest();
        if(boundForm.hasErrors()){
            return badRequest(login.render(boundForm));
        }else{
            User user = boundForm.get();
            user.save();
            return ok(register.render(boundForm));
        }
    }
    public static Result allUsers(){
        List<User> users = Ebean.find(User.class).findList();
        int len = users.size();
        return ok(views.html.users.render(users,len));
    }
}
