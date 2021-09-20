package com.example.effe_21ca.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.effe_21ca.R
import com.example.effe_21ca.models.LeaderboardEntry

class LeaderboardAdapter(val context: Context) : RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    private var leaderboardEntry = ArrayList<LeaderboardEntry>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_leaderboard, parent, false)
        return LeaderBoardViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return leaderboardEntry.size
    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        if(position==0||position==1||position==itemCount-1){
            holder.itemView.visibility = View.INVISIBLE
        } else {
            holder.itemView.visibility = View.VISIBLE
        }
        holder.bind(leaderboardEntry[position], position-1)
    }


    inner class LeaderBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.helloText)
        val srNoTextView = itemView.findViewById<TextView>(R.id.srNoTextView)
        val emailTextView = itemView.findViewById<TextView>(R.id.emailTextView)
        val pointsTextView = itemView.findViewById<TextView>(R.id.pointsTextView)
        val separator = itemView.findViewById<View>(R.id.separator)

        fun bind(entry: LeaderboardEntry, position: Int){
            nameTextView.text = entry.name
            srNoTextView.text = "$position."
            pointsTextView.text = "${entry.score}"
//            emailTextView.text = entry.collegeName
            if(entry.isCurrentUser){
                itemView.setBackgroundResource(R.color.gray1)
            } else {
                itemView.setBackgroundResource(R.color.gray0)
            }
            if(position >= itemCount-2){
                separator.visibility = View.INVISIBLE
            }else {
                separator.visibility = View.VISIBLE
            }
        }
    }

    fun swapList(list: ArrayList<LeaderboardEntry>){
        if(leaderboardEntry.size == 0){
            leaderboardEntry.add(LeaderboardEntry())
            leaderboardEntry.add(LeaderboardEntry())
        } else {
            leaderboardEntry.removeAt(leaderboardEntry.size - 1)
        }

        leaderboardEntry.addAll(list)
        leaderboardEntry.add(LeaderboardEntry())
        notifyDataSetChanged()
    }
}