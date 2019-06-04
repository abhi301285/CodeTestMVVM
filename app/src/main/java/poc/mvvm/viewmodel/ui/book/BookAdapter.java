package poc.mvvm.viewmodel.ui.book;

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
import poc.mvvm.viewmodel.data.model.BookList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private final List<BookList> data = new ArrayList<>();
    private Activity activity;
    private BookSelectedListener mListener;

    public BookAdapter(Activity activity, BookViewModel viewModel, LifecycleOwner lifecycleOwner, BookSelectedListener listener) {
        this.activity = activity;
        this.mListener = listener;
        viewModel.getListOverviewResponse().observe(lifecycleOwner, bookListOverviewResponse -> {
            data.clear();
            if (bookListOverviewResponse != null) {
                data.addAll(bookListOverviewResponse.getResults().getLists());
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new BookAdapter.BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        final BookList bookList = data.get(position);
        holder.bookListName.setText(bookList.getList_name());
        holder.bookDisplayName.setText(bookList.getDisplay_name());

        String url = bookList.getList_image();
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .into(holder.ivBookImage);

        holder.bookLayout.setOnClickListener(v -> {
            Log.e("BookAdapter", "List Name === " + bookList.getList_name());
            mListener.onBookSelected(bookList);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class BookViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBookListName)
        TextView bookListName;
        @BindView(R.id.tvBookDisplayName)
        TextView bookDisplayName;
        @BindView(R.id.ivBookImage)
        ImageView ivBookImage;
        @BindView(R.id.bookLayout)
        RelativeLayout bookLayout;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface BookSelectedListener {
        void onBookSelected(BookList bookList);
    }
}
