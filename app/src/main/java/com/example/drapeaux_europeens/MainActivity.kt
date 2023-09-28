package com.example.drapeaux_europeens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.drapeaux_europeens.databinding.ActivityMainBinding
import com.example.drapeaux_europeens.models.CountrysObjectManagement

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countryObjectManagement = CountrysObjectManagement(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableQuizz()

        // [EVENT] lorsque l'utilisateur clique sur le bouton valider
        binding.validateButton.setOnClickListener {
            onClickValidButton()
        }
    }

    // A pour but de récupérer le fichier assets/country_capitals.json pour l'envoyer au countryObjectManagement
    private fun initializeResources() {

        val jsonString = Utils.getJsonFromAssets(this, "country_capitals.json")

        countryObjectManagement.transformJsonStringIntoArray(jsonString)
        val displayingAmountOfCountry = "0/${countryObjectManagement.getNumberOfCountrys()}"

        changeView.updateCounterOnScreen(binding, displayingAmountOfCountry)
    }


    private fun enableQuizz()
    {
        initializeResources()
        changeView.displayFlagInTextView(binding, countryObjectManagement.ArrayOfCountryObject[0].flag)
    }

    private fun showAlertDialogEndOfQuizz()
    {
        var alertDialog = AlertDialog.Builder(this).setCancelable(false)
        alertDialog.apply {
            setTitle("Félicitations")

            setMessage("Vous venez de finir le quiz, appuyez sur oui pour recommencer !")
            setPositiveButton("Oui") { _,_ ->
                enableQuizz()
            }
        }.create().show()
    }

    /*
       Fonction qui va permettre de passer à la prochaine "image"
    */
    private fun onClickValidButton() {

        val valueInputPlayer = binding.guessCountryName.text.toString().trim()
        var messageToShowInToast = getString(R.string.badAnswer)

        if (valueInputPlayer.isEmpty()) return

        if (countryObjectManagement.ArrayOfCountryObject[0].isEqualToMyName(valueInputPlayer)) {

            // On met à jour notre liste de pays [On augmente le compteur + on supprimer l'objet qui vient d'être trouvé]
            countryObjectManagement.increaseAmountOfGoodAnswers()
            countryObjectManagement.deleteCountryAlreadyUsed()

            // Si le tableau de pays est vide, alors le quizz est terminé
            if (countryObjectManagement.ArrayOfCountryObject.isEmpty()){
                showAlertDialogEndOfQuizz()
                return
            }

            // On ajoute un élément visuel pour informer l'utilisateur
            messageToShowInToast = getString(R.string.goodAnswer)

            // On change des informations visuelles
            changeView.displayFlagInTextView(binding, countryObjectManagement.ArrayOfCountryObject[0].flag)

            val displayingAmountOfCountry = "${countryObjectManagement.getAmountOfGoodAnswers()}/${countryObjectManagement.getNumberOfCountrys()}"
            changeView.updateCounterOnScreen(binding, displayingAmountOfCountry)
        }
        changeView.applyToastOnScreen(this, messageToShowInToast)
    }
}