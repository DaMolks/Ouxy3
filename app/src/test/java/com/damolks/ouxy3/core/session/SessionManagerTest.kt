package com.damolks.ouxy3.core.session

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SessionManagerTest {
    private lateinit var context: Context
    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sessionManager: SessionManager

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        prefs = mockk(relaxed = true)
        editor = mockk(relaxed = true)

        every { context.getSharedPreferences(any(), any()) } returns prefs
        every { prefs.edit() } returns editor
        every { editor.putBoolean(any(), any()) } returns editor
        every { editor.putLong(any(), any()) } returns editor

        sessionManager = SessionManager(context)
    }

    @Test
    fun `isOnboardingCompleted returns false by default`() {
        every { prefs.getBoolean(any(), any()) } returns false
        assertFalse(sessionManager.isOnboardingCompleted())
    }

    @Test
    fun `setOnboardingCompleted sets flag to true`() {
        sessionManager.setOnboardingCompleted()
        verify { editor.putBoolean("onboarding_completed", true) }
    }

    @Test
    fun `getTechnicianId returns null when not set`() {
        every { prefs.getLong(any(), any()) } returns -1L
        assertNull(sessionManager.getTechnicianId())
    }

    @Test
    fun `setTechnicianId stores id`() {
        val id = 123L
        sessionManager.setTechnicianId(id)
        verify { editor.putLong("technician_id", id) }
    }

    @Test
    fun `clearSession clears all preferences`() {
        sessionManager.clearSession()
        verify { editor.clear() }
    }
}