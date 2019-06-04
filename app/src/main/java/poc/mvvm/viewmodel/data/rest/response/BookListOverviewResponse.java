package poc.mvvm.viewmodel.data.rest.response;

import java.io.Serializable;

import poc.mvvm.viewmodel.data.model.Results;

public class BookListOverviewResponse implements Serializable {

    String copyright;
    int num_results;
    Results results;
    String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
