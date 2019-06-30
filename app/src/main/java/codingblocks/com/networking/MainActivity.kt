package codingblocks.com.networking

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getdata.setOnClickListener {
            updateTextView()
        }
    }

    fun updateTextView() {
        val networkTask = NetworkTask()
        networkTask.execute(
            "https://jsonplaceholder.typicode.com/users",
            "https://www.github.com",
            "https://api.github.com/search/users?q=karandeep"
        )
    }

    inner class NetworkTask : AsyncTask<String, Int, String>() {
        override fun doInBackground(vararg url: String?): String? {
            val googleUrl = URL(url[0])
            val connection = googleUrl.openConnection() as HttpURLConnection
            val isr = InputStreamReader(connection.inputStream)
            val bufferReader = BufferedReader(isr)
            val sb = StringBuilder()
            var buffer: String? = ""
            while (buffer != null) {
                sb.append(buffer)
                buffer = bufferReader.readLine()
            }
            return sb.toString()
        }

        override fun onPostExecute(result: String?) {

//            Log.i("Networking", result)
            val userList = arrayListOf<GithubUser>()
                //To fetch data from github api
//            val jsondata=JSONObject(result)

//            val userArray=jsondata.getJSONArray("items")
//
//            for (i in 0..20) {
//
//                val user = GithubUser(
//                    (userArray[i] as JSONObject).getString("login"),
//                    (userArray[i] as JSONObject).getInt("id"),
//                    (userArray[i] as JSONObject).getString("avatar_url")
//                )
//                userList.add(user)
//            }
            //To fetch data from jsonplaceholder api
            val userArray=JSONArray(result)

            for (i in 0..8) {
                var user = userArray[i] as JSONObject
                val useraddr=user.getJSONObject("address")
                val streetname =useraddr.getString("street")
                val suitename =useraddr.getString("suite")
                val cityname =useraddr.getString("city")
                val u=GithubUser(
                    user.getInt("id"),
                    user.getString("name"),
                    user.getString("username"),
                    user.getString("email"),
                    streetname,suitename,cityname,
                    useraddr.getString("zipcode")

                )
                userList.add(u)
//                showdata.text=showdata.text.toString()+(i+1).toString()+streetname+"\n"+suitename+"\n"+cityname+"\n\n"
                     }


                //To fetch data from github api using GSON library
//                val user = Gson().fromJson(result, Github::class.java)
//                userList.addAll(user.items)
                //To fetch data from jsonplaceholder api using GSON library

                rview.layoutManager = LinearLayoutManager(this@MainActivity)
                rview.adapter = GithubAdapter(this@MainActivity, userList)

//            val user=userArray[0] as JSONObject
//            val street=user.getString("street")
//            val suite=user.getString("suite")
//            val username=user.getString("login")
//            val avatar=user.getString("avatar_url")
//            val followers=user.getString("followers_url")
//            showdata.text=username
//            showdata.text=showdata.text.toString()+suite
//            Picasso.get().load(avatar).into(img)
        }
    }

}