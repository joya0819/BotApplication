package com.example.botapp01

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() , CustomAdapterListener {

    lateinit var mCustomAdapter: CustomAdapter
    lateinit var mBotList: ArrayList<BotInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val voice = BotInfo("発言ボット", "ボットが盛りあげます", R.drawable.bot_voice, "15秒間沈黙が続けばボットが盛りあげます", "会議が全然面白くない時に使おう！")
        val launch = BotInfo("バズーカボット", "寝てる人にバズーカを撃ちます", R.drawable.bot_launch, "寝ている人の家にバズーカを打ち込みます", "会議で反応がない人、嫌いな人に使おう！")
        mBotList = arrayListOf(voice,launch)
        //ここに新たなボットを書き込む

        val listView = findViewById<ListView>(R.id.list_view)

        mCustomAdapter = CustomAdapter(this, mBotList, this)

        listView.adapter = mCustomAdapter

    }


    override fun clicked(bot: BotInfo) {

        mCustomAdapter.popup(bot)
    }

}