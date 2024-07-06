package intro.android.projetocm.ui.tarefa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentNovaTarefaBinding
import intro.android.projetocm.databinding.FragmentRegistoBinding
import java.util.UUID


class NovaTarefaFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentNovaTarefaBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNovaTarefaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        auth = Firebase.auth

        binding.btnGuardar.setOnClickListener {
            val idTarefa = UUID.randomUUID().toString()
            val email = auth.currentUser?.email
            val tarefaMap = hashMapOf(
                "id" to idTarefa,
                "email" to email,
                "projeto" to binding.nomeProjeto.text.toString(),
                "descricao" to binding.infoTarefa.text.toString()
            )

            if (email != null) {
                db.collection("tarefas").document(idTarefa)
                    .set(tarefaMap).addOnCompleteListener {
                        Log.d("db", "Tarefa salva com sucesso!")

                        navController.navigate(R.id.action_nav_nova_tarefa_to_nav_tarefa)
                    }.addOnFailureListener {
                    }
            }

        }
    }
    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
}