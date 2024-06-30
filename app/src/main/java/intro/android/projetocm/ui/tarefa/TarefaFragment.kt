package intro.android.projetocm.ui.tarefa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import intro.android.projetocm.databinding.FragmentTarefaBinding


class TarefaFragment : Fragment() {
     private lateinit var navController: NavController
     private lateinit var binding: FragmentTarefaBinding
     private lateinit var auth: FirebaseAuth



     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = FragmentTarefaBinding.inflate(inflater, container, false)
          val root: View = binding.root
          return root
     }

     private fun init(view: View) {
          navController = Navigation.findNavController(view)
          auth = FirebaseAuth.getInstance()
     }


}