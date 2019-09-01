package br.com.concretizando.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.concretizando.R;
import br.com.concretizando.model.PullRequest;

public class ListPullRequestAdapter extends BaseAdapter {

    private final List<PullRequest> list;
    private final Activity act;

    public ListPullRequestAdapter(List<PullRequest> list, Activity act) {

        this.list = list;
        this.act = act;
    }

    @Override
    public int getCount() {

        return this.list.size();
    }

    @Override
    public PullRequest getItem(int i) {

        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = act.getLayoutInflater().inflate(R.layout.list_pullrequest, viewGroup, false);
        PullRequest pullRequest = this.list.get(i);
        ImageView userAvatar = view.findViewById(R.id.userAvatar);
        TextView authorName = view.findViewById(R.id.authorName);
        TextView pullRequestTitle = view.findViewById(R.id.pullRequestTitle);
        TextView pullRequestBody = view.findViewById(R.id.pullRequestBody);
        TextView pullRequestDate = view.findViewById(R.id.pullRequestDate);
        Picasso.with(act).load(pullRequest.getUser().getAvatar_url()).into(userAvatar);
        authorName.setText(pullRequest.getUser().getLogin());
        pullRequestTitle.setText(pullRequest.getTitle());
        pullRequestBody.setText(pullRequest.getBody());
        pullRequestDate.setText(pullRequest.getCreated_at().substring(0,10));
        return view;
    }
}







