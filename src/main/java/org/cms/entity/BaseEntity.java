package org.cms.entity;

import java.util.UUID;

public abstract class BaseEntity {

    protected final String id;

    public String getId() {
        return id;
    }

    protected BaseEntity(String id) {
        this.id = id;
    }

    protected BaseEntity() {
        this.id = UUID.randomUUID().toString();

    }

}
