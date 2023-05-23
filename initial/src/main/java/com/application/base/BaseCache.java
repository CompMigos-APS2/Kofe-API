//package com.application.base;
//
//import java.util.*;
//
//public class BaseCache<T extends BaseObject> {
//    private Map<UUID, T> cache;
//
//    public BaseCache(){
//        this.cache = new HashMap<UUID, T>() ;
//    }
//    public T Get(UUID id){
//        return cache.get(id);
//    }
//    public void UnsafelyInsert(T obj){
//        cache.put(obj.id, obj);
//    }
//
//    public Collection<T> GetAll(){
//        return this.cache.values();
//    }
//
//    public boolean TryInsert(T obj){
//        if(cache.get(obj.id) == null) return false;
//
//        cache.put(obj.id, obj);
//        return true;
//    }
//}
