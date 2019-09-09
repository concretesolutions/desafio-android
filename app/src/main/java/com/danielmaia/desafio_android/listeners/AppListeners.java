package com.danielmaia.desafio_android.listeners;

import java.util.ArrayList;
import java.util.List;

public class AppListeners {

    private List<ListListener> listListeners;
    private List<PullListener> pullListeners;

    private static AppListeners instance;

    public AppListeners() {
        listListeners = new ArrayList<>();
        pullListeners = new ArrayList<>();
    }

    public static synchronized AppListeners getInstance() {
        if (instance == null) {
            instance = new AppListeners();
        }

        return instance;
    }



    /*  Início List */
    public synchronized void addListListener(ListListener listener) {
        if (listener != null && !listListeners.contains(listener)) {
            listListeners.add(listener);
        }
    }

    public synchronized void removeListListener(ListListener listener) {
        if (listener != null && listListeners.contains(listener)) {
            listListeners.remove(listener);
        }
    }

    public synchronized void notifyOnListSuccess(boolean hasMore) {
        for (ListListener listener : listListeners) {
            try {
                listener.onListSuccess(hasMore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void notifyOnListError(String message) {
        for (ListListener listener : listListeners) {
            try {
                listener.onListError(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /* Fim List */



    /*  Início Pull */
    public synchronized void addPullListener(PullListener listener) {
        if (listener != null && !pullListeners.contains(listener)) {
            pullListeners.add(listener);
        }
    }

    public synchronized void removePullListener(PullListener listener) {
        if (listener != null && pullListeners.contains(listener)) {
            pullListeners.remove(listener);
        }
    }

    public synchronized void notifyOnPullSuccess() {
        for (PullListener listener : pullListeners) {
            try {
                listener.onPullSuccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void notifyOnPullError(String message) {
        for (PullListener listener : pullListeners) {
            try {
                listener.onPullError(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /* Fim Pull */

}
