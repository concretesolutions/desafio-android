package br.com.appdesafio.util;

/**
 * Class to open activities from any part of the code.
 */
public enum IntentActions {
    LIST_REPOSITORY_ACTIVITY("appdesafio.com.br.appdesafio.LIST_REPOSITORY_ACTIVITY");





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