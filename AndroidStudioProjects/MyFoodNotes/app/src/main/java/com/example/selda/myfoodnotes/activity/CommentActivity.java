package com.example.selda.myfoodnotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.adapter.CommentArrayAdapter;
import com.example.selda.myfoodnotes.model.ProductCommentModel;

public class CommentActivity extends AppCompatActivity {
    DataBaseHandler db;
    ListView commentListView;
    List<ProductCommentModel> commentModels;
    CommentArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DataBaseHandler(this);

        FloatingActionButton fab = findViewById(R.id.fab);

        String productId = getIntent().getExtras().getString("id");
        commentListView = findViewById(R.id.commentListView);
        commentModels = db.getCommentsByProductId(productId);

        adapter = new CommentArrayAdapter(this, commentModels);
        commentListView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
