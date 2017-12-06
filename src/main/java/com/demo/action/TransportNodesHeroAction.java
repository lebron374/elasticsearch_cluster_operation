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
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
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

        final List<HeroInfo> nodesInfos = new ArrayList<>();
        for (int i=0; i<nodesResponses.length(); i++) {
            Object resp = nodesResponses.get(i);
            if (resp instanceof HeroInfo) {
                nodesInfos.add((HeroInfo) resp);
            }
        }

        return new HeroResponse(clusterName, nodesInfos.toArray(new HeroInfo[nodesInfos.size()]));
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

    @Override
    protected HeroInfo nodeOperation(HeroOperationRequest request) throws ElasticsearchException {
        String localIp = null;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            localIp = "0.0.0.0";
        }

        return new HeroInfo(localIp, clusterService.localNode());
    }

    @Override
    protected boolean accumulateExceptions() {
        return false;
    }

    static class HeroOperationRequest extends NodeOperationRequest {
        private HeroOperationRequest() {

        }

        private HeroOperationRequest(String nodeId, HeroRequest request) {
            super(request, nodeId);
        }

        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
        }
    }
}
