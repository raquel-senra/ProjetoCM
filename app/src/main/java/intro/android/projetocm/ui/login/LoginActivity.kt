package intro.android.projetocm.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.databinding.ActivityLoginBinding
import intro.android.projetocm.ui.RegistoActivity
import intro.android.projetocm.ui.TarefaActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnIniciar.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.email.getText().toString().trim(),
                binding.password.getText().toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, TarefaActivity::class.java)
                        startActivity(intent)



                        //navView.menu.findItem(id.nav_logout).setVisible(true)
                        //navView.getMenu().findItem(id.nav_login).setVisible(false)

                    } else {
                        Log.w(TAG, "signWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.txtRegisto.setOnClickListener {
            val intent = Intent(this, RegistoActivity::class.java)
            startActivity(intent)
        }

        fun onStart() {
            super.onStart()

            val currentUser = auth.currentUser
            if (currentUser != null) {
                val intent = Intent(this, TarefaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}