package com.concrete.android.challenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class License implements Parcelable {

  public static final Creator<License> CREATOR = new Creator<License>() {
    @Override public License createFromParcel(Parcel source) {
      return new License(source);
    }

    @Override public License[] newArray(int size) {
      return new License[size];
    }
  };

  @Json(name = "name") private String name;

  public License() {
  }

  public License(String name) {
    this.name = name;
  }

  protected License(Parcel in) {
    this.name = in.readString();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return "License{" +
        "name='" + name + '\'' +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
  }

  @Override public int describeContents() {
    return 0;
  }
}
