package com.concrete.android.challenge.data.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.concrete.android.challenge.data.model.Repository;
import com.squareup.moshi.Json;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryResponse implements Parcelable {

  public static final Creator<RepositoryResponse> CREATOR = new Creator<RepositoryResponse>() {
    @Override public RepositoryResponse createFromParcel(Parcel source) {
      return new RepositoryResponse(source);
    }

    @Override public RepositoryResponse[] newArray(int size) {
      return new RepositoryResponse[size];
    }
  };

  @Json(name = "total_count") private Long totalCount;
  @Json(name = "incomplete_results") private Boolean incompleteResults;
  @Json(name = "items") private List<Repository> items;

  public RepositoryResponse() {
  }

  public RepositoryResponse(Long totalCount, Boolean incompleteResults,
      List<Repository> items) {
    this.totalCount = totalCount;
    this.incompleteResults = incompleteResults;
    this.items = items;
  }

  protected RepositoryResponse(Parcel in) {
    this.totalCount = (Long) in.readValue(Long.class.getClassLoader());
    this.incompleteResults = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.items = in.createTypedArrayList(Repository.CREATOR);
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public Boolean getIncompleteResults() {
    return incompleteResults;
  }

  public void setIncompleteResults(Boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  public List<Repository> getItems() {
    return items;
  }

  public void setItems(List<Repository> items) {
    this.items = items;
  }

  @Override public String toString() {
    return "RepositoryResponse{" +
        "totalCount=" + totalCount +
        ", incompleteResults=" + incompleteResults +
        ", items=" + items +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.totalCount);
    dest.writeValue(this.incompleteResults);
    dest.writeTypedList(this.items);
  }

  @Override public int describeContents() {
    return 0;
  }
}
