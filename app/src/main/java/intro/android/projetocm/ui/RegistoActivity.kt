package intro.android.projetocm.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.MainActivity
import intro.android.projetocm.R
import intro.android.projetocm.databinding.ActivityRegistoBinding

class RegistoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistoBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnRegisto.setOnClickListener{
            auth.createUserWithEmailAndPassword(binding.idEmail.getText().toString().trim(), binding.idPassword.getText().toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")

                        val userMap = hashMapOf(
                            "nome" to binding.idNome.getText().toString().trim(),
                            "email" to binding.idEmail.getText().toString().trim())

                        db.collection("users").document(binding.idNome.getText().toString().trim()).set(userMap).addOnCompleteListener{
                            Log.d("db", "success")
                        }



                        val intent = Intent(this, TarefaActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()


    }
}
