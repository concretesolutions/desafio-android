package com.joseluzardo.githubjavatoplist;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.joseluzardo.githubjavatoplist.Models.Pulls.PullsItem;
import com.joseluzardo.githubjavatoplist.Models.Repos.JavaList;
import com.joseluzardo.githubjavatoplist.Netwok.HttpService;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HttpTest {

    @Test
    public void testReposServiceSortStar(){

        final Call<JavaList> javaList = HttpService.getRepos().list("language:Java","stars",String.valueOf(1));

        javaList.enqueue(new Callback<JavaList>() {
            @Override
            public void onResponse(@NonNull Call<JavaList> call, @NonNull Response<JavaList> response) {

                System.out.println("REPOS: "+new Gson().toJson(response.body()));


            }

            @Override
            public void onFailure(@NonNull Call<JavaList> call, Throwable t) {

                System.out.println("ERROR: "+ t.getMessage());
            }

        });

    }

    @Test
    public void testReposServiceSortFork(){

        final Call<JavaList> javaList = HttpService.getRepos().list("language:Java","forks",String.valueOf(1));

        javaList.enqueue(new Callback<JavaList>() {
            @Override
            public void onResponse(@NonNull Call<JavaList> call, @NonNull Response<JavaList> response) {

                System.out.println("REPOS: "+new Gson().toJson(response.body()));


            }

            @Override
            public void onFailure(@NonNull Call<JavaList> call, Throwable t) {

                System.out.println("ERROR: "+ t.getMessage());
            }

        });

    }

    @Test
    public void testReposServiceSortUpdated(){

        final Call<JavaList> javaList = HttpService.getRepos().list("language:Java","updates",String.valueOf(1));

        javaList.enqueue(new Callback<JavaList>() {
            @Override
            public void onResponse(@NonNull Call<JavaList> call, @NonNull Response<JavaList> response) {

                System.out.println("REPOS: "+new Gson().toJson(response.body()));


            }

            @Override
            public void onFailure(@NonNull Call<JavaList> call, Throwable t) {

                System.out.println("ERROR: "+ t.getMessage());
            }

        });

    }

    @Test
    public void testPullService(){

        final Call<List<PullsItem>> pullList = HttpService.getPullRequest().list("concretesolutions","desafio-android");

        pullList.enqueue(new Callback<List<PullsItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<PullsItem>> call, @NonNull Response<List<PullsItem>> response) {

            }

            @Override
            public void onFailure(Call<List<PullsItem>> call, Throwable t) {

            }


        });

    }

}