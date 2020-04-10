package com.uw.service;

public interface UwService<E, M> {
    E getEntity();
    M getModel();
}
