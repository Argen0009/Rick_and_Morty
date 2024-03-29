package mbk.io.homework2.di
import mbk.io.homework2.BuildConfig
import mbk.io.homework2.data.RMRepository
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.ui.characters_activity.MainViewModel
import mbk.io.homework2.ui.secondActivity.DetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get
import java.util.concurrent.TimeUnit


// ViewModel Module
val CartoonModule = module {

    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailsViewModel(get())
    }
}

// Repository Module
val repositoryModule = module {
    single { RMRepository(get()) }
}

// Network Module
val networkModule = module {
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideCartoonApiService(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

fun provideInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

fun provideCartoonApiService(retrofit: Retrofit): CartoonApiService =
    retrofit.create(CartoonApiService::class.java)