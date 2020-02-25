package academy.learnprogramming.youtubeplayer

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity : AppCompatActivity(){

    private val STORAGE_PERMISSION_CODE:Int =1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

//        when(onClick(btnurl)){
//
//        }
//        btnurl.setOnClickListener(this)

        btnurl.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M )
            {
                if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_DENIED)
                {
                    requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODE)
                }
                else
                {
                    startDownloading()
                }
            }
            else
            {
                startDownloading()
            }
        }

    }
private fun startDownloading(){
 var url = urltext1.text.toString()

    val request = DownloadManager.Request(Uri.parse(url))
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
    request.setTitle("Download")
    request.setDescription("This file is downloading...")

    request.allowScanningByMediaScanner()
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"${System.currentTimeMillis()}")

    val manager=getSystemService(Context.DOWNLOAD_SERVICE)as DownloadManager
    manager.enqueue(request)
}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            STORAGE_PERMISSION_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    startDownloading()
                }
                else
                {
                    Toast.makeText(this,"permission denied!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}