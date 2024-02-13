package mbk.io.homework2.di
import mbk.io.homework2.BuildConfig
import mbk.io.homework2.data.RMRepository
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.ui.characters_activity.MainViewModel
import mbk.io.homework2.ui.secondActivity.DetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get
import java.util.concurrent.TimeUnit

val CartoonModule = module {
    val ViewModelModule = module {
        viewModel {
            MainViewModel()
        }
        viewModel {
            DetailsViewModel(get())
        }
    }

    val RepositoryModule = module {
        single {
            providesCartoonApiService(get())
        }
    }

    val NetworkModule = module {
        single {
            provideRetrofit(get())
        }
        factory {
            provideOkHttpClient(get())
        }
        single {
            provideInterceptor()
        }
    }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
): OkHttpClient =
    OkHttpClient.Builder()
        .writeTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .callTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

fun provideInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


fun providesCartoonApiService(retrofit: Retrofit): CartoonApiService =
    retrofit.create(CartoonApiService::class.java)