package com.demo.action;

import org.elasticsearch.action.support.nodes.NodeOperationResponse;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroInfo extends NodeOperationResponse {
    private String localIp;

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    HeroInfo() {

    }

    public HeroInfo(DiscoveryNode node) {
        super(node);
    }

    public HeroInfo(String localIp, DiscoveryNode node) {
        super(node);
        this.localIp = localIp;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);

        this.localIp = in.readString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(this.localIp);
    }

    public static HeroInfo readHeroInfo(StreamInput in) throws IOException {
        HeroInfo heroInfo = new HeroInfo();
        heroInfo.readFrom(in);

        return heroInfo;
    }
}
