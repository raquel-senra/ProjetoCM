package intro.android.projetocm.ui.registo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import intro.android.projetocm.MainActivity
import intro.android.projetocm.R
import intro.android.projetocm.databinding.FragmentLoginBinding
import intro.android.projetocm.databinding.FragmentRegistoBinding


class RegistoFragment : Fragment() {
    private lateinit var binding: FragmentRegistoBinding
    private lateinit var auth: FirebaseAuth

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

        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()


        }
    }
