package com.masavi.hackatoncito2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.masavi.hackatoncito2.api.BooksAPI;
import com.masavi.hackatoncito2.api.ServiceGenerator;
import com.masavi.hackatoncito2.models.Book;
import com.masavi.hackatoncito2.models.BookObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_title)
    EditText inputTitle;

    @BindView(R.id.rv_books)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_book_search)
    public void onClick() {
        String query = inputTitle.getText().toString();
        searchBooksByTitle(query);
    }

    public void  settingRecyclerView(List<Book> books){
        BookAdapter bookAdapter = new BookAdapter(books);

        // Layout manager que define la manera en la que se organizan
        // mis elementos dentro del recyclerview
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);

        // Separador entre los elementos del recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bookAdapter);
    }

    private void searchBooksByTitle(String title) {
        BooksAPI booksAPI = ServiceGenerator.createService();

        booksAPI.searchBooksByTitle(title).enqueue(new Callback<BookObject>() {
            @Override
            public void onResponse(Call<BookObject> call, Response<BookObject> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        BookObject bookObject = response.body();
                        Book books = bookObject.getBooks().get(0);
                        //if (bookObject.getResults() != "0"){
                        //   Book books = bookObject.getBooks().get(0);
                        //}
                        Log.e("myLog", bookObject.toString());
                        settingRecyclerView(bookObject.getBooks());
                    } else {
                        Toast.makeText(MainActivity.this, "No existen resultados para esta búsqueda", Toast.LENGTH_SHORT).show();
                    }

                    }else {
                    Log.e("myLog", "Status Code" + response.code());
                }

                }

            @Override
            public void onFailure(Call<BookObject> call, Throwable t) {
                Log.e("myLog", t.getMessage());
                t.printStackTrace();
            }

        });

    }
}
