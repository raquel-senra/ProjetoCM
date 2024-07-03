package intro.android.projetocm.ui.login

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentLoginBinding
import intro.android.projetocm.ui.RegistoActivity
import intro.android.projetocm.ui.TarefaActivity


class LoginFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        auth = Firebase.auth

        binding.txtRegisto.setOnClickListener {
            navController.navigate(R.id.action_nav_login_to_nav_registo)
        }

        binding.btIniciar.setOnClickListener {
            val email = binding.email.getText().toString().trim()
            val password = binding.password.getText().toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty())

                loginUser(email, password)
            else
                Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    public override fun onStart() {
        super.onStart()

        if (auth.currentUser != null){
            navController.navigate(R.id.action_nav_login_to_perfilFragment)
        }
    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful)
                navController.navigate(R.id.action_nav_login_to_nav_tarefa)
            else
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }





}