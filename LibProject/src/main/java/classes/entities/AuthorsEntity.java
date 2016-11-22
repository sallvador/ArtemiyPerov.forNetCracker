package classes.entities;

import java.util.NoSuchElementException;

/**
 * Created by demon on 15.11.2016.
 */
public class AuthorsEntity {
    private long authorid;
    private String authorname;
    static long lastID = 0;
    protected AuthorsEntity(){

    }

    public AuthorsEntity(String name){
        if (name == null) throw new NoSuchElementException();
        name = name.trim().replaceAll("[\\s]{2,}", " ");
        if (name.length() > 30){
            System.out.println("Name must be not longer than 30 characters");
            return;
        }
        this.authorname = name;
        this.authorid = lastID + 1;
        lastID = this.authorid;
        if (lastID == Long.MAX_VALUE){
            //will be supported later
        }

    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorsEntity that = (AuthorsEntity) o;

        if (authorid != that.authorid) return false;
        if (authorname != null ? !authorname.equals(that.authorname) : that.authorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (authorid ^ (authorid >>> 32));
        result = 31 * result + (authorname != null ? authorname.hashCode() : 0);
        return result;
    }
}
