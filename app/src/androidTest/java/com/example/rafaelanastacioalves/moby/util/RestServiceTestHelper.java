package com.example.rafaelanastacioalves.moby.util;

import android.content.Context;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.repolisting.RepoViewHolder;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rafaelanastacioalves on 28/03/18.
 */

public class RestServiceTestHelper {
    public static String getStringFromFile(Context context, String filpePath) throws IOException {
        final InputStream stream = context.getResources().getAssets().open(filpePath);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }


    public static Matcher<RecyclerView.ViewHolder> withHolderTitleView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RepoViewHolder>(RepoViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(RepoViewHolder item) {
                TextView timeViewText = (TextView) item.itemView.findViewById(R.id.repo_text_view_title);
                if (timeViewText == null) {
                    return false;
                }
                return timeViewText.getText().toString().contains(text);
            }
        };
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }
}
