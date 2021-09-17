package com.example.cleanarchitecture

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class RvAdapter(private val list:ArrayList<ResponseItem>,private val context: Context,
      private val click: OnClick) : RecyclerView.Adapter<RvAdapter.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        if (viewType==0)
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
    if (position==list.size){
        holder.bind(null,position,context)

    }else
        holder.bind(list[position],position,context)

    }

    override fun getItemCount(): Int {
        return list.size+1
    }
    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            return 1;
        }
        return 0;

    }
    private var hide=false;

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(responseItem: ResponseItem?, position: Int, context: Context) {

            if (position==list.size){
                viewholder2(itemView)
            }else {
                itemView.textView.text = responseItem?.stars.toString();
                itemView.forks.text = responseItem?.forks.toString();
                val imageView = itemView.repo_img;
                itemView.setOnClickListener {
                    click.itemClicked(position)
                }
                Glide.with(context)
                    .load("https://moodle.htwchur.ch/pluginfile.php/124614/mod_page/content/4/example.jpg")
                    .into(imageView)
            }

        }



    }
    private fun viewholder2(itemView: View)  {

            itemView.cancel_button.setOnClickListener{
                hide=true
                itemView.visibility=View.INVISIBLE

            }
            if (hide){
                itemView.visibility=View.INVISIBLE
            }


    }

}

interface OnClick{
    fun itemClicked(position:Int);
}