package intro.android.projetocm.ui.gestores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import intro.android.projetocm.databinding.FragmentGestoresBinding

class GestoresFragment : Fragment() {

    private lateinit var binding: FragmentGestoresBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGestoresBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
}