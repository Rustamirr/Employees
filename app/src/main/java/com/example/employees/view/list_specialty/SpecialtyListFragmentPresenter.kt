package com.example.employees.view.list_specialty

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.employees.App
import com.example.employees.database.model.Specialty
import com.example.employees.repository.RepositorySpecialty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpecialtyListFragmentPresenter: ViewModel(), SpecialtyListFragmentContract.Presenter {
    @Inject
    lateinit var repositorySpecialty: RepositorySpecialty
    @Inject
    lateinit var adapter: ArrayAdapter<Specialty>

    private var view: SpecialtyListFragmentContract.View? = null
    private lateinit var disposable: Disposable

    init {
        App.instance.injector.getMainActivityComponent().inject(this)
    }

    override fun onViewCreated(view: SpecialtyListFragmentContract.View) {
        this.view = view
    }

    override fun onStart() { subscribeToUpdates() }

    override fun onStop() { unSubscribeToUpdates() }

    override fun onDestroyView() { view = null }

    private fun subscribeToUpdates(){
        disposable = repositorySpecialty.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                adapter.clear()
                adapter.addAll(list)
                adapter.notifyDataSetChanged()
            }
    }

    private fun unSubscribeToUpdates(){
        if (!disposable.isDisposed){
            disposable.dispose()
        }
    }
}