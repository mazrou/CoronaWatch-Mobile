package solutus.coronawatch.ui.firstActivity.firstView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.start_fragment.*
import solutus.coronawatch.ui.loginActivity.LoginActivity


class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empty_cercle.setOnClickListener {
            val navController: NavController =
                Navigation.findNavController(activity!!, R.id.first_fragment)
            navController.navigate(R.id.to_advice_fragment_action)
        }
        start_button.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}
