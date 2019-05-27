package poc.mvvm.viewmodel.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import poc.mvvm.viewmodel.R;
import poc.mvvm.viewmodel.base.BaseActivity;
import poc.mvvm.viewmodel.data.rest.request.LoginRequest;
import poc.mvvm.viewmodel.ui.userlist.UserListActivity;
import poc.mvvm.viewmodel.util.ViewModelFactory;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.loading_view)
    View loadingView;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @Inject
    ViewModelFactory viewModelFactory;
    private LoginViewModel viewModel;


    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest request = new LoginRequest();
                request.setEmail(edtUsername.getText().toString().trim());
                request.setPassword(edtPassword.getText().toString().trim());
                viewModel.getLogin(request);
                observableViewModel();
            }
        });
    }

    private void observableViewModel() {
        viewModel.getLoginResponse().observe(this, repos -> {
            if (repos != null) {
                Toast.makeText(this, "Token Login === " + repos.getToken(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UserListActivity.class));
            }
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                Toast.makeText(this, "Error while getting the login", Toast.LENGTH_SHORT).show();
//                errorTextView.setVisibility(View.VISIBLE);
//                listView.setVisibility(View.GONE);
//                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                Toast.makeText(this, "Error while getting the login else", Toast.LENGTH_SHORT).show();
//                errorTextView.setVisibility(View.GONE);
//                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }
}
