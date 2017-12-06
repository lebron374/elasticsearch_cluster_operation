package com.demo.action;

import org.elasticsearch.action.admin.cluster.ClusterAction;
import org.elasticsearch.client.ClusterAdminClient;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroAction extends ClusterAction<HeroRequest, HeroResponse, HeroRequestBuilder> {

    public static final HeroAction INSTANCE = new HeroAction();
    public static final String NAME = "com.demo.hero";

    private HeroAction() {
        super(NAME);
    }


    @Override
    public HeroRequestBuilder newRequestBuilder(ClusterAdminClient client) {
        return new HeroRequestBuilder(client);
    }

    @Override
    public HeroResponse newResponse() {
        return new HeroResponse();
    }
}
