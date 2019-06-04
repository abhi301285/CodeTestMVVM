package poc.mvvm.viewmodel.data.rest;

import io.reactivex.Observable;
import io.reactivex.Single;
import poc.mvvm.viewmodel.data.rest.request.LoginRequest;
import poc.mvvm.viewmodel.data.rest.response.ArticleResponse;
import poc.mvvm.viewmodel.data.rest.response.BookListOverviewResponse;
import poc.mvvm.viewmodel.data.rest.response.LoginResponse;
import poc.mvvm.viewmodel.data.rest.response.UserListResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST("/api/login")
    Single<LoginResponse> getLogin(@Body LoginRequest request);

    @GET("/api/users?")
    Single<UserListResponse> getUserList(@Query("page") int page);

    @GET("archive/v1/{year}/{month}.json?")
    Observable<ArticleResponse> getArticles(@Path("year") int year,
                                        @Path("month") int month,
                                        @Query("api-key") String apiKey);

    @GET("books/v3/lists/overview.json?")
    Observable<BookListOverviewResponse> getBookListOverview(@Query("api-key") String apiKey);
}
