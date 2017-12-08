package com.demo.action;

import org.elasticsearch.action.admin.cluster.ClusterAction;
import org.elasticsearch.client.ClusterAdminClient;

/**
 * 负责向ES注册Action和对应的TransportNodesHeroAction的关系
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroAction extends ClusterAction<HeroNodesRequest, HeroNodesResponse, HeroNodesRequestBuilder> {

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
    public HeroNodesRequestBuilder newRequestBuilder(ClusterAdminClient client) {
        return new HeroNodesRequestBuilder(client);
    }

    /**
     * 负责创建集群工作模式下response，实际上是调用真正的response对象
     * @return
     */
    @Override
    public HeroNodesResponse newResponse() {
        return new HeroNodesResponse();
    }
}
