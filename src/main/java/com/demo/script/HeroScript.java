package com.demo.script;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractDoubleSearchScript;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Map;

public class HeroScript implements NativeScriptFactory {

    public static final String SCRIPT_NAME = "hero_script";
    public Map<String, Object> localParams; // 在针对每个doc执行算分的时候可以访问传递下来的参数

    /**
     * 创建执行脚本对象，在一次query当中只会生成一个对象
     * @param params score query 传下来的参数
     * @return
     */
    @Override
    public ExecutableScript newScript(@Nullable Map<String, Object> params) {
        localParams = params;

        return new HeroActionScript();
    }

    /**
     * 实现打分的逻辑类
     */
    private class HeroActionScript extends AbstractDoubleSearchScript {

        /**
         * 真正执行打分部分的逻辑，可以获取doc的信息
         * @return 算法
         */
        @Override
        public double runAsDouble() {
            //执行一些打分逻辑,打分的时候可以获取传递下来的参数，可以获取doc文档的field字段以及更多，可以通过doc()跟进查看源码
            System.out.println(localParams.toString());
            System.out.println(this.doc().toString());
            return 0;
        }
    }
}
