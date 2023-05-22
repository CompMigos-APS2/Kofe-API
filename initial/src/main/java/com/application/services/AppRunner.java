package com.application.services;

import com.application.services.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final PersistenceManager persistenceManager;

    public AppRunner(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing services");
        long start = System.currentTimeMillis();

        persistenceManager.Persistence1();

        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));


    }

}