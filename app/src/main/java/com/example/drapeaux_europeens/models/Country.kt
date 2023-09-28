package com.example.drapeaux_europeens.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Country (
    val name: String,
    val flag: String,
) {

    fun isEqualToMyName(pvalue : String): Boolean{
        return pvalue == name
    }
}

class CountrysObjectManagement(
    private var totalOfGoodAnswers : Int
) {

    lateinit var ArrayOfCountryObject : MutableList<Country> ; private set

    private var numberOfCountrys : Int = 0

    fun transformJsonStringIntoArray(jsonString: String?) {

        val gson = Gson()

        val listCountryType = object : TypeToken<List<Country>>() {}.type

        ArrayOfCountryObject = gson.fromJson(jsonString, listCountryType)

        numberOfCountrys = ArrayOfCountryObject.size

        shuffleNumericalIDOfCountry()
        totalOfGoodAnswers = 0
    }


    fun getNumberOfCountrys() : Int {
        return numberOfCountrys
    }

    // Permet de supprimer l'objet pays qui vient d'être trouvé par l'utilisateur.
    fun deleteCountryAlreadyUsed()
    {
        ArrayOfCountryObject.removeAt(0)
    }

    // Permet de mélanger notre tableau de pays
    private fun shuffleNumericalIDOfCountry() {
        ArrayOfCountryObject.shuffle()
    }

    // Permet de gérer l'augmentation du conteur qui est affiché sous le drapeau
    fun increaseAmountOfGoodAnswers()
    {
        totalOfGoodAnswers++
    }

    fun getAmountOfGoodAnswers() : Int
    {
        return totalOfGoodAnswers
    }
}