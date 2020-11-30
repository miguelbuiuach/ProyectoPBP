package mx.uach.usualapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.uach.usualapp.Adapters.suspectAdapter
import mx.uach.usualapp.models.suspect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this variable was not getting recognized when we referenced it later on, left here as an
        //example to do stupid stuff it it means it will work
        val sus = findViewById<RecyclerView>(R.id.rvSuspects)

        val queue = Volley.newRequestQueue(this)
        val url = "https://pastebin.com/raw/nfAYJYfu"


        val jsonRequest : StringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response->
            //Log.i("JSON", response.toString())
            val gson = Gson()
            val itemType = object : TypeToken<List<suspect>>(){}.type
            val suspects : List<suspect> = gson.fromJson(response.toString(), itemType)
            //referenced this directly because it didn work otherwise
            findViewById<RecyclerView>(R.id.rvSuspects).adapter = suspectAdapter(suspects)
            findViewById<RecyclerView>(R.id.rvSuspects).layoutManager = LinearLayoutManager(this)

            Log.i("SUSPECTS", suspects.toString())

        }, Response.ErrorListener {  error ->
            Log.e("JSON", "Error en petición ${error.toString()}")
        })

        queue.add(jsonRequest)
    }
}