package com.retarded.di;

import java.util.List;
import java.util.stream.Collectors;

class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {
        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }

}