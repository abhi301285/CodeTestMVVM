package poc.mvvm.viewmodel.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import poc.mvvm.viewmodel.data.rest.request.LoginRequest;
import poc.mvvm.viewmodel.data.rest.response.ArticleResponse;
import poc.mvvm.viewmodel.data.rest.response.LoginResponse;
import poc.mvvm.viewmodel.data.rest.response.UserListResponse;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiRepository {

    private final APIService APIService;

    @Inject
    public ApiRepository(APIService APIService) {
        this.APIService = APIService;
    }

    public Single<LoginResponse> getLogin(@Body LoginRequest request) {
        return APIService.getLogin(request);
    }

    public Single<UserListResponse> getUserList(int page) {
        return APIService.getUserList(page);
    }

    public Single<ArticleResponse> getArticles(int year,int month, String apiKey){
        return APIService.getArticles(year, month,apiKey);
    }

}
