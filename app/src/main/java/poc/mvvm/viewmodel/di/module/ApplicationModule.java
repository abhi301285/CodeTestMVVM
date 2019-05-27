package poc.mvvm.viewmodel.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import poc.mvvm.viewmodel.data.rest.APIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    private static final String BASE_URL = "https://reqres.in/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static APIService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
