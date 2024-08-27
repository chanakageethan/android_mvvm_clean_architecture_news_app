package com.example.mvvm_clean_architecure_2.data.api


import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class NewsAPIServiceTest {
    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer


    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=64f0843cfcc64b4298f3381a12cf19a3")

        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles

            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles

           val article = articleList[0]
            assertThat(article.author).isEqualTo("The Express Tribune")
            assertThat(article.url).isEqualTo("https://news.google.com/rss/articles/CBMixAFBVV95cUxNUEwxNUE5YkgwVVdBaGRTTS05RTdWTThCZWp6b2VCdjVoLVlHRWp1alExM0NMcGlMSExEOEJIX1NZdFVLOXJtUmZfWkpOVDFVNFBSQTl3NTljc1lOcWl0SlZEX2NRQWtuR1FmRjY3bTF3N3M1LWtsc2R3VUFBTmJHcmNvWmxWdTNqV29hMDhuR0lqcnhCdjRvSFdiZ0dJOHRLb0RBYjE1VTY1WmRvVE5LX09FQ05SUU1jWU1kb0FEdUIzakFf0gHMAUFVX3lxTFBfaGp0OFVXOFdqd3pjUlJfbUt3Ui1rU25NaWVkVTA0RjVuZklmVjh4YXpIc0M5QUlzSkhpTXB6cGhwemVGNlVNanA3MnJTaUZjekpjVFNKSWNJSncyZFBYZ2VkZWU3LWZTa29GS2otbE1ROFJGTFNRTzVMVDk0MF9oWkxIZTFiNjlvQnpkZ3R1VzF5eGVPV19aZ042cmhaU09ocS1oRjRsbGF3SU5DTF9QQVU1SlpTRFpCNUs1TlFrRThmcnBhak1SRzZqVA?oc=5")
            assertThat(article.publishedAt).isEqualTo("2024-08-23T12:27:26Z")
        }
    }

}