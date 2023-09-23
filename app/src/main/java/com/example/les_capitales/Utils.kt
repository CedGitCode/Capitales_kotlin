package com.example.les_capitales

import android.content.Context
import com.example.les_capitales.databinding.ActivityMainBinding
import java.io.InputStream

class Utils() {

    companion object {
        fun getJsonFromAssets(context: Context, fileName: String): String? {
            var input: InputStream? = null
            var jsonString: String

            try {
                input = context.assets.open("capital.json")
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