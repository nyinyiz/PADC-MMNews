package com.nyinyi.nw.sfc.dagger;

import com.nyinyi.nw.sfc.network.MMNewsDataAgent;
import com.nyinyi.nw.sfc.network.MMNewsDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/7/18.
 */


@Module
public class NetworkModule {

    @Provides
    @Singleton // obj copy ta ku pl shi chin lo singleton use tr
    public MMNewsDataAgent provideMMNewsDataAgent() {
        return new MMNewsDataAgentImpl();
    }

}
