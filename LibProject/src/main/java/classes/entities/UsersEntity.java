package classes.entities;

import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by demon on 15.11.2016.
 */
public class UsersEntity {
    private long userid;
    private String firstname;
    private String lastname;
    private Time regdate;
    private String email;
    private String phone;
    private String category = "User";

    static long lastID = 0;

    protected UsersEntity(){

    }

    public UsersEntity(String firstname, String lastname, String email){
        firstname = firstname.trim().replaceAll("[\\s]{2,}", " ");
        lastname = lastname.trim().replaceAll("[\\s]{2,}", " ");
        email = email.trim();
        if ((firstname.length() > 30)||(lastname.length() > 30)){
            System.out.println("Name and Surname must be not longer than 30 characters");
            return;
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = lastID + 1;
        lastID = this.getUserid();
        if (lastID == Long.MAX_VALUE){
            //will be supported later
        }
        this.regdate = new Time(java.util.Calendar.getInstance().getTime().getTime());
        Pattern pattern = Pattern.compile("^([A-Za-z0-9]{1}[A-Za-z0-9-_.]{0,20}[A-Za-z0-9]{1})@([A-Za-z0-9][a-zA-Z0-9-]*[A-Za-z0-9].)+(ru|com|net|org)$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == false){
            System.out.println("Wrong email format");
            return;
        }
    }

    public UsersEntity(String firstname, String lastname, String email, String phone){
        firstname = firstname.trim().replaceAll("[\\s]{2,}", " ");
        lastname = lastname.trim().replaceAll("[\\s]{2,}", " ");
        email = email.trim();
        phone = phone.trim();
        if ((firstname.length() > 30)||(lastname.length() > 30)){
            System.out.println("Name and Surname must be not longer than 30 characters");
            return;
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = lastID + 1;
        lastID = this.getUserid();
        if (lastID == Long.MAX_VALUE){
            //will be supported later
        }
        this.regdate = new Time(java.util.Calendar.getInstance().getTime().getTime());
        Pattern pattern = Pattern.compile("^([A-Za-z0-9]{1}[A-Za-z0-9-_.]{0,20}[A-Za-z0-9]{1})@([A-Za-z0-9][a-zA-Z0-9-]*[A-Za-z0-9].)+(ru|com|net|org)$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == false){
            System.out.println("Wrong email format");
            return;
        }
        pattern = Pattern.compile("^\\+[1-9][0-9]{10}$");
        matcher = pattern.matcher(phone);
        if (matcher.matches() == false){
            System.out.println("Wrong phone format");
            return;
        }
        this.phone = phone;
    }

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

    public Time getRegdate() {
        return regdate;
    }

    public void setRegdate(Time regdate) {
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
