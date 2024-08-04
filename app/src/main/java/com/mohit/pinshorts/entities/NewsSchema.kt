package com.mohit.pinshorts.entities
import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    val search_metadata: SearchMetadata?,
    val search_parameters: SearchParameters?,
    val news_results: List<NewsResult>,
    val menu_links: List<MenuLink>?
)

@Serializable
data class MenuLink(
    val title: String?,
    val topic_token: String?,
    val serpapi_link: String?
)

@Serializable
data class SearchMetadata(
    val id: String?,
    val status: String?,
    val json_endpoint: String?,
    val created_at: String?,
    val processed_at: String?,
    val google_news_url: String?,
    val raw_html_file: String?,
    val total_time_taken: Double?
)

@Serializable
data class SearchParameters(
    val engine: String?,
    val gl: String?,
    val q: String?=null
)

@Serializable
data class NewsResult(
    val position: Int?,
    val title: String,
    val source: Source?=null,
    val link: String?=null,
    val thumbnail: String?=null,
    val date :String?=null
)

@Serializable
data class Source(
    val name: String?,
    val icon: String?=null,
    val authors: List<String>?=null
)
