package com.feliperamoscarvalho.appconsultagithub.repositories;

import android.content.Context;
import android.content.Intent;
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

import com.feliperamoscarvalho.appconsultagithub.PullRequestsActivity;
import com.feliperamoscarvalho.appconsultagithub.R;
import com.feliperamoscarvalho.appconsultagithub.data.RepositoryServiceImpl;
import com.feliperamoscarvalho.appconsultagithub.data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesFragment extends Fragment implements RepositoriesContract.View {

    private RepositoriesContract.UserActionsListener mActionsListener;
    private RepositoriesAdapter mListAdapter;

    /**
     * Returns an instance of the fragment.
     */
    public static RepositoriesFragment newInstance() {
        return new RepositoriesFragment();
    }

    /**
     * Instantiate adapter and object containing user actions (presenter).
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new RepositoriesAdapter(new ArrayList<Item>(0), mItemListener);
        mActionsListener = new RepositoriesPresenter(new RepositoryServiceImpl(), this);
    }

    /**
     * Call presenter function to load data.
     */
    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadRepository();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.repositories_fragment, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.list_repositories);
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
                mActionsListener.loadRepository();
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
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(isActive);
            }
        });
    }

    /**
     * Update data on adapter
     *
     * @param repositories list of repositories that will be added to the adapter.
     */
    @Override
    public void showRepositoryItems(List<Item> repositories) {
        mListAdapter.replaceData(repositories);
    }

    /**
     * Show the details screen of the selected item.
     *
     * @param login          login that will be used in the pull requests search.
     * @param repositoryName name of the repository that will be used in the pull requests search.
     */
    @Override
    public void showDetailsUI(String login, String repositoryName) {
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        bundle.putString("repositoryName", repositoryName);
        Intent intent = new Intent(getContext(), PullRequestsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    ItemListener mItemListener = new ItemListener() {
        @Override
        public void onRepositoryClick(Item item) {
            mActionsListener.openDetails(item);
        }
    };

    /**
     * Adapter class for RecyclerView that displays repository data.
     *
     */
    private static class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

        private List<Item> mRepositories;
        private ItemListener mItemListener;

        public RepositoriesAdapter(List<Item> repositories, ItemListener itemListener) {
            setList(repositories);
            mItemListener = itemListener;
        }

        public void replaceData(List<Item> notes) {
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Item> notes) {
            mRepositories = notes;
        }

        public Item getItem(int position) {
            return mRepositories.get(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.repository_item, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Item item = mRepositories.get(position);

            viewHolder.txtName.setText(item.getName());
            viewHolder.txtDescription.setText(item.getDescription());
            viewHolder.txtForksCount.setText(Integer.toString(item.getForksCount()));
            viewHolder.txtStarsCount.setText(Integer.toString(item.getStargazersCount()));
            viewHolder.txtUserName.setText(item.getOwner().getLogin());
            viewHolder.txtIdUser.setText(Integer.toString(item.getOwner().getId()));

            Context context = viewHolder.imageView.getContext();

            Picasso.with(context)
                    .load(item.getOwner().getAvatarUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(viewHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return mRepositories.size();
        }

        /**
         * ViewHolder class for use in repository adapter.
         *
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView imageView;
            public TextView txtName;
            public TextView txtDescription;
            public TextView txtForksCount;
            public TextView txtStarsCount;
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
                txtForksCount = (TextView) itemView.findViewById(R.id.txtForksCount);
                txtStarsCount = (TextView) itemView.findViewById(R.id.txtStarsCount);
                txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
                txtIdUser = (TextView) itemView.findViewById(R.id.txtIdUser);
                imageView = (ImageView) itemView.findViewById(R.id.imgUser);
                itemView.setOnClickListener(this);
            }

            /**
             * Add onClick action to ViewHolder register.
             *
             * @param view view of record that will be displayed in RecyclerView.
             *
             */
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Item item = getItem(position);
                mItemListener.onRepositoryClick(item);
            }
        }
    }

    public interface ItemListener {

        void onRepositoryClick(Item clickedNote);
    }
}
