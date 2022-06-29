package com.silva021.estacionaa.domain.usecase

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class ValidateTransitBoardTest {
    private val validateTransitBoard by lazy { ValidateTransitBoard() }

    @Test
    fun `Given board is valid then result is true`() {
        val result = validateTransitBoard("axs-1234")
        assertTrue(result)
    }

    @Test
    fun `Given board is not valid then result is true`() {
        val result = validateTransitBoard("axss1234")
        assertFalse(result)
    }
}