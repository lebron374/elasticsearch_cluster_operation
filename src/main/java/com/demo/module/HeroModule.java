package com.demo.module;

import com.demo.rest.RestHeroAction;
import org.elasticsearch.common.inject.AbstractModule;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RestHeroAction.class).asEagerSingleton();
    }
}
