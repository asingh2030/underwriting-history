/*
package com.uw.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParserService<M, E> {

    private List<M> parseToModel(List<E> list, UwService service) {
        if (list != null) {
            return list.stream().map(EmapToModel()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Object mapToModel(E details, UwService service) {
        Object model = service.getModel();
        BeanUtils.copyProperties(details, model);
        return model;
    }
}
*/
