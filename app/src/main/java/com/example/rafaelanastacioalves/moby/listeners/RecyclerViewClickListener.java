package com.example.rafaelanastacioalves.moby.listeners;


import android.view.View;
public interface RecyclerViewClickListener {

    /**
     * Callback method to be invoked when a item in a
     * RecyclerView is clicked
     *
     * @param view     The view within the RecyclerView.Adapter
     * @param position The position of the view in the adapter
     */
    public void onClick(View view, int position);
}
