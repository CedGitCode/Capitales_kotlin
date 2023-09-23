package com.example.les_capitales

import android.content.Context
import android.widget.Toast
import com.example.les_capitales.databinding.ActivityMainBinding

class changeView {

    companion object {

        // Va nous permettre d'afficher un drapeau sur l'application en fonction de l'id reçu
        fun displayFlagInTextView(pBinding: ActivityMainBinding, pFlag : String)
        {
            pBinding.displayCountryFlag.text = pFlag
        }

        // Va nous permettre de modifier des éléments graphiques sur l'application
        fun updateCounterOnScreen(pBinding: ActivityMainBinding, ptotalGoodAnswers: String) {

            // Mise à jour visuel de l'application
            pBinding.displayGoodAnswers.text = ptotalGoodAnswers
            pBinding.guessCountryName.setText("")
        }

        fun applyToastOnScreen(p_context : Context, pHint : String)
        {
            Toast.makeText(p_context, pHint, Toast.LENGTH_LONG).show()
        }
    }
}