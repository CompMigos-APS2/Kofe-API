package com.application.base;

import java.util.UUID;

public class BaseObject {
    public UUID internalId;
    public BaseObject(){
        internalId = UUID.randomUUID();
    }
}
