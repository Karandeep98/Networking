package codingblocks.com.networking

class GithubUser(
    val login: String,
    val id: Int,
    val avatar_url: String)
data class Github(val items:ArrayList<GithubUser>)
