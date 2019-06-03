package poc.mvvm.viewmodel.ui.article;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import poc.mvvm.viewmodel.R;
import poc.mvvm.viewmodel.base.BaseActivity;
import poc.mvvm.viewmodel.util.Constants;
import poc.mvvm.viewmodel.util.ViewModelFactory;

public class ArticleActivity extends BaseActivity {

    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;

    private ArticleViewModel viewModel;

    String TAG = ArticleActivity.class.getSimpleName();

    @Override
    protected int layoutRes() {
        return R.layout.activity_article;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);

       viewModel.fetchArticles(2015,11, Constants.API_KEY);
       observableViewModel();
    }


    private void observableViewModel() {
        //to update the UI
        viewModel.getArticles().observe(this, articleResponse -> {
            if (articleResponse != null) {
                Toast.makeText(this, "CopyRight === " + articleResponse.getCopyright(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this, MainActivity.class));
            }
        });

        viewModel.getError().observe(this, throwable -> {
            if (throwable != null) {
                Log.e(TAG, "Error LIst = " + throwable.getLocalizedMessage());
            } else {
                Log.e(TAG, "Error Throwable null ");
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }
}
