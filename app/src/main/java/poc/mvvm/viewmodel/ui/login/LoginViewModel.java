package poc.mvvm.viewmodel.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import poc.mvvm.viewmodel.data.rest.ApiRepository;
import poc.mvvm.viewmodel.data.rest.request.LoginRequest;
import poc.mvvm.viewmodel.data.rest.response.LoginResponse;

public class LoginViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public LoginViewModel(ApiRepository apiRepository){
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

    }

    LiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }
    LiveData<Boolean> getError() {
        return loginError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }


    public void getLogin(LoginRequest request){
        fetchRepos(request);
    }

    private void fetchRepos(LoginRequest request) {
        loading.setValue(true);
        disposable.add(apiRepository.getLogin(request).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse value) {
                        loginError.setValue(false);
                        loginResponse.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginError.setValue(true);
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
