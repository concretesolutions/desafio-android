package cl.getapps.githubjavarepos.core.data


open class DataStoreFactory<T>(
    private val cacheDataStore: DataStore<T>,
    private val remoteDataStore: DataStore<T>) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): DataStore<T> {
        if (isCached && !cacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): DataStore<T> {
        return cacheDataStore
    }

    /**
     * Return an instance of the PullRequestsRemote Data Store
     */
    open fun retrieveRemoteDataStore(): DataStore<T> {
        return remoteDataStore
    }
}