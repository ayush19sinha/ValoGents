package my.android.valogents.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import my.android.valogents.data.repository.ValorantRepository
import javax.inject.Inject

@HiltViewModel
class AgentListViewModel @Inject constructor(private val repository: ValorantRepository) : ViewModel(){

}