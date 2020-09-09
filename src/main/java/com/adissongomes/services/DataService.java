package com.adissongomes.services;

import com.adissongomes.services.dto.Data;

import java.util.Random;

public class DataService extends Service<Data> {

    private static DataService INSTANCE;
    private final Random random = new Random();

    public static DataService create() {
        if (INSTANCE == null)
            INSTANCE = new DataService();
        return INSTANCE;
    }

    @Override
    protected int getSleepSecondsTimeout() {
        return 4;
    }

    @Override
    protected Data get() {
        int id = random.nextInt(10000);
        return new Data(id, "data " + id);
    }
}
