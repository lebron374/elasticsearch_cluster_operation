package com.demo.action;

import org.elasticsearch.action.support.nodes.NodesOperationResponse;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * 负责在cluster模式下整个集群的response数据
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroResponse extends NodesOperationResponse<HeroInfo> implements ToXContent {

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

    /**
     * 负责组装返回的数据
     * @param builder
     * @param params
     * @return
     * @throws IOException
     */
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("cluster_name", getClusterName().value(), XContentBuilder.FieldCaseConversion.NONE);
        builder.startObject("nodes");
        for (HeroInfo heroInfo : nodes) {

            builder.startObject(heroInfo.getNode().getId(), XContentBuilder.FieldCaseConversion.NONE);

            builder.field("localIp", heroInfo.getLocalIp(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("name", heroInfo.getName(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("sex", heroInfo.getSex(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("uuid", heroInfo.getUuid(), XContentBuilder.FieldCaseConversion.NONE);

            builder.endObject();

        }
        builder.endObject();

        return builder;
    }
}
