package yourbestigor.jobber.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
object ContextModule {



    //Provide application context
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }

}