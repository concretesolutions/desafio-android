package br.com.appdesafio.util;

/**
 * Class to open activities from any part of the code.
 */
public enum IntentActions {
    LIST_REPOSITORY_ACTIVITY("br.com.appdesafio.LIST_REPOSITORY_ACTIVITY"),
    LIST_PULL_REQUEST_ACTIVITY("br.com.appdesafio.LIST_PULL_REQUEST_ACTIVITY");


    private final String action;

    /**
     * Default constructor
     *
     * @param action
     */
    IntentActions(final String action) {
        this.action = action;
    }

    /**
     * Returns the current action
     *
     * @return
     */
    public String getAction() {
        return action;
    }
}