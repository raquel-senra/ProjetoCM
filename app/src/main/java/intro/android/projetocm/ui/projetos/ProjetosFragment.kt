package intro.android.projetocm.ui.projetos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import intro.android.projetocm.databinding.FragmentProjetosBinding
import intro.android.projetocm.databinding.FragmentTarefaBinding

class ProjetosFragment : Fragment() {

    private lateinit var binding: FragmentProjetosBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjetosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
}