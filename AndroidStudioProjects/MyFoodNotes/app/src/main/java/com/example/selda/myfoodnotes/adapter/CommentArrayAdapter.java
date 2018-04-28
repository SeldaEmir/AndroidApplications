package com.example.selda.myfoodnotes.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.activity.DataBaseHandler;
import com.example.selda.myfoodnotes.model.ProductCommentModel;

public class CommentArrayAdapter extends BaseAdapter {
    private List<ProductCommentModel> commentModelList;
    private static LayoutInflater inflater = null;
    private DataBaseHandler db;

    public CommentArrayAdapter(Activity activity, List<ProductCommentModel> commentModelList) {
        this.commentModelList = commentModelList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = new DataBaseHandler(activity);
    }

    @Override
    public int getCount() {
        return commentModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.comment_row_layout, null);
            holder = new ViewHolder();

            holder.content = view.findViewById(R.id.itemContentTextView);
            holder.date = view.findViewById(R.id.itemDateTextView);
            holder.degree = view.findViewById(R.id.itemDegreeTextView);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ProductCommentModel model = commentModelList.get(i);

        holder.date.setText(model.getComment_date());
        holder.content.setText(model.getComment_content());

        String degree = model.getComment_degree();
        if (Double.valueOf(degree) > 3) {
            holder.degree.setTextColor(Color.parseColor("#16BA78"));
        } else {
            holder.degree.setTextColor(Color.RED);
        }
        holder.degree.setText("Rating: " + degree);

        return view;
    }

    private class ViewHolder {
        TextView date;
        TextView degree;
        TextView content;
    }
}
