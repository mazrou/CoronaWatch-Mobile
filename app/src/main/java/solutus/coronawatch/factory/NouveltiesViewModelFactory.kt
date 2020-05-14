package solutus.coronawatch.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import solutus.coronawatch.data.reposetory.NouveltiesRepository
import solutus.coronawatch.viewModel.NouveltiesViewModel


open class NouveltiesViewModelFactory(
    private val articlesRepository: NouveltiesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NouveltiesViewModel(
            articlesRepository
        ) as T
    }

}