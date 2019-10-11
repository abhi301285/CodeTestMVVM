package poc.mvvm.viewmodel.ui.book;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import poc.mvvm.viewmodel.R;
import poc.mvvm.viewmodel.base.BaseActivity;
import poc.mvvm.viewmodel.data.model.BookList;
import poc.mvvm.viewmodel.util.Constants;
import poc.mvvm.viewmodel.util.ViewModelFactory;

public class BookActivity extends BaseActivity implements BookAdapter.BookSelectedListener {

    @BindView(R.id.loading_view)
    View loadingView;

    @BindView(R.id.bookRecyclerView)
    RecyclerView recyclerView;

    @Inject
    ViewModelFactory viewModelFactory;

    private BookViewModel viewModel;

    String TAG = BookActivity.class.getSimpleName();

    @Override
    protected int layoutRes() {
        return R.layout.activity_book;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookViewModel.class);

        recyclerView.setAdapter(new BookAdapter(this, viewModel, this, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.fetchBookListOverview(Constants.API_KEY);
        observableViewModel();
    }

    private void observableViewModel() {
        //to update the UI
        viewModel.getListOverviewResponse().observe(this, bookListOverviewResponse -> {
            if (bookListOverviewResponse != null) {
                Toast.makeText(this, "CopyRight === " + bookListOverviewResponse.getCopyright(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Book Display Name === " + bookListOverviewResponse.getResults().getLists().get(0).getDisplay_name(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getError().observe(this, throwable -> {
            if (throwable != null) {
                Log.e(TAG, "Error LIst = " + throwable.getLocalizedMessage());
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                Log.e(TAG, "Loading  = ");
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onBookSelected(BookList bookList) {
        Toast.makeText(this, "Book List Name = " + bookList.getList_name(), Toast.LENGTH_SHORT).show();
    }
}
