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
public class HeroNodesResponse extends NodesOperationResponse<HeroNodeResponse> implements ToXContent {

    HeroNodesResponse() {
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        nodes = new HeroNodeResponse[in.readVInt()];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = HeroNodeResponse.readHeroInfo(in);
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeVInt(nodes.length);
        for (HeroNodeResponse node : nodes) {
            node.writeTo(out);
        }
    }

    public HeroNodesResponse(ClusterName clusterName, HeroNodeResponse[] nodes) {
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
        for (HeroNodeResponse heroNodeResponseInfo : nodes) {

            builder.startObject(heroNodeResponseInfo.getNode().getId(), XContentBuilder.FieldCaseConversion.NONE);

            builder.field("localIp", heroNodeResponseInfo.getLocalIp(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("name", heroNodeResponseInfo.getName(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("sex", heroNodeResponseInfo.getSex(), XContentBuilder.FieldCaseConversion.NONE);
            builder.field("uuid", heroNodeResponseInfo.getUuid(), XContentBuilder.FieldCaseConversion.NONE);

            builder.endObject();

        }
        builder.endObject();

        return builder;
    }
}
