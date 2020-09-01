package com.example.botapp01

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //URL入力して正しければ遷移
        btn_join.setOnClickListener {
            val inputmoji = input_url.text
            val jitsiURL = "https://meet.jit.si/"
            if (inputmoji.indexOf(jitsiURL) != 0) {
                warning.text = "正しくないで"
            }
            else {
                warning.text = ""
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }

        //クリップボードに貼ってあるものをペーストする
        var mManager: ClipboardManager = applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        btn_paste.setOnClickListener{
            input_url.text.clear()
            input_url.text.append(mManager.primaryClip?.getItemAt(0)?.text)
        }


    }
}


