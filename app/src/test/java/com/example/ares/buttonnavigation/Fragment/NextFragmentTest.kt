package com.example.ares.buttonnavigation.Fragment

import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.MatchResponse
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.example.ares.buttonnavigation.Utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextFragmentTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository



    @Mock
    private lateinit var presenter: Presenter


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = Presenter(view, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getMatch(){
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch()),
                MatchResponse::class.java)).thenReturn(response)

        presenter.getNextMatch()
        verify(view).showLoading()
        verify(view).showMatchDetail(match)
        verify(view).hideLoading()

    }
}