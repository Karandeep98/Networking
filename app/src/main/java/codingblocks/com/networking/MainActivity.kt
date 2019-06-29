package codingblocks.com.networking

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
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
                "https://api.github.com/search/users?q=karandeep98"
            )
        }

    inner class NetworkTask: AsyncTask<String, Int, String>() {
        override fun doInBackground(vararg url: String?): String? {
            val googleUrl =URL(url[0])
            val connection=googleUrl.openConnection()as HttpURLConnection
            val isr= InputStreamReader(connection.inputStream)
            val bufferReader= BufferedReader(isr)
            val sb= StringBuilder()
            var buffer:String?=""
            while(buffer!=null){
                sb.append(buffer)
                buffer=bufferReader.readLine()
            }
            return sb.toString()
             }

        override fun onPostExecute(result: String?) {

            Log.i("Networking", result)
            val userList= arrayListOf<GithubUser>()
            val jsondata=JSONObject(result)
            val userArray=jsondata.getJSONArray("address")
            for (i in 0..3) {

                val user = GithubUser(
                    (userArray[i] as JSONObject).getString("street"),
                    (userArray[i] as JSONObject).getString("suite"),
                    (userArray[i] as JSONObject).getString("city")
                )
                userList.add(user)
            }
            rview.layoutManager = LinearLayoutManager(this@MainActivity)
            rview.adapter = GithubAdapter(this@MainActivity,userList)

//            val user=userArray[0] as JSONObject
//            val username=user.getString("login")
//            val avatar=user.getString("avatar_url")
//            val followers=user.getString("followers_url")
//            showdata.text=username
//            showdata.text=showdata.text.toString()+followers
//            Picasso.get().load(avatar).into(img)
        }
    }
}
