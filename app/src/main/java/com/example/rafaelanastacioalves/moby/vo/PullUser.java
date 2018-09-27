package com.example.rafaelanastacioalves.moby.vo;

import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class PullUser extends GitHubUser {
}
