import com.example.myapplication.ProductModel.DataShow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val access_token: String)
interface ApiInterface {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    @GET("products")
    fun getProducts(): Call<List<DataShow>>
}
