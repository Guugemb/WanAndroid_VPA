package com.example.wanandroid_vpa.home.repository

import com.example.wanandroid_vpa.network.ApiService
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by geegumb on 2020/12/10
 */
class HomeRepositoryTest {

    private lateinit var mApiService: ApiService
    private val mockServer = MockWebServer()

    @Before
    fun setUp() {
        //设置响应，默认返回http code是 200
        //设置响应，默认返回http code是 200
        val mockResponse = MockResponse()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .throttleBody(100, 1, TimeUnit.SECONDS) // 0.1KB/S，模拟网速慢的情况
            .setBody(MockData.BANNER)
        val mockResponse1 = MockResponse()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .setResponseCode(404)
            .setBody("{\"error\": \"网络异常\"}")
        mockServer.enqueue(mockResponse) //成功响应
        mockServer.enqueue(mockResponse1) //失败响应


        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://${mockServer.hostName}:${mockServer.port}/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun requestBannerFromNet() {
        runBlocking { println(mApiService.getBanner().toString()) }
        val request = mockServer.takeRequest()
        println("request.requestLine : ${request.requestLine}")
        println("User-Agent : ${request.getHeader("User-Agent")}")
    }

    @Test
    fun requestArticleFromNet() {
        runBlocking { println(NetworkService.apiService.getHomeArticle(0)) }
    }
}

class MockInterceptor : Interceptor {

    private val responseString = MockData.BANNER

    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(responseString.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

class MockData {
    companion object {
        const val BANNER = "{\"data\":[{\"desc\":\"享学~\",\"id\":29,\"imagePath\":\"https://wanandroid.com/blogimgs/184" +
                "b499f-dc69-41f1-b519-ff6cae530796.jpeg\",\"isVisible\":1,\"order\":0,\"title\":\"Andr" +
                "oid开发简历怎么写？让你的简历通过率降低200%！\",\"type\":0,\"url\":\"https://www.bilibili.c" +
                "om/video/BV1fA411x7bV\"},{\"desc\":\"\",\"id\":6,\"imagePath\":\"https://www.wanandro" +
                "id.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png\",\"isVisible\":1,\"order\":1" +
                ",\"title\":\"我们新增了一个常用导航Tab~\",\"type\":1,\"url\":\"https://www.wanandroid.com" +
                "/navi\"},{\"desc\":\"一起来做个App吧\",\"id\":10,\"imagePath\":\"https://www.wanandroid.c" +
                "om/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png\",\"isVisible\":1,\"order\":1,\"titl" +
                "e\":\"一起来做个App吧\",\"type\":1,\"url\":\"https://www.wanandroid.com/blog/show/2\"},{\"de" +
                "sc\":\"\",\"id\":20,\"imagePath\":\"https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b" +
                "318-b912f163b8d0.png\",\"isVisible\":1,\"order\":2,\"title\":\"flutter 中文社区 \",\"type\":1" +
                ",\"url\":\"https://flutter.cn/\"}],\"errorCode\":0,\"errorMsg\":\"\"}"
    }
}