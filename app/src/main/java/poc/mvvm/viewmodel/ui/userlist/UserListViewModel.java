package poc.mvvm.viewmodel.ui.userlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import poc.mvvm.viewmodel.data.rest.ApiRepository;
import poc.mvvm.viewmodel.data.rest.response.UserListResponse;

public class UserListViewModel extends ViewModel {
    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<UserListResponse> userListResponse = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public UserListViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

    }

    LiveData<UserListResponse> getUserList() {
        return userListResponse;
    }

    LiveData<Throwable> getError() {
        return error;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    void getUserList(int page) {
        fetchUserList(page);
    }

    private void fetchUserList(int page) {
        loading.setValue(true);
        disposable.add(apiRepository.getUserList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<UserListResponse>() {
                    @Override
                    public void onSuccess(UserListResponse value) {
                        error.setValue(null);
                        userListResponse.setValue(value);
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
