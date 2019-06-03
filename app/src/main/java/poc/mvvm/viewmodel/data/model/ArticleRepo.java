package poc.mvvm.viewmodel.data.model;

import java.util.ArrayList;

public class ArticleRepo {
    ArrayList<Article> docs;

    public ArrayList<Article> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Article> docs) {
        this.docs = docs;
    }
}
