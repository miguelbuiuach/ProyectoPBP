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
import mx.uach.usualapp.models.Team
import mx.uach.usualapp.models.suspect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this variable was not getting recognized when we referenced it later on, left here as an
        //example to do stupid stuff it it means it will work

        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/miguelbuiuach/ProyectoPBP/master/server/about/about.json"

        val sus = findViewById<RecyclerView>(R.id.rvSuspects)

        val jsonRequest : StringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response->
            //Log.i("JSON", response.toString())
            val gson = Gson()
            val team : Team = gson.fromJson(response.toString(), Team::class.java)
            val suspects : List<suspect> = team.equipo
            sus.adapter = suspectAdapter(suspects)
            sus.layoutManager = LinearLayoutManager(this)

            Log.i("SUSPECTS", suspects.toString())

        }, Response.ErrorListener {  error ->
            Log.e("JSON", "Error en petición ${error.toString()}")
        })

        queue.add(jsonRequest)
    }
}