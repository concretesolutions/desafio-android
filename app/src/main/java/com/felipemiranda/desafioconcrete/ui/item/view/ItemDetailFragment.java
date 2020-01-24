package com.felipemiranda.desafioconcrete.ui.item.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipemiranda.desafioconcrete.R;
import com.felipemiranda.desafioconcrete.model.ItemDetail;
import com.felipemiranda.desafioconcrete.ui.home.view.HomeActivity;
import com.felipemiranda.desafioconcrete.ui.item.view.adapter.ItemDetailAdapter;
import com.felipemiranda.desafioconcrete.ui.item.presenter.ItemDetailPresenter;
import com.felipemiranda.desafioconcrete.ui.main.BaseFragment;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingPresenter;
import com.felipemiranda.desafioconcrete.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by felipemiranda
 */

public class ItemDetailFragment extends BaseFragment implements ItemDetailView, ItemDetailAdapter.OnClickItem {

    @BindView(R.id.rv_list_items)
    RecyclerView rvListItems;

    private static final String URL = "URL";
    private static final String NAME = "NAME";

    private ItemDetailPresenter mItemDetailPresenter;

    public static ItemDetailFragment newInstance(String url, String name) {
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(NAME, name);
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItemDetailPresenter = new ItemDetailPresenter();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String url = getArguments().getString(URL);
        url = url.substring(0, url.length() - 9);

        mItemDetailPresenter.requestItemDetail(url);
    }

    @Override
    public BaseLoadingPresenter getPresenter() {
        return mItemDetailPresenter;
    }

    @Override
    public void successItemDetail(ArrayList<ItemDetail> response) {
        if (response != null && !response.isEmpty()) {
            getActivity().setTitle(getArguments().getString(NAME));
            ItemDetailAdapter itemDetailAdapter = new ItemDetailAdapter(response, this);
            rvListItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            rvListItems.setAdapter(itemDetailAdapter);
            Utils.recyclerViewAnimationEntrance(rvListItems);
        } else {
            getActivity().onBackPressed();
            Toast.makeText(getActivity(), getString(R.string.not_pull), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickItem(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
