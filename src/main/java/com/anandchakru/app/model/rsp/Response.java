package com.anandchakru.app.model.rsp;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@c")
public interface Response extends Serializable {
}
