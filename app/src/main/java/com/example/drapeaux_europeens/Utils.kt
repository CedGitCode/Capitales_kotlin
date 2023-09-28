package com.example.drapeaux_europeens

import android.content.Context
import java.io.InputStream

class Utils() {

    companion object {
        fun getJsonFromAssets(context: Context, fileName: String): String? {
            var input: InputStream? = null
            var jsonString: String

            try {
                input = context.assets.open("country_capitals.json")
                val size = input.available()

                val buffer = ByteArray(size)

                input.read(buffer)

                jsonString = String(buffer)
                return jsonString;

            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                input?.close()
            }
            return null
        }
    }
}