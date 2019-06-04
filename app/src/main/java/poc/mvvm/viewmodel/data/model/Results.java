package poc.mvvm.viewmodel.data.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Results implements Serializable {

    String bestsellers_date;
    ArrayList<BookList> lists;
    String published_date;

    public String getBestsellers_date() {
        return bestsellers_date;
    }

    public void setBestsellers_date(String bestsellers_date) {
        this.bestsellers_date = bestsellers_date;
    }

    public ArrayList<BookList> getLists() {
        return lists;
    }

    public void setLists(ArrayList<BookList> lists) {
        this.lists = lists;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
}
