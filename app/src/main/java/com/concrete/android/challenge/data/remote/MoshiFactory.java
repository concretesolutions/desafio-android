package com.concrete.android.challenge.data.remote;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;
import java.util.Date;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public final class MoshiFactory {

  private static final Moshi MOSHI =
      new Moshi.Builder().add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
          .build();

  private MoshiFactory() {
    throw new RuntimeException("No instances!");
  }

  public static Moshi get() {
    return MOSHI;
  }
}
