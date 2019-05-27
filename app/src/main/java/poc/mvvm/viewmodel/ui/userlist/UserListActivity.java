package poc.mvvm.viewmodel.ui.userlist;

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
import poc.mvvm.viewmodel.data.model.Users;
import poc.mvvm.viewmodel.util.ViewModelFactory;

public class UserListActivity extends BaseActivity implements UserSelectedListener {


    @BindView(R.id.userListRecycler)
    RecyclerView userRecyclerView;
    @BindView(R.id.loading_view)
    View loadingView;
    @Inject
    ViewModelFactory viewModelFactory;
    private UserListViewModel viewModel;
    String TAG = UserListActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel.class);

        userRecyclerView.setAdapter(new UserListAdapter(this, viewModel, this, this));
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getUserList(2);
        observableViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_list;
    }


    private void observableViewModel() {
        //to update the UI
        viewModel.getUserList().observe(this, userListResponse -> {
            if (userListResponse != null) {
                //Toast.makeText(this, "User List size === " + userListResponse.getData().size(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onUserSelected(Users user) {
        Toast.makeText(this, "Selected User === " + user.getFirst_name(), Toast.LENGTH_SHORT).show();
    }
}
