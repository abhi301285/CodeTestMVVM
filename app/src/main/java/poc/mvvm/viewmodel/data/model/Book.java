package poc.mvvm.viewmodel.data.model;

import java.io.Serializable;

public class Book implements Serializable {
    String age_group;

    String author;
    String contributor;
    String contributor_note;
    String created_date;
    String description;
    int price;
    String primary_isbn10;
    String primary_isbn13;
    String publisher;
    int rank;
    String title;
    String updated_date;

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getContributor_note() {
        return contributor_note;
    }

    public void setContributor_note(String contributor_note) {
        this.contributor_note = contributor_note;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrimary_isbn10() {
        return primary_isbn10;
    }

    public void setPrimary_isbn10(String primary_isbn10) {
        this.primary_isbn10 = primary_isbn10;
    }

    public String getPrimary_isbn13() {
        return primary_isbn13;
    }

    public void setPrimary_isbn13(String primary_isbn13) {
        this.primary_isbn13 = primary_isbn13;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}
