package com.example.javapop.Events;

import com.example.javapop.Models.Repository;

public class ClickItemRepositoryEvent {
    private Repository mRepository;
    private int position;

    public ClickItemRepositoryEvent(Repository Repository, int adapterPosition) {
        this.mRepository = Repository;
        this.position = adapterPosition;
    }

    public Repository getRepository() {
        return mRepository;
    }

    public int getPosition() {
        return position;
    }
}
