package com.demo.action;

import org.elasticsearch.action.admin.cluster.ClusterAction;
import org.elasticsearch.client.ClusterAdminClient;

/**
 * 负责cluster下Action的包装
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroAction extends ClusterAction<HeroRequest, HeroResponse, HeroRequestBuilder> {

    public static final HeroAction INSTANCE = new HeroAction();
    public static final String NAME = "com.demo.hero";

    private HeroAction() {
        super(NAME);
    }


    /**
     * 负责创建集群工作模式下requestBuilder类，实际是调用真正的builder
     * @param client
     * @return
     */
    @Override
    public HeroRequestBuilder newRequestBuilder(ClusterAdminClient client) {
        return new HeroRequestBuilder(client);
    }

    /**
     * 负责创建集群工作模式下response，实际上是调用真正的response对象
     * @return
     */
    @Override
    public HeroResponse newResponse() {
        return new HeroResponse();
    }
}
