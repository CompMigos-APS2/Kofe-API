package com.application.services;

import com.application.base.BaseCache;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersistenceManager {
    private Map<String, BaseCache> registeredCaches;
    public PersistenceManager(){
        this.registeredCaches = new ConcurrentHashMap<>();
    }

    public void Register(String key, BaseCache cache){
        if(registeredCaches.containsKey(key)) return;
        registeredCaches.put(key, cache);
    }
    @Async
    public void Persistence1() throws InterruptedException {

        System.out.println("initialized");
        System.out.println(this.registeredCaches.keySet());
        Thread.sleep(1000);

    }
}
