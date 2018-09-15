package comnewsapp.example.moham.newsapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    private String status;
    private int otalResults;
    private List<News> articles;

    @Override
    public String toString() {
        return "NewsResponse{" +
                "status='" + status + '\'' +
                ", otalResults=" + otalResults +
                ", articles=" + articles +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOtalResults() {
        return otalResults;
    }

    public void setOtalResults(int otalResults) {
        this.otalResults = otalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
