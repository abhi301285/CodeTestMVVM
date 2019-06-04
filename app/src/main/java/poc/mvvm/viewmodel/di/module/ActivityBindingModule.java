package poc.mvvm.viewmodel.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import poc.mvvm.viewmodel.data.model.Article;
import poc.mvvm.viewmodel.ui.article.ArticleActivity;
import poc.mvvm.viewmodel.ui.book.BookActivity;
import poc.mvvm.viewmodel.ui.login.LoginActivity;
import poc.mvvm.viewmodel.ui.userlist.UserListActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract UserListActivity bindUserListActivity();

    @ContributesAndroidInjector
    abstract ArticleActivity bindArticleActivity();

    @ContributesAndroidInjector
    abstract BookActivity bindBookActivity();

}
