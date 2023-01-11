package com.actin.app40Grados
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app40grados.R
import app40grados.databinding.ItemClaseReservadaBinding
import com.actin.app40Grados.model.clasesReservadas

class  clasesReservadasAdapter(private val listaClases: MutableList<clasesReservadas>?, private val onClickListener: (clasesReservadas) -> Unit): RecyclerView.Adapter<clasesReservadasAdapter.clasesReservadasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): clasesReservadasViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_clase_reservada, parent, false)
        return clasesReservadasViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: clasesReservadasViewHolder, position: Int) {
        val currentItem = listaClases?.get(position)
        currentItem?.let { holder.render(it, onClickListener) }

    }

    override fun getItemCount(): Int {
        return listaClases?.size ?: 0

    }

    class clasesReservadasViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemClaseReservadaBinding.bind(itemView)

        fun render(Clase : clasesReservadas, onClickListener: (clasesReservadas) -> Unit) {
            binding.fechaClaseTvR.text = Clase.fecha
            binding.horaClaseTvR.text = Clase.hora
            binding.imagenMaestroR.setImageResource(Clase.fotoMaestro)
            binding.nombreClaseTvR.text = Clase.tipoDeClase
            binding.nombreMaestroTvR.text = Clase.maestro
            itemView.setOnClickListener{ onClickListener(Clase)}
        }
    }

}