package models;
import play.data.validation.Constraints.*;
import play.data.*;
import javax.persistence.*;
@Entity
public class User extends com.avaje.ebean.Model{
    @Required
    @Id
    public String name;
    @Required
    public String pwd;
}
