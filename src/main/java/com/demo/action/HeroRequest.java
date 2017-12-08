package com.demo.action;

import org.elasticsearch.action.support.nodes.NodesOperationRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;

/**
 * 负责在cluster模式下整个集群的request数据
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroRequest extends NodesOperationRequest<HeroRequest> {

    private String name;
    private String sex;

    public HeroRequest setName(String name) {
        this.name = name;

        return this;
    }

    public HeroRequest setSex(String sex) {
        this.sex = sex;

        return this;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    /**
     * 用于执行节点接收请求参数
     * @param in
     * @throws IOException
     */
    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        this.name = in.readString();
        this.sex = in.readString();
    }

    /**
     * 用于协调节点发送请求参数
     * @param out
     * @throws IOException
     */
    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);

        out.writeString(this.name);
        out.writeString(this.sex);
    }
}
