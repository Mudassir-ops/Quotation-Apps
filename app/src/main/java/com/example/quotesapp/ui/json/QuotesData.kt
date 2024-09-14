import com.google.gson.annotations.SerializedName

data class QuotesData(
    @SerializedName("motivational_quotes")
    val motivationalQuotes: List<Quote>?,

    @SerializedName("inspirational_quotes")
    val inspirationalQuotes: List<Quote>?,

    @SerializedName("love_quotes")
    val loveQuotes: List<Quote>?,

    @SerializedName("friendship_quotes")
    val friendshipQuotes: List<Quote>?,

    @SerializedName("success_quotes")
    val successQuotes: List<Quote>?,

    @SerializedName("life_quotes")
    val lifeQuotes: List<Quote>?,

    @SerializedName("happiness_quotes")
    val happinessQuotes: List<Quote>?,

    @SerializedName("leadership_quotes")
    val leadershipQuotes: List<Quote>?,

    @SerializedName("wisdom_quotes")
    val wisdomQuotes: List<Quote>?,

    @SerializedName("positive_thinking_quotes")
    val positiveThinkingQuotes: List<Quote>?,

    @SerializedName("courage_quotes")
    val courageQuotes: List<Quote>?,

    @SerializedName("family_quotes")
    val familyQuotes: List<Quote>?,

    @SerializedName("self_love_quotes")
    val selfLoveQuotes: List<Quote>?,

    @SerializedName("time_management_quotes")
    val timeManagementQuotes: List<Quote>?,

    @SerializedName("gratitude_quotes")
    val gratitudeQuotes: List<Quote>?
)

data class Quote(
    @SerializedName("text")
    val text: String?,

    @SerializedName("author")
    val author: String?
)
