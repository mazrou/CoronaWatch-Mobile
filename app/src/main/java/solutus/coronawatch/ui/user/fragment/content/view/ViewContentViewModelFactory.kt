package solutus.coronawatch.ui.user.fragment.content.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import solutus.coronawatch.data.reposetory.VideosRepository

class ViewContentViewModelFactory (
    private val videosRepository: VideosRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewContentViewModel(videosRepository) as T
    }
}
