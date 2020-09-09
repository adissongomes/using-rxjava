package com.adissongomes.services;

import com.adissongomes.services.dto.Info;

import java.util.Random;

public class InfoService extends Service<Info> {

    private static InfoService INSTANCE;

    private final Random random = new Random();

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
        return new Info(id, "INFO " + id);
    }

}
