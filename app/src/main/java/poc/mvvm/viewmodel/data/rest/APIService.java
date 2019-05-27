package poc.mvvm.viewmodel.data.rest;

import io.reactivex.Single;
import poc.mvvm.viewmodel.data.rest.request.LoginRequest;
import poc.mvvm.viewmodel.data.rest.response.LoginResponse;
import poc.mvvm.viewmodel.data.rest.response.UserListResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("/api/login")
    Single<LoginResponse> getLogin(@Body LoginRequest request);

    @GET("/api/users?")
    Single<UserListResponse> getUserList(@Query("page") int page);
}
