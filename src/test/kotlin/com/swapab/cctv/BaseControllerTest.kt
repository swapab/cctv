package com.swapab.cctv

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
abstract class BaseControllerTest<C> {
    protected abstract var subject: C

    @Autowired
    protected lateinit var mockMvc: MockMvc
}
