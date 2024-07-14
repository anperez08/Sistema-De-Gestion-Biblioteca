package com.uasd.sgb.utils;

@FunctionalInterface
public interface TransformFrom<F, T> {
    T from(F f);
}

