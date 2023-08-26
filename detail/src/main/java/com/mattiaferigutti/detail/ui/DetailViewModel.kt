package com.mattiaferigutti.detail.ui

import androidx.lifecycle.ViewModel
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val repo: ITaskRepo
) : ViewModel() {


}