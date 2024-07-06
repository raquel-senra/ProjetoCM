package intro.android.projetocm.ui.registo

import android.content.Intent
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
import intro.android.projetocm.MainActivity
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentLoginBinding
import intro.android.projetocm.databinding.FragmentRegistoBinding


class RegistoFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegistoBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        auth = Firebase.auth

        binding.btnRegisto.setOnClickListener {
            val email = binding.idEmail.text.toString()
            val pass = binding.idPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                    registerUser(email, pass)

            } else
                Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    public override fun onStart() {
        super.onStart()


        }

    private fun registerUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful){
                val userMap = hashMapOf(
                    "nome" to binding.idNome.getText().toString().trim(),
                    "email" to binding.idEmail.getText().toString().trim())

                db.collection("users").document(binding.idEmail.getText().toString().trim()).set(userMap).addOnCompleteListener{
                    Log.d("db", "success")
                }

                navController.navigate(R.id.action_nav_registo_to_nav_tarefa)

            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    }
