package com.example.myapplicationjacoco

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class JacocoInstrumentation : Instrumentation() {

    private val TAG = "JacocoInstrumentation"
    private var coverageFilePath: String? = null

    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)

        // Définit le chemin du fichier de couverture
        val coverageFile = arguments.getString("coverageFile") ?: "coverage.ec"
        coverageFilePath = File(targetContext.getExternalFilesDir(null), coverageFile).absolutePath
        Log.d(TAG, "Coverage file path set to: $coverageFilePath")

        start()
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "Starting MainActivity...")

        // Intent pour démarrer MainActivity
        val intent = Intent(targetContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        // Démarrer MainActivity
        startActivitySync(intent)

        Log.d(TAG, "MainActivity started")
    }

    override fun onDestroy() {
        super.onDestroy()
        generateCoverageReport()
    }

    private fun generateCoverageReport() {
        try {
            val agentClass = Class.forName("org.jacoco.agent.rt.RT")
            val agent = agentClass.getMethod("getAgent").invoke(null)

            val executionData = agentClass.getMethod("getExecutionData", Boolean::class.java)
                .invoke(agent, false) as ByteArray

            val coverageFile = File(coverageFilePath)
            FileOutputStream(coverageFile).use { it.write(executionData) }

            Log.d(TAG, "Coverage data saved to: ${coverageFile.absolutePath}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to generate coverage report", e)
        }
    }
}

