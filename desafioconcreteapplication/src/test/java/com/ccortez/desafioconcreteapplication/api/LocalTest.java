package com.ccortez.desafioconcreteapplication.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import br.com.concretesolutions.requestmatcher.model.HttpMethod;

public class LocalTest {

    @Rule
    public final RequestMatcherRule serverRule = new LocalTestRequestMatcherRule();

    @Before
    public void setUp() {
        // Setup your application to point to this rootUrl
        final String rootUrl = serverRule.url("/").toString();

        // do setup
    }

    @Test
    public void canMakeRequestAssertions() {

        serverRule.addFixture(200, "api-response/repos-yigit.json")
                .ifRequestMatches()
                .pathIs("/somepath")
                .hasEmptyBody()
                .methodIs(HttpMethod.GET);

        // make interaction with the server
    }
}