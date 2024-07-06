package intro.android.projetocm.ui.tarefa

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentTarefaBinding
import intro.android.projetocm.utils.TarefaAdapter


class TarefaFragment : Fragment() {
     private lateinit var navController: NavController
     private lateinit var binding: FragmentTarefaBinding
     private lateinit var auth: FirebaseAuth
     private val db = FirebaseFirestore.getInstance()



     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = FragmentTarefaBinding.inflate(inflater, container, false)
          val root: View = binding.root
          return root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          init(view)

          auth = Firebase.auth

          binding.btnNovaTarefa.setOnClickListener {
               navController.navigate(R.id.action_nav_tarefa_to_nav_nova_tarefa)
          }

          if (auth.currentUser != null) {
               val email = auth.currentUser?.email

               if (email != null) {
                    db.collection("tarefas").whereEqualTo("email", email).get()
                         .addOnSuccessListener { documents ->
                              val tarefas = mutableListOf<String>()
                              for (document in documents) {
                                   val descricao = document.getString("descricao")
                                   if (descricao != null) {
                                        tarefas.add(descricao)
                                   }
                              }
                              val adapter = TarefaAdapter(tarefas)
                              binding.rvTarefas.adapter = adapter
                              binding.rvTarefas.layoutManager = LinearLayoutManager(context)
                         }
                         .addOnFailureListener { exception ->
                              Log.w(TAG, "Error getting documents: ", exception)
                         }
               }
          }
     }

     private fun init(view: View) {
          navController = Navigation.findNavController(view)
          auth = FirebaseAuth.getInstance()
     }


}