package com.demo.rest;

import com.demo.action.HeroAction;
import com.demo.action.HeroNodesRequest;
import com.demo.action.HeroNodesRequestBuilder;
import com.demo.action.HeroNodesResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.action.support.RestToXContentListener;

import java.io.IOException;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class RestHeroAction extends BaseRestHandler {

    Client client;

    @Inject
    public RestHeroAction(Settings settings, Client client, RestController controller) {
        super(settings, controller, client);
        this.client = client;
        controller.registerHandler(RestRequest.Method.GET, "/_hero", new Get());
        controller.registerHandler(RestRequest.Method.POST, "/_hero", new Post());
        controller.registerHandler(RestRequest.Method.PUT, "/_hero", new Put());
    }

    class Get implements RestHandler {

        public void handleRequest(RestRequest request, RestChannel channel) throws Exception {
            try {

                String name = request.param("name");
                String sex = request.param("sex");

                HeroNodesRequestBuilder heroNodesRequestBuilder = new HeroNodesRequestBuilder(client.admin().cluster()).setName(name).setSex(sex);
                HeroNodesRequest heroNodesRequest = heroNodesRequestBuilder.request();
                client.admin().cluster().execute(HeroAction.INSTANCE, heroNodesRequest, new RestToXContentListener<HeroNodesResponse>(channel));
            } catch (Throwable ex) {
                logger.error(ex.getMessage(), ex);
                try {
                    channel.sendResponse(new BytesRestResponse(channel, ex));
                } catch (IOException ex2) {
                    logger.error(ex2.getMessage(), ex2);
                    channel.sendResponse(new BytesRestResponse(RestStatus.INTERNAL_SERVER_ERROR));
                }
            }
        }
    }

    class Post implements RestHandler {

        public void handleRequest(RestRequest request, RestChannel channel) throws Exception {
            try {
                String name = request.param("name");
                String sex = request.param("sex");

                HeroNodesRequestBuilder heroNodesRequestBuilder = new HeroNodesRequestBuilder(client.admin().cluster()).setName(name).setSex(sex);
                HeroNodesRequest heroNodesRequest = heroNodesRequestBuilder.request();
                client.admin().cluster().execute(HeroAction.INSTANCE, heroNodesRequest, new RestToXContentListener<HeroNodesResponse>(channel));
            } catch (Throwable ex) {
                logger.error(ex.getMessage(), ex);
                try {
                    channel.sendResponse(new BytesRestResponse(channel, ex));
                } catch (IOException ex2) {
                    logger.error(ex2.getMessage(), ex2);
                    channel.sendResponse(new BytesRestResponse(RestStatus.INTERNAL_SERVER_ERROR));
                }
            }
        }
    }

    class Put implements RestHandler {

        public void handleRequest(RestRequest request, RestChannel channel) throws Exception {
            try {
                String name = request.param("name");
                String sex = request.param("sex");

                HeroNodesRequestBuilder heroNodesRequestBuilder = new HeroNodesRequestBuilder(client.admin().cluster()).setName(name).setSex(sex);
                HeroNodesRequest heroNodesRequest = heroNodesRequestBuilder.request();
                client.admin().cluster().execute(HeroAction.INSTANCE, heroNodesRequest, new RestToXContentListener<HeroNodesResponse>(channel));
            } catch (Throwable ex) {
                logger.error(ex.getMessage(), ex);
                try {
                    channel.sendResponse(new BytesRestResponse(channel, ex));
                } catch (IOException ex2) {
                    logger.error(ex2.getMessage(), ex2);
                    channel.sendResponse(new BytesRestResponse(RestStatus.INTERNAL_SERVER_ERROR));
                }
            }
        }
    }

    @Override
    protected void handleRequest(RestRequest request, RestChannel channel, Client client) throws Exception {

    }
}
