package beltran.miguel.digiming.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import beltran.miguel.digiming.Actividad
import beltran.miguel.digiming.R
import kotlinx.android.synthetic.main.elemento_view.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var actividades=ArrayList<Actividad>()
    var adaptadorActividad:AdaptadorActividad?=null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        cargarActividades()
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        adaptadorActividad=AdaptadorActividad(root.context,actividades)
        root.findViewById<GridView>(R.id.gridViewActividades).adapter=adaptadorActividad

        return root
    }

    fun cargarActividades(){
        for(i in 1..9){
            actividades.add(Actividad("Practice","Everyday","17:00"))
        }
    }

    class AdaptadorActividad: BaseAdapter {
        var actividades=ArrayList<Actividad>()
        var context: Context?=null

        constructor(contexto: Context, actividades:ArrayList<Actividad>){
            this.context=contexto
            this.actividades=actividades
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var actividad=actividades[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as  LayoutInflater
            var vista=inflator.inflate(R.layout.elemento_view,null)
            vista.tx_titulo.setText(actividad.titulo)
            vista.tx_desc.setText(actividad.desc)
            vista.tx_hora.setText(actividad.hora)

            return vista
        }

        override fun getItem(position: Int): Any {
            return actividades[position];
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getCount(): Int {
            return actividades.size
        }
    }
}
