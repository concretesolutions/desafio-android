package com.concrete.android.challenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class Repository implements Parcelable {

  public static final Creator<Repository> CREATOR = new Creator<Repository>() {
    @Override public Repository createFromParcel(Parcel source) {
      return new Repository(source);
    }

    @Override public Repository[] newArray(int size) {
      return new Repository[size];
    }
  };

  private static final Comparator<Repository> NAME =
      (o1, o2) -> o1.getName().compareTo(o2.getName());

  private static final Comparator<Repository> STAR =
      (o1, o2) -> o2.getStargazersCount().compareTo(o1.getStargazersCount());

  @Json(name = "id") private Long id;
  @Json(name = "name") private String name;
  @Json(name = "owner") private Owner owner;
  @Json(name = "description") private String description;
  @Json(name = "created_at") private Date createdAt;
  @Json(name = "updated_at") private Date updatedAt;
  @Json(name = "stargazers_count") private Long stargazersCount;
  @Json(name = "watchers_count") private Long watchersCount;
  @Json(name = "forks") private Long forks;
  @Json(name = "license") private License license;

  public Repository() {
  }

  public Repository(Long id, String name, Owner owner, String description, Date createdAt,
      Date updatedAt, Long stargazersCount, Long watchersCount, Long forks,
      License license) {
    this.id = id;
    this.name = name;
    this.owner = owner;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.stargazersCount = stargazersCount;
    this.watchersCount = watchersCount;
    this.forks = forks;
    this.license = license;
  }

  protected Repository(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.name = in.readString();
    this.owner = in.readParcelable(Owner.class.getClassLoader());
    this.description = in.readString();
    long tmpCreatedAt = in.readLong();
    this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    long tmpUpdatedAt = in.readLong();
    this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
    this.stargazersCount = (Long) in.readValue(Long.class.getClassLoader());
    this.watchersCount = (Long) in.readValue(Long.class.getClassLoader());
    this.forks = (Long) in.readValue(Long.class.getClassLoader());
    this.license = in.readParcelable(License.class.getClassLoader());
  }

  public static Comparator<Repository> getNameComparator() {
    return NAME;
  }

  public static Comparator<Repository> getStarComparator() {
    return STAR;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(Long stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  public Long getWatchersCount() {
    return watchersCount;
  }

  public void setWatchersCount(Long watchersCount) {
    this.watchersCount = watchersCount;
  }

  public Long getForks() {
    return forks;
  }

  public void setForks(Long forks) {
    this.forks = forks;
  }

  public License getLicense() {
    return license;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  @Override public String toString() {
    return "Repository{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", owner=" + owner +
        ", description='" + description + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", stargazersCount=" + stargazersCount +
        ", watchersCount=" + watchersCount +
        ", forks=" + forks +
        ", license=" + license +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.name);
    dest.writeParcelable(this.owner, flags);
    dest.writeString(this.description);
    dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
    dest.writeValue(this.stargazersCount);
    dest.writeValue(this.watchersCount);
    dest.writeValue(this.forks);
    dest.writeParcelable(this.license, flags);
  }

  @Override public int describeContents() {
    return 0;
  }
}
