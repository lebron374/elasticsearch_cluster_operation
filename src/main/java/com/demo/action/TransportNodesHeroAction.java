package com.demo.action;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.nodes.NodeOperationRequest;
import org.elasticsearch.action.support.nodes.TransportNodesOperationAction;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 在cluster模式下真正的执行部分
 * Created by zhi.wang on 2017/12/6.
 */
public class TransportNodesHeroAction extends TransportNodesOperationAction<HeroRequest, HeroResponse, TransportNodesHeroAction.HeroOperationRequest, HeroInfo> {


    @Inject
    public TransportNodesHeroAction(Settings settings, ClusterName clusterName, ThreadPool threadPool,
                                    ClusterService clusterService, TransportService transportService, ActionFilters actionFilters) {
        super(settings, HeroAction.NAME, clusterName, threadPool, clusterService, transportService, actionFilters);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.MANAGEMENT;
    }

    @Override
    protected HeroRequest newRequest() {
        return new HeroRequest();
    }

    @Override
    protected HeroResponse newResponse(HeroRequest request, AtomicReferenceArray nodesResponses) {

        final List<HeroInfo> heroInfos = new ArrayList<>();
        for (int i=0; i<nodesResponses.length(); i++) {
            Object resp = nodesResponses.get(i);
            if (resp instanceof HeroInfo) {
                heroInfos.add((HeroInfo) resp);
            }
        }

        return new HeroResponse(clusterName, heroInfos.toArray(new HeroInfo[heroInfos.size()]));
    }

    @Override
    protected HeroOperationRequest newNodeRequest() {
        return new HeroOperationRequest();
    }

    @Override
    protected HeroOperationRequest newNodeRequest(String nodeId, HeroRequest request) {
        return new HeroOperationRequest(nodeId, request);
    }

    @Override
    protected HeroInfo newNodeResponse() {
        return new HeroInfo();
    }

    /**
     * 执行节点真正执行任务的位置,真正发起执行在父类当中，有点模板类的执行模式
     * @param request
     * @return
     * @throws ElasticsearchException
     */
    @Override
    protected HeroInfo nodeOperation(HeroOperationRequest request) throws ElasticsearchException {
        String localIp = null;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            localIp = "0.0.0.0";
        }

        String uuid = UUID.randomUUID().toString();

        return new HeroInfo(localIp, request.getRequest().getName(), request.getRequest().getSex(), uuid,  clusterService.localNode());
    }

    @Override
    protected boolean accumulateExceptions() {
        return false;
    }

    /**
     * 内部类，具体作用不明确，目前看只是针对HeroRequest进行了一层封装而已
     */
    static class HeroOperationRequest extends NodeOperationRequest {
        private HeroRequest request;

        private HeroOperationRequest() {

        }

        private HeroOperationRequest(String nodeId, HeroRequest request) {
            super(request, nodeId);

            this.request = request;
        }

        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
        }

        public HeroRequest getRequest() {
            return this.request;
        }
    }
}
