package poc.mvvm.viewmodel.ui.book;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import poc.mvvm.viewmodel.data.rest.ApiRepository;
import poc.mvvm.viewmodel.data.rest.response.ArticleResponse;
import poc.mvvm.viewmodel.data.rest.response.BookListOverviewResponse;

public class BookViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<BookListOverviewResponse> bookListOverviewResponse = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    LiveData<BookListOverviewResponse> getListOverviewResponse() {
        return bookListOverviewResponse;
    }

    LiveData<Throwable> getError() {
        return error;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    public BookViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

    }

    public void fetchBookListOverview( String apiKey) {
        loading.setValue(true);
        disposable.add(apiRepository.getBookListOverview( apiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<BookListOverviewResponse>() {
                    @Override
                    public void onComplete() {
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e);
                        loading.setValue(false);
                    }

                    @Override
                    public void onNext(BookListOverviewResponse value) {
                        error.setValue(null);
                        bookListOverviewResponse.setValue(value);

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
