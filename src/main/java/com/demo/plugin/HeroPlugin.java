package com.demo.plugin;

import com.demo.action.HeroAction;
import com.demo.action.TransportNodesHeroAction;
import com.demo.module.HeroModule;
import com.google.common.collect.Lists;
import org.elasticsearch.action.ActionModule;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.rest.RestModule;
import org.elasticsearch.script.ScriptModule;
import org.elasticsearch.search.highlight.HighlightModule;

import java.util.Collection;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroPlugin extends AbstractPlugin {

    public String name() {
        return "hero";
    }

    public String description() {
        return "hero";
    }

    /**
     * onModule接口在InternalNode.setup()接口中初始化
     * onModule接口在InternalNode.start中的injector = modules.createInjector();进行初始化
     * @param module
     */
    public void onModule(ActionModule module) {
        module.registerAction(HeroAction.INSTANCE, TransportNodesHeroAction.class);
    }

    public void onModule(RestModule module) {
    }

    public void onModule(ScriptModule module) {

    }

    public void onModule(HighlightModule module) {

    }

    /**
     * onModule接口在InternalNode的start进行启动
     * for (Class<? extends LifecycleComponent> plugin : pluginsService.services()) {
     * injector.getInstance(plugin).start();
     * }
     * @return
     */
    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        return super.services();
    }

    /**
     * modules接口在InternalNode的setup的modules.add(new PluginsModule(settings, pluginsService))加载
     * @return
     */
    @Override
    public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        modules.add(HeroModule.class);

        return modules;

    }
}
