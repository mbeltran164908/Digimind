package beltran.miguel.digiming.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import beltran.miguel.digiming.Actividad
import beltran.miguel.digiming.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var adaptadorActividad:AdaptadorActividad?=null

    companion object{
        var actividades=ArrayList<Actividad>()
        var first=true
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        if(first){
            cargarActividades()
            first=false
        }
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        adaptadorActividad=AdaptadorActividad(root.context,actividades)
        root.gridViewActividades.adapter=adaptadorActividad
        return root
    }

    fun cargarActividades(){
        actividades.add(Actividad("Practice 1", arrayListOf("Tuesday"),"17:30"))
        actividades.add(Actividad("Practice 2", arrayListOf("Monday","Sunday"),"17:00"))
        actividades.add(Actividad("Practice 3", arrayListOf("Wednesday"),"14:00"))
        actividades.add(Actividad("Practice 4", arrayListOf("Saturday"),"11:00"))
        actividades.add(Actividad("Practice 5", arrayListOf("Friday"),"13:00"))
        actividades.add(Actividad("Practice 6", arrayListOf("Thursday"),"10:40"))
        actividades.add(Actividad("Practice 7", arrayListOf("Monday"),"12:00"))
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
            var vista=inflator.inflate(R.layout.task_view,null)
            vista.tx_titulo.setText(actividad.titulo)
            vista.tx_days.setText(actividad.days.toString())
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
