package com.demo.action;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.nodes.NodesOperationRequestBuilder;
import org.elasticsearch.client.ClusterAdminClient;

/**
 * 负责在cluster模式下对request的包装,内部使用到了所谓的builder的设计模式
 * 具体使用逻辑有待验证
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroRequestBuilder extends NodesOperationRequestBuilder<HeroRequest, HeroResponse, HeroRequestBuilder> {

    public HeroRequestBuilder(ClusterAdminClient client) {
        super(client, new HeroRequest());
    }

    public HeroRequestBuilder setName(String name) {
        this.request.setName(name);

        return this;
    }

    public HeroRequestBuilder setSex(String sex) {
        this.request.setSex(sex);

        return this;
    }

    @Override
    protected void doExecute(ActionListener<HeroResponse> listener) {

    }
}
