package com.padcmyanmar.sfcnn.dagger;

import com.padcmyanmar.sfcnn.network.MMNewsDataAgent;
import com.padcmyanmar.sfcnn.network.UITestDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aung on 1/7/18.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MMNewsDataAgent provideMMNewsDataAgent() {
        return new UITestDataAgentImpl();
    }
}
