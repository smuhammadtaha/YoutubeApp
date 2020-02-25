package academy.learnprogramming.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Urlbtn1.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when (view.id){
            R.id.Urlbtn1 -> YouTubeStandalonePlayer.createVideoIntent(
                    this,getString(R.string.GOOGLE_API_KEY), urltext.text.toString())

            else -> throw IllegalArgumentException("Undefined button clicked")
        }

        startActivity(intent)

    }
}
