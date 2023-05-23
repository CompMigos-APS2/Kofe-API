//package com.application.base;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class BaseState<T extends BaseObject> {
//    private BaseCache cache;
//    public BaseState(){
//        this.cache = new BaseCache<T>();
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss z");
//        Date date = new Date(System.currentTimeMillis());
//
//        System.out.println(formatter.format(date) + " " + "Initializing " + this.getClass().getSimpleName());
//    }
//
//    public Collection<T> GetAll(){
//        return cache.GetAll();
//    }
//    public boolean TryInsert(T obj){
//        return cache.TryInsert(obj);
//    }
//
//    public T Get(UUID id){
//        return (T) cache.Get(id);
//    }
//}
