package com.revolut.moneytransfer.web.facade.convertors;

public interface Converter<SOURCE, TARGET>{

    TARGET convert(SOURCE var1);
}