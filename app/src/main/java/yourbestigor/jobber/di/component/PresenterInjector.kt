package yourbestigor.jobber.di.component

import dagger.BindsInstance
import dagger.Component
import yourbestigor.jobber.base.BaseView
import yourbestigor.jobber.di.module.ContextModule
import yourbestigor.jobber.di.module.NetworkModule
import yourbestigor.jobber.ui.JobsPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {


    fun inject(jobsPresenter: JobsPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }

}
