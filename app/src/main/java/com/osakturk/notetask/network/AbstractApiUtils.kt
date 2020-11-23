package com.osakturk.notetask.network

abstract class AbstractApiUtils : ApiUtilsInterface{
    protected var apiService: ApiService
    protected var customCallbackSender: CustomCallbackSender

    constructor(apiService: ApiService, customCallbackSender: CustomCallbackSender) {
        this.apiService= apiService
        this.customCallbackSender = customCallbackSender
    }
}