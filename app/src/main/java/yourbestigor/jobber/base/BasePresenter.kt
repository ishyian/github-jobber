package yourbestigor.jobber.base

import yourbestigor.jobber.di.component.DaggerPresenterInjector
import yourbestigor.jobber.di.component.PresenterInjector
import yourbestigor.jobber.di.module.ContextModule
import yourbestigor.jobber.di.module.NetworkModule
import yourbestigor.jobber.ui.JobsPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is JobsPresenter -> injector.inject(this)
        }
    }
}