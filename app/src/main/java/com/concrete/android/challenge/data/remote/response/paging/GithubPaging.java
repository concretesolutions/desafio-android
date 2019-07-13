package com.concrete.android.challenge.data.remote.response.paging;

/**
 * @author Thiago Corredo
 * @since 2019-07-11
 */
public class GithubPaging {

  private final int nextPage;
  private final int lastPage;

  public GithubPaging(int nextPage, int lastPage) {
    super();
    this.nextPage = nextPage;
    this.lastPage = lastPage;
  }

  public boolean hasMore() {
    return nextPage <= lastPage;
  }

  public int nextPage() {
    return nextPage;
  }

  public int lastPage() {
    return lastPage;
  }
}
