package com.example.myapplication

import ApiInterface
import LoginRequest
import LoginResponse
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Homepage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage) // Move this line above findViewById calls

        val name = findViewById<EditText>(R.id.name)
        val pass = findViewById<EditText>(R.id.passwd)
        val submit = findViewById<Button>(R.id.submit)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = RetrofitInstance.api
//
        submit.setOnClickListener {
            val username = name.text.toString()
            val userpasswd = pass.text.toString()

            if (username.isBlank() || userpasswd.isBlank()) {
                Toast.makeText(this, "Enter  value", Toast.LENGTH_SHORT).show()
            } else {
                val loginRequest = LoginRequest(username, userpasswd)
                val call = apiService.login(loginRequest)

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            Toast.makeText(this@Homepage, "Login successful: ", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Homepage, ProductPage::class.java)
                            startActivity(intent)
                            finish()


                        } else {
                            Toast.makeText(this@Homepage, "Login failed: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@Homepage, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}
