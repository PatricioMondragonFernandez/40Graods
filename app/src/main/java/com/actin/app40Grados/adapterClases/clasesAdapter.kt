package com.actin.app40Grados.adapterClases
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app40grados.R
import app40grados.databinding.ItemClaseBinding
import com.actin.app40Grados.model.Clases

class clasesAdapter(private val clasesList: MutableList<Clases>, private val onClickListener:(Clases) -> Unit): RecyclerView.Adapter<clasesAdapter.clasesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): clasesViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_clase, parent, false)
        return clasesViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: clasesViewHolder, position: Int) {
        val currentItem = clasesList[position]
        holder.render(currentItem, onClickListener)



    }

    override fun getItemCount(): Int {

        return clasesList.size
    }


    class clasesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val binding = ItemClaseBinding.bind(itemView)




        fun render(Clase : Clases, onClickListener:(Clases) -> Unit){
            binding.nombreClaseTv.text = Clase.tipoDeClase
            binding.horaClaseTv.text = Clase.hora
            binding.nombreMaestroTv.text = Clase.maestro
            binding.imagenMaestro.setImageResource(Clase.fotoMaestro)
            itemView.setOnClickListener{ onClickListener(Clase)
            }

        }
    }
}

private fun ImageView.setImageResource(fotoMaestro: Drawable) {

}
