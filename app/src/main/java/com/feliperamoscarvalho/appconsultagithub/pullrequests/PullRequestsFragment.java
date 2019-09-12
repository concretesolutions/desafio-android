package com.feliperamoscarvalho.appconsultagithub.pullrequests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feliperamoscarvalho.appconsultagithub.R;
import com.feliperamoscarvalho.appconsultagithub.data.PullRequestServiceImpl;
import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PullRequestsFragment extends Fragment implements PullRequestsContract.View {

    private PullRequestsContract.UserActionsListener mActionsListener;
    private PullRequestsAdapter mListAdapter;

    String login;
    String repositoryName;

    /**
     * Returns an instance of the fragment.
     */
    public static PullRequestsFragment newInstance() {
        return new PullRequestsFragment();
    }

    /**
     * Instantiate adapter and object containing user actions (presenter).
     * Get bundle parameters to use for endpoint data search.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        login = args.getString("login");
        repositoryName = args.getString("repositoryName");

        mListAdapter = new PullRequestsAdapter(new ArrayList<Pull>(0), mItemListener);
        mActionsListener = new PullRequestsPresenter(new PullRequestServiceImpl(), this);
    }

    /**
     * Call presenter function to load data.
     */
    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadPullRequest(login, repositoryName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pull_requests_fragment, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.list_pull_requests);
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadPullRequest(login, repositoryName);
            }
        });

        return root;
    }

    /**
     * Set load when loading the SwipeRefreshLayout.
     *
     * @param isActive indicates whether or not to show load.
     */
    @Override
    public void setLoading(final boolean isActive) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(isActive);
            }
        });
    }

    /**
     * Update data on adapter.
     *
     * @param pullRequests list of pull requests that will be added to the adapter.
     */
    @Override
    public void showPullRequestsItems(List<Pull> pullRequests) {
        mListAdapter.replaceData(pullRequests);
    }

    /**
     * Show the details pull request in browser of the selected item.
     *
     * @param htmlUrl URL to open in browser.
     */
    @Override
    public void showDetailsInBrowser(String htmlUrl) {

        if (!htmlUrl.startsWith("http://") && !htmlUrl.startsWith("https://")) {
            htmlUrl = "http://" + htmlUrl;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(htmlUrl));
        startActivity(intent);
    }

    ItemListener mItemListener = new ItemListener() {
        @Override
        public void onPullRequestClick(String htmlUrl) {
            mActionsListener.openDetailsInBrowser(htmlUrl);
        }
    };

    /**
     * Adapter class for RecyclerView that displays pull request data.
     *
     */
    private static class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.ViewHolder> {

        private List<Pull> mPullRequests;
        private ItemListener mitemListener;

        public PullRequestsAdapter(List<Pull> pullRequests, ItemListener itemListener) {
            setList(pullRequests);
            mitemListener = itemListener;
        }

        public void replaceData(List<Pull> notes) {
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Pull> notes) {
            mPullRequests = notes;
        }

        public Pull getItem(int position) {
            return mPullRequests.get(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.pull_request_item, parent, false);

            return new ViewHolder(noteView, mitemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Pull pullRequest = mPullRequests.get(position);

            viewHolder.txtName.setText(pullRequest.getTitle());
            viewHolder.txtDescription.setText(pullRequest.getBody());
            viewHolder.txtIdUser.setText(Integer.toString(pullRequest.getId()));
            viewHolder.txtUserName.setText(pullRequest.getUser().getLogin());

            Context context = viewHolder.imageView.getContext();

            Picasso.with(context)
                    .load(pullRequest.getUser().getAvatarUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(viewHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return mPullRequests.size();
        }

        /**
         * ViewHolder class for use in PullRequest adapter.
         *
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView imageView;
            public TextView txtName;
            public TextView txtDescription;
            public TextView txtUserName;
            public TextView txtIdUser;
            public ItemListener mItemListener;

            /**
             * Constructor starts the views.
             *
             * @param itemView view of record that will be displayed in RecyclerView.
             * @param listener object that implements RecyclerView record click.
             */
            public ViewHolder(View itemView, ItemListener listener) {
                super(itemView);

                mItemListener = listener;
                txtName = (TextView) itemView.findViewById(R.id.txtName);
                txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
                txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
                txtIdUser = (TextView) itemView.findViewById(R.id.txtIdUser);
                imageView = (ImageView) itemView.findViewById(R.id.imgUser);
                itemView.setOnClickListener(this);
            }

            /**
             * Add onClick action to ViewHolder register.
             *
             * @param view view of record that will be clicked in RecyclerView.
             *
             */
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Pull pullRequest = getItem(position);
                mitemListener.onPullRequestClick(pullRequest.getHtmlUrl());
            }
        }
    }

    public interface ItemListener {

        void onPullRequestClick(String htmlUrl);
    }

}
