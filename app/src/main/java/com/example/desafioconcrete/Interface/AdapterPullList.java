package com.example.desafioconcrete.Interface;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.desafioconcrete.Objects.DetailsPull;
import com.example.desafioconcrete.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterPullList extends BaseAdapter {

    private Context context;
    private List<DetailsPull> detailsPullList;
    private LayoutInflater inflater;

    public AdapterPullList(Context context, List<DetailsPull> detailsPullList) {
        this.context = context;
        this.detailsPullList = detailsPullList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return detailsPullList.size();
    }

    @Override
    public Object getItem(int i) {
        return detailsPullList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        if (view == null) {
            rowView = inflater.inflate(R.layout.adapter_listview_pull, null);
        } else {
            rowView = view;
        }

        TextView txtPullTitle = rowView.findViewById(R.id.txtPullTitle);
        TextView txtPullBody = rowView.findViewById(R.id.txtPullBody);
        TextView txtPullUsername = rowView.findViewById(R.id.txtPullUsername);
        TextView txtPullDate = rowView.findViewById(R.id.txtPullDate);

        ImageView imgPullAvatar = rowView.findViewById(R.id.imgPullAvatar);

        txtPullTitle.setText(detailsPullList.get(i).getTitle());
        if (detailsPullList.get(i).getBody().isEmpty()){
            txtPullBody.setText(context.getResources().getString(R.string.empty_body));
        } else {
            txtPullBody.setText(detailsPullList.get(i).getBody());
        }
        txtPullUsername.setText(detailsPullList.get(i).getUser().getLogin());

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            SimpleDateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = format.parse(detailsPullList.get(i).getCreated_at());
            txtPullDate.setText(formatBR.format(date));
        } catch (ParseException e) {
            txtPullDate.setText(detailsPullList.get(i).getCreated_at());
        }

        Glide.with(context).load(detailsPullList.get(i).getUser().getAvatar_url()).into(imgPullAvatar);

        return rowView;
    }
}
