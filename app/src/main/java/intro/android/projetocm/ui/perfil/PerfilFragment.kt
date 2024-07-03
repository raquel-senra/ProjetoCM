package intro.android.projetocm.ui.perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentPerfilBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        auth = Firebase.auth

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(R.id.action_perfilFragment_to_nav_login)
        }

        if (auth.currentUser != null) {
            val email = auth.currentUser?.email
            binding.textEmailUser.text = email

            if (email != null) {
                db.collection("users").document(email)
                    .addSnapshotListener { documento, error ->
                        if (documento != null) {
                            val nome = documento.getString("nome")
                            binding.textNomeUser.text = nome

                        }
                    }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
}