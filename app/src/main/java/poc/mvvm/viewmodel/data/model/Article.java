package poc.mvvm.viewmodel.data.model;

import java.util.ArrayList;

public class Article {

    String _id;

    Byline byline;
    String document_type;
    Headline headline;
    ArrayList<KeyWord> keywords;
    ArrayList<MultiMedia> multimedia;
    String news_desk;
    int print_page;
    String pub_date;
    int score;
    String snippet;
    String source;
    String type_of_material;
    String uri;
    String web_url;
    int word_count;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public ArrayList<KeyWord> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<KeyWord> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<MultiMedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ArrayList<MultiMedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }

    public int getPrint_page() {
        return print_page;
    }

    public void setPrint_page(int print_page) {
        this.print_page = print_page;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public int getWord_count() {
        return word_count;
    }

    public void setWord_count(int word_count) {
        this.word_count = word_count;
    }
}
