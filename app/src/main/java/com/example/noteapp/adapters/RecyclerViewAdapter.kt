package com.example.noteapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.ColorUtil
import com.example.noteapp.R
import com.example.noteapp.model.NotesModel
import kotlinx.android.synthetic.main.item_note.view.*


class RecyclerViewAdapter(val listener: RowClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<NotesModel>()

    fun setListData(data: ArrayList<NotesModel>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }



        holder.bind(items[position])

    }


    class MyViewHolder(view: View, val listener: RowClickListener) : RecyclerView.ViewHolder(view) {

        val tvName = view.textView7
        val tvDetails = view.textView9
        val tvDate = view.date
        val views = view.viewColorBorder


        fun bind(data: NotesModel) {
            tvName.text = data.title
            tvDetails.text = data.description
            tvDate.text = data.date?.dropLast(5) ?: "2 june"

            views.setBackgroundColor(
                ContextCompat.getColor(views.context, ColorUtil.getColorByPosition(position))
            )

        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(user: NotesModel)
        fun onItemClickListener(user: NotesModel)
    }
}