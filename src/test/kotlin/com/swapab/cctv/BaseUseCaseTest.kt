package com.swapab.cctv

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings

@ExtendWith(MockitoExtension::class)
@MockitoSettings
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
interface BaseUseCaseTest