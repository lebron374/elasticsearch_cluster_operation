package com.demo.plugin;

import com.demo.action.HeroAction;
import com.demo.action.TransportNodesHeroAction;
import com.demo.module.HeroModule;
import com.demo.rest.RestHeroAction;
import com.google.common.collect.Lists;
import org.elasticsearch.action.ActionModule;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.rest.RestModule;
import org.elasticsearch.script.ScriptModule;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HerePlugin extends AbstractPlugin {

    public String name() {
        return "hero";
    }

    public String description() {
        return "hero";
    }

    public void onModule(ActionModule module) {
        module.registerAction(HeroAction.INSTANCE, TransportNodesHeroAction.class);
    }

//    public void onModule(RestModule module) {
//        module.addRestAction(RestHeroAction.class);
//    }

    public void onModule(ScriptModule module) {

    }

    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        return super.services();
    }

    @Override
    public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        modules.add(HeroModule.class);

        return modules;

    }
}
