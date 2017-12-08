package com.demo.action;

import org.elasticsearch.action.support.nodes.NodeOperationResponse;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;

/**
 * 负责在cluster模式下每个节点的的响应
 * 需要实现readFrom/writeTo核心接口，猜测是用于传输序列化的时候使用
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroInfo extends NodeOperationResponse {
    private String localIp;
    private String name;
    private String sex;
    private String uuid;

    public String getLocalIp() {
        return localIp;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getUuid() {
        return uuid;
    }

    HeroInfo() {

    }

    public HeroInfo(String localIp, String name, String sex, String uuid, DiscoveryNode node) {
        super(node);
        this.localIp = localIp;
        this.name = name;
        this.sex = sex;
        this.uuid = uuid;
    }

    /**
     * 猜测协调节点接收数据
     * @param in
     * @throws IOException
     */
    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);

        this.localIp = in.readString();
        this.name = in.readString();
        this.sex = in.readString();
        this.uuid = in.readString();
    }

    /**
     * 测试执行节点发送数据
     * @param out
     * @throws IOException
     */
    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(this.localIp);
        out.writeString(this.name);
        out.writeString(this.sex);
        out.writeString(this.uuid);
    }

    public static HeroInfo readHeroInfo(StreamInput in) throws IOException {
        HeroInfo heroInfo = new HeroInfo();
        heroInfo.readFrom(in);

        return heroInfo;
    }
}
