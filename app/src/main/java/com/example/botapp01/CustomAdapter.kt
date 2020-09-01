package com.example.botapp01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.handling.view.*


interface CustomAdapterListener{
    fun clicked(bot: BotInfo)
}

class CustomAdapter (context: Context, var mBotlist: List<BotInfo>,val listener: CustomAdapterListener) : ArrayAdapter<BotInfo>(context, 0, mBotlist) {
    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bot = mBotlist[position]

        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_item, parent, false)
        }

        //BotInfoクラスとlist_item.xmlを紐付け
        val imageView = view?.findViewById<ImageView>(R.id.imageView)
        imageView?.setImageResource(bot.imageId)
        val name = view?.findViewById<TextView>(R.id.text_name)
        name?.text = bot.name
        val note = view?.findViewById<TextView>(R.id.text_notes)
        note?.text = bot.notes

        val shousai = view?.findViewById<Button>(R.id.btn_handle)
        shousai?.setOnClickListener{
            listener.clicked(bot)
        }
        val status = arrayOf("未参加", "参加中")
        val checkbox = view?.findViewById<CheckBox>(R.id.btn_in)
        checkbox?.isChecked = false
        checkbox?.text = status[0]

        checkbox?.setOnClickListener(View.OnClickListener {
            val check = checkbox.isChecked()
            if (check){
                checkbox.text = status[1]
                //ここでfire baseに書き込む
            } else{
                checkbox.text = status[0]
            }
        })


        return view!!
    }


    fun popup(bot: BotInfo){
        //ポップアップ
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = layoutInflater.inflate(R.layout.handling, null)
        view.hdl_name.text = bot.name
        view.imageView_popup.setImageResource(bot.imageId)
        view.text_hdl_detail.text = bot.detail
        view.text_hdl_use.text = bot.use
        AlertDialog.Builder(context).setView(view).setPositiveButton("back", { dialog, which ->}).show()

    }

}