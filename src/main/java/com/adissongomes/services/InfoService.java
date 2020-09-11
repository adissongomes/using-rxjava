package com.adissongomes.services;

import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.core.BackpressureStrategy;

import java.util.Random;

public class InfoService extends Service<Info> {

    private static InfoService INSTANCE;

    private final Random random = new Random();

    public InfoService() {
        super(BackpressureStrategy.ERROR);
    }

    public static InfoService create() {
        if (INSTANCE == null)
            INSTANCE = new InfoService();
        return INSTANCE;
    }

    @Override
    public int getSleepSecondsTimeout() {
        return 1;
    }

    @Override
    protected Info get() {
        int id = random.nextInt(10000);
        System.out.println("gen " + id);
        return new Info(id, "INFO " + id);
    }

}
