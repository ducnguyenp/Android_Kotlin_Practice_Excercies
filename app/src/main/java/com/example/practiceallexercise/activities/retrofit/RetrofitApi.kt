package com.example.practiceallexercise.activities.retrofit

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.TextView
import com.example.practiceallexercise.R
import com.example.practiceallexercise.utils.Constants
import retrofit.*

class RetrofitApi(var context: Context) {
    private fun isNetworkAvailable(): Boolean {
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectionManager.activeNetwork ?: return false // like in javascript A ?? B
            val activeNetwork = connectionManager.getNetworkCapabilities(network)
            return when {
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> false
            }
        } else {
            val networkInfo = connectionManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

    fun getUserDetail() {
        if (isNetworkAvailable()) {
            var retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            var service: RetrofitService = retrofit.create(RetrofitService::class.java)
            var listCall: Call<UserResponse> = service.getUser()

            listCall.enqueue(object : Callback<UserResponse> {
                override fun onResponse(response: Response<UserResponse>?, retrofit: Retrofit?) {
                    if (response!!.isSuccess) {
                        setUpUI(response.body())
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.i("Fetch api", t!!.message.toString())
                }

            })
        }
    }

    private fun setUpUI(user: UserResponse) {
        var view = context as Activity
        view.findViewById<TextView>(R.id.tv_user_id).text = user.userId.toString()
        view.findViewById<TextView>(R.id.tv_id).text = user.id.toString()
        view.findViewById<TextView>(R.id.tv_title).text = user.title
        view.findViewById<TextView>(R.id.tv_complete).text = user.complete.toString()
    }
}