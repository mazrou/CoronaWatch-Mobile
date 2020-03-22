package solutus.coronawatch.ui.home.novelties

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.coronawatch_mobile.R

class NouveltiesFragment : Fragment() {

    companion object {
        fun newInstance() = NouveltiesFragment()
    }

    private lateinit var viewModel: NouveltiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nouvelties_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NouveltiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
