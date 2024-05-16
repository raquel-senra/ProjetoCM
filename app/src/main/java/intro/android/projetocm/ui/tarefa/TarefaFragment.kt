package intro.android.projetocm.ui.tarefa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import intro.android.projetocm.databinding.FragmentTarefaBinding


class TarefaFragment : Fragment() {

    private lateinit var binding: FragmentTarefaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTarefaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }


}