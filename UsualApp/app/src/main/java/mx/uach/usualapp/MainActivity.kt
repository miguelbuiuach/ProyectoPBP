package mx.uach.usualapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvSuspect = findViewById<RecyclerView>(R.id.rvSuspects)

        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/miguelbuiuach/ProyectoPBP/master/server/about/about.json"

        val jsonRequest : StringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response->
            Log.i("JSON", response.toString())
        }, Response.ErrorListener {  error ->
            Log.e("JSON", "Error en petición ${error.toString()}")
        })

        queue.add(jsonRequest)
    }
}