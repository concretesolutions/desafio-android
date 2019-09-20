package br.com.concrete.mock;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Logger;

public class MockServer {

    private MockWebServer mockWebServer;
    private static final Logger logger = Logger.getLogger(MockServer.class.getName());

    public MockServer() {
        try {
            mockWebServer = new MockWebServer();
            mockWebServer.setDispatcher(new DefaultConfigurationDispatcher());
            logger.info(InetAddress.getLocalHost().getHostAddress());
            String address = InetAddress.getLocalHost().getHostAddress();
            InetAddress inetAddress = InetAddress.getByName(address);
            mockWebServer.start(inetAddress, 8080);
        } catch (Exception e) {
            logger.info("Error when try start mock server ..."+ e.getMessage());
        }
    }


    public void changeResponse(Object responseObject) {
        mockWebServer.setDispatcher(new AlterDispatcher(responseObject));
    }

    private class DefaultConfigurationDispatcher extends Dispatcher {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            MockResponse mockResponse = new MockResponse();
            DefaultConfigurationMockResponse defaultConfigurationMockResponse = new DefaultConfigurationMockResponse();
            Object response = defaultConfigurationMockResponse.response(request.getPath());
            String responseJson = new Gson().toJson(response);
            logger.info("------------------------------ --------------- --------------- ---------------");
            logger.info("REQUEST PATH: " + request.getPath());
            logger.info("RESPONSE JSON: " + responseJson);
            mockResponse
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Cache-Control", "no-cache")
                    .setBody(responseJson);
            return mockResponse;
        }
    }

    private class AlterDispatcher extends Dispatcher{

        private Object responseObject;

        public AlterDispatcher(final Object responseObject) {
            this.responseObject = responseObject;
        }

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            Gson gson = new Gson();
            MockResponse mockResponse = new MockResponse();
            mockResponse.setResponseCode(200);
            String responseJson = gson.toJson(responseObject);
            logger.info("RESPONSE ON DISPATCHER: " + responseJson);
            mockResponse.setBody(gson.toJson(responseObject));
            return mockResponse;
        }
    }

    public void stop() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}