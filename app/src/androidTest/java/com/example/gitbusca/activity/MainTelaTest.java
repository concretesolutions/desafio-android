package com.example.gitbusca.activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.gitbusca.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainTelaTest {

    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>( MainActivity.class, true, true);

    @Test
    public void verificar_estado_inicial_mainActivity(){

        onView(withId(R.id.progressRep)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerRepositorios)).check(matches(isDisplayed()));
        onView(isCompletelyDisplayed());

    }


}