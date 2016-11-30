
package classes.entities;

import classes.util.Assistant;
import classes.util.HiberSF;
import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by demon on 15.11.2016.
 */
public class UsersEntity {
    private static long lastID ;
    static {
        try{
            SessionFactory sf = HiberSF.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            lastID = new Long(session.createQuery("select max(id) from UsersEntity").uniqueResult().toString());
            session.getTransaction().commit();
            session.close();
            sf.close();
        }catch (Exception e) {
            System.err.println("Failed to get users id" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private long userid;
    private String firstname;
    private String lastname;
    private Date regdate;
    private String email;
    private String phone;
    private String category = "U";
    private String password;



    protected UsersEntity(){

    }

    public UsersEntity(String firstname, String lastname, String email, String password){
        if ((firstname == null) || (lastname == null) || (email == null))  throw new NoSuchElementException();
        firstname = Assistant.deleteNeedlessSpaces(firstname);
        lastname = Assistant.deleteNeedlessSpaces(lastname);
        email = email.trim();
        if ((firstname.length() > Assistant.maxTextLength)||(lastname.length() > Assistant.maxTextLength)){
            System.out.println("Name and Surname must be not longer than 30 characters");
            return;
        }
        this.firstname = firstname;
        this.lastname = lastname;
        //this.userid = lastID + 1;
        this.email = email;
        this.password = password;
        lastID = this.getUserid();
        if (lastID == Long.MAX_VALUE){
            //will be supported later
        }
        this.regdate = new Date(Calendar.getInstance().getTime().getTime());//Time(Calendar.getInstance().getTime().getTime());
        Matcher matcher = Assistant.emailPattern.matcher(email);
        if (matcher.matches() == false){
            System.out.println("Wrong email format");
            return;
        }
    }

    public UsersEntity(String firstname, String lastname, String email, String phone, String password){
        if ((firstname == null) || (lastname == null) || (email == null) || (phone == null))  throw new NoSuchElementException();
        firstname = Assistant.deleteNeedlessSpaces(firstname);
        lastname = Assistant.deleteNeedlessSpaces(lastname);
        email = email.trim();
        phone = phone.trim();
        if ((firstname.length() > Assistant.maxTextLength)||(lastname.length() > Assistant.maxTextLength)){
            System.out.println("Name and Surname must be not longer than 30 characters");
            return;
        }
        this.firstname = firstname;
        this.lastname = lastname;
        //this.userid = lastID + 1;
        lastID = this.getUserid();
        if (lastID == Long.MAX_VALUE){
            //will be supported later
        }
        this.regdate = new Date(Calendar.getInstance().getTime().getTime());
        Matcher matcher = Assistant.emailPattern.matcher(email);
        if (matcher.matches() == false){
            System.out.println("Wrong email format");
            return;
        }
        this.email = email;
        matcher = Assistant.phonePattern.matcher(phone);
        if (matcher.matches() == false){
            System.out.println("Wrong phone format");
            return;
        }
        this.phone = phone;
        this.password = password;
    }

    public static long getLastID(){ return lastID; }

    private static void setLastID(long lastID){ UsersEntity.lastID = lastID; };

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userid != that.userid) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (regdate != null ? !regdate.equals(that.regdate) : that.regdate != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (regdate != null ? regdate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
