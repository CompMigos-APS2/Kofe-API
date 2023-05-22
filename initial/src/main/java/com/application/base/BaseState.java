package com.application.base;

import com.application.services.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;

public class BaseState<T extends BaseObject> {
    private BaseCache cache;
    private PersistenceManager persistenceManager;
    @Autowired
    public BaseState(PersistenceManager persistenceManager){
        this.cache = new BaseCache<T>();
        this.persistenceManager = persistenceManager;

        persistenceManager.Register(this.getClass().getSimpleName().toString(), this.cache);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        System.out.println(formatter.format(date) + " " + "Initializing " + this.getClass().getSimpleName());
    }

    public Collection<T> GetAll(){
        return cache.GetAll();
    }
    public boolean TryInsert(T obj){
        return cache.TryInsert(obj);
    }

    public T Get(UUID id){
        return (T) cache.Get(id);
    }
}
