package com.example.myapplicationjacoco

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputA = findViewById<EditText>(R.id.input_a)
        val inputB = findViewById<EditText>(R.id.input_b)
        val resultText = findViewById<TextView>(R.id.result_text)

        val buttonAdd = findViewById<Button>(R.id.button_add)
        val buttonSubtract = findViewById<Button>(R.id.button_subtract)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonModulo = findViewById<Button>(R.id.button_modulo)
        val buttonPower = findViewById<Button>(R.id.button_power)

        val calculator = Calculator()

        // Gestion des clics pour chaque opération
        buttonAdd.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b -> calculator.add(a, b) }
            checkJacocoAgentExistence()
        }

        buttonSubtract.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b -> calculator.subtract(a, b) }
        }

        buttonMultiply.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b -> calculator.multiply(a, b) }
        }

        buttonDivide.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b ->
                if (b == 0) throw IllegalArgumentException("Division par zéro n'est pas permise et cela est cool!")
                calculator.divide(a, b)
            }
        }

        buttonModulo.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b ->
                if (b == 0) throw IllegalArgumentException("Modulo par zéro n'est pas permis.")
                calculator.modulo(a, b)
            }
        }

        buttonPower.setOnClickListener {
            handleOperation(inputA, inputB, resultText) { a, b -> calculator.power(a, b) }
        }
    }

    fun checkJacocoAgentExistence() {
        try {
            val jacocoAgentClass = Class.forName("org.jacoco.agent.rt.RT")
            Log.i("JacocoCheck", "La classe Jacoco agent existe : ${jacocoAgentClass.name}")

            val getAgentMethod = jacocoAgentClass.getMethod("getAgent")
            Log.i("JacocoCheck", "Méthode trouvée : $getAgentMethod")

            val jacocoAgent = getAgentMethod.invoke(null)
            if (jacocoAgent != null) {
                Log.i("JacocoCheck", "L'agent Jacoco est actif : $jacocoAgent")
            } else {
                Log.e("JacocoCheck", "L'agent Jacoco n'est pas actif.")
            }
        } catch (e: Exception) {
            Log.e("JacocoCheck", "Erreur lors de la vérification de Jacoco agent.", e)
        }
    }

    private fun handleOperation(
        inputA: EditText,
        inputB: EditText,
        resultText: TextView,
        operation: (Int, Int) -> Int
    ) {
        val a = inputA.text.toString().toIntOrNull()
        val b = inputB.text.toString().toIntOrNull()

        if (a == null || b == null) {
            resultText.text = "Veuillez entrer des nombres valides."
            return
        }

        try {
            val result = operation(a, b)
            resultText.text = "Résultat : $result"
        } catch (e: IllegalArgumentException) {
            resultText.text = e.message
        }
    }
}
