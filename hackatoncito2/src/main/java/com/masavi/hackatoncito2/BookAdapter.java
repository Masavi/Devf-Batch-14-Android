package com.masavi.hackatoncito2;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.masavi.hackatoncito2.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Masavi on 11/5/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> items;

    public BookAdapter(List<Book> items){
        this.items = items;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bindBookItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class BookViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_book_title)
        TextView tvBookTitle;

        @BindView(R.id.tv_book_isbn)
        TextView tvBookIsbn;

        @BindView(R.id.img_book)
        ImageView imgBook;

        private View rootView;

        public BookViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindBookItem(Book book){
            tvBookTitle.setText(book.getTitle() + "");
            tvBookIsbn.setText( book.getIsbn() + "");

            if(!book.getCover().isEmpty()){
                Picasso.with(rootView.getContext())
                        .load(book.getCover()).into(imgBook);
            } else {
                Log.e("myLog", book.getTitle() + " no tiene imagen");
            }
        }
    }
}
