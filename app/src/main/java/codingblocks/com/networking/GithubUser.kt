package codingblocks.com.networking

import org.json.JSONObject

//class GithubUser(
//    val login: String,
//    val id: Int,
//    val avatar_url: String)
//data class Github(val items:ArrayList<GithubUser>)

class GithubUser(
    val id:Int,
    val name:String,
    val username:String,
    val email:String,
    val street: String,
    val suite: String,
    val city: String,
val zipcode:String)
data class Github(val items:ArrayList<GithubUser>)
