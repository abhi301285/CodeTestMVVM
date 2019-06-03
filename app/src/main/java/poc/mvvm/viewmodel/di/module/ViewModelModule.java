package poc.mvvm.viewmodel.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import poc.mvvm.viewmodel.di.util.ViewModelKey;
import poc.mvvm.viewmodel.ui.article.ArticleViewModel;
import poc.mvvm.viewmodel.ui.login.LoginViewModel;
import poc.mvvm.viewmodel.ui.userlist.UserListViewModel;
import poc.mvvm.viewmodel.util.ViewModelFactory;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    abstract ViewModel bindUserListViewModel(UserListViewModel userListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel.class)
    abstract ViewModel bindArticleViewModel(ArticleViewModel articleViewModel);
}
