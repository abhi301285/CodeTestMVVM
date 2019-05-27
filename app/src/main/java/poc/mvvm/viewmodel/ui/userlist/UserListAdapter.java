package poc.mvvm.viewmodel.ui.userlist;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import poc.mvvm.viewmodel.R;
import poc.mvvm.viewmodel.data.model.Users;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    // private RepoSelectedListener repoSelectedListener;
    private final List<Users> data = new ArrayList<>();
    private Activity activity;
    private UserSelectedListener mListener;

    public UserListAdapter(Activity activity, UserListViewModel viewModel, LifecycleOwner lifecycleOwner, UserSelectedListener listener) {
        this.activity = activity;
        mListener = listener;
        viewModel.getUserList().observe(lifecycleOwner, userListResponse -> {
            data.clear();
            if (userListResponse != null) {
                data.addAll(userListResponse.getData());
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        final Users user = data.get(position);
        holder.userName.setText(user.getFirst_name() + " " + user.getLast_name());
        holder.userEmail.setText(user.getEmail());

        String url = user.getAvatar();
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .into(holder.ivAvatar);

        holder.userLayout.setOnClickListener(v -> {
            Log.e("UserListAdapter", "Users Name === " + user.getFirst_name());
            mListener.onUserSelected(user);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class UserListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView userName;
        @BindView(R.id.tvEmail)
        TextView userEmail;
        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.userLayout)
        RelativeLayout userLayout;


        private Users user;

        public UserListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
