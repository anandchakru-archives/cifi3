package com.anandchakru.app.model.fault;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@c")
public interface Fault extends Serializable {
}
