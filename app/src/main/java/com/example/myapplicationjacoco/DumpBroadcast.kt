package com.example.myapplicationjacoco


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class DumpBroadcast : BroadcastReceiver() {

    private val TAG = "DumpBroadcast"

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "STOP_COVERAGE") {
            Log.d(TAG, "Received STOP_COVERAGE broadcast")
            dumpCoverageData(context)

            // Arrête le processus après avoir dumpé les données
            Log.d(TAG, "Stopping process")
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    private fun dumpCoverageData(context: Context) {
        try {
            // Vérifie si la classe JaCoCo RT existe
            val rtClass = Class.forName("org.jacoco.agent.rt.RT")
            Log.d(TAG, "JaCoCo RT class found: ${rtClass.name}")

            val agent = rtClass.getMethod("getAgent").invoke(null)

            // Génère le fichier de couverture
            val coverageFile = File(context.getExternalFilesDir(null), "coverage.ec")
            val executionData = agent.javaClass.getMethod("getExecutionData", Boolean::class.java)
                .invoke(agent, false) as ByteArray

            // Écrit les données dans le fichier
            FileOutputStream(coverageFile).use { it.write(executionData) }
            Log.d(TAG, "Coverage data dumped to ${coverageFile.absolutePath}")
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "JaCoCo RT class not found. Ensure the agent is included in the APK.", e)
        } catch (e: IllegalStateException) {
            Log.e(TAG, "JaCoCo agent is not started. Check if the agent is properly initialized.", e)
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error while dumping JaCoCo coverage data", e)
        }
    }
}
