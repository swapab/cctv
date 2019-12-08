package com.swapab.cctv

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.toJsonString(): String {
    val mapper = ObjectMapper()
    return mapper.writeValueAsString(this)
}