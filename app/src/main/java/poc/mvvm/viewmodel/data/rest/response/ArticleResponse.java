package poc.mvvm.viewmodel.data.rest.response;

import java.io.Serializable;

import poc.mvvm.viewmodel.data.model.ArticleRepo;

public class ArticleResponse implements Serializable {
    String copyright;
    ArticleRepo response;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArticleRepo getResponse() {
        return response;
    }

    public void setResponse(ArticleRepo response) {
        this.response = response;
    }
}
