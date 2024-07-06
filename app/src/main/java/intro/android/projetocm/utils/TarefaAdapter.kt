package intro.android.projetocm.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import intro.android.projetocm.databinding.TarefaCardBinding

class TarefaAdapter(private val list: MutableList<String>) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    inner class TarefaViewHolder(val binding: TarefaCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val binding = TarefaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TarefaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.descricaoTarefa.text = list[position]
            }
        }
    }

}