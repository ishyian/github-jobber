package yourbestigor.jobber.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import yourbestigor.jobber.base.BaseView


@Module
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    //Provide application context
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }

}