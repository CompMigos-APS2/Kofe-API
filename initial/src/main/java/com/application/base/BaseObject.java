package com.application.base;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class BaseObject {
    @Id
    public UUID id;
    public BaseObject(){
        id = UUID.randomUUID();
    }
}
