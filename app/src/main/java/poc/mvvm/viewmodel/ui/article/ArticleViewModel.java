package poc.mvvm.viewmodel.ui.article;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import poc.mvvm.viewmodel.data.rest.ApiRepository;
import poc.mvvm.viewmodel.data.rest.response.ArticleResponse;
import poc.mvvm.viewmodel.data.rest.response.UserListResponse;

public class ArticleViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<ArticleResponse> articleResponse = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    LiveData<ArticleResponse> getArticles() {
        return articleResponse;
    }

    LiveData<Throwable> getError() {
        return error;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    public ArticleViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

    }

    public void fetchArticles( int year, int month,String apiKey) {
        loading.setValue(true);
        disposable.add(apiRepository.getArticles(year, month, apiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ArticleResponse>() {
                    @Override
                    public void onSuccess(ArticleResponse value) {
                        error.setValue(null);
                        articleResponse.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
