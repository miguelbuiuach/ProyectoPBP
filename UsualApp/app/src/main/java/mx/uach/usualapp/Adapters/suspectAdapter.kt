package mx.uach.usualapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uach.usualapp.R
import mx.uach.usualapp.Suspect_Activity
import mx.uach.usualapp.models.suspect

//this is the adapter to add the suspects from the json to the app

class suspectAdapter(val suspects: List<suspect>) : RecyclerView.Adapter<suspectAdapter.ViewHolder>(){
    inner class ViewHolder(listItem: View) : RecyclerView.ViewHolder(listItem){
        val tvSus : TextView = itemView.findViewById(R.id.TVSus)
        val btnSus : Button = itemView.findViewById(R.id.btnSus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val suspectView = inflater.inflate(R.layout.item_suspect, parent, false)
        return ViewHolder(suspectView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sus : suspect = suspects.get(position)
        holder.tvSus.text = sus.name
        holder.btnSus.setOnClickListener(View.OnClickListener {
           /*val intent = Intent(this, Suspect_Activity::class.java)
            intent.putExtra("SUSPECT", "HEWWO")
            */
        })
        //holder.tvSusl.text = sus.lastname
    }

    override fun getItemCount(): Int {
        return suspects.size
    }
}