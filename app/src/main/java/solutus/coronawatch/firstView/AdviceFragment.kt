package solutus.coronawatch.ui.firstView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.advice_fragment.*

import solutus.coronawatch.ui.MainActivity

class AdviceFragment : Fragment() {

    companion object {
        fun newInstance() = AdviceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.advice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empty_w_cercle.setOnClickListener {
            MainActivity.replaceFragment(activity, R.id.first_fragment, StartFragment())
        }
    }

}
