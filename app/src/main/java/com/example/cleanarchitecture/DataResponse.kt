package com.example.cleanarchitecture

data class DataResponse (
    val response: List<ResponseItem?>? = null

)

data class BuiltByItem(
    val href: String? = null,
    val avatar: String? = null,
    val username: String? = null
)

data class ResponseItem(
    val forks: Int? = null,
    val builtBy: List<BuiltByItem?>? = null,
    val author: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val avatar: String? = null,
    val languageColor: String? = null,
    val stars: Int? = null,
    val url: String? = null,
    val currentPeriodStars: Int? = null
)