package com.demo.action;

import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
import org.elasticsearch.action.support.nodes.NodesOperationResponse;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroResponse extends NodesOperationResponse<HeroInfo> implements ToXContent {

    private String localIp;

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    HeroResponse() {
    }


    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        nodes = new HeroInfo[in.readVInt()];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = HeroInfo.readHeroInfo(in);
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeVInt(nodes.length);
        for (HeroInfo node : nodes) {
            node.writeTo(out);
        }
    }

    public HeroResponse(ClusterName clusterName, HeroInfo[] nodes) {
        super(clusterName, nodes);
    }

    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("cluster_name", getClusterName().value(), XContentBuilder.FieldCaseConversion.NONE);
        builder.startObject("nodes");
        for (HeroInfo heroInfo : nodes) {

            builder.field("localIp", heroInfo.getLocalIp(), XContentBuilder.FieldCaseConversion.NONE);

        }
        builder.endObject();
        return builder;
    }
}
