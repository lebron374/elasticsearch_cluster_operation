package com.demo.action;

import org.elasticsearch.action.support.nodes.NodesOperationResponse;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroResponse extends NodesOperationResponse<HeroInfo> implements ToXContent {

    HeroResponse() {

    }
    public HeroResponse(ClusterName clusterName, HeroInfo[] nodes) {
        super(clusterName, nodes);
    }

    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("cluster_name", getClusterName().value(), XContentBuilder.FieldCaseConversion.NONE);
        builder.startObject("nodes");
        for (HeroInfo heroInfo : this) {

            builder.field("localIp", heroInfo.getLocalIp(), XContentBuilder.FieldCaseConversion.NONE);

            builder.endObject();
        }
        builder.endObject();
        return builder;
    }
}
