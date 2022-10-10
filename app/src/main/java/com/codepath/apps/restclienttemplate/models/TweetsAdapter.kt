package com.codepath.apps.restclienttemplate.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.R
import com.codepath.apps.restclienttemplate.TimeFormatter

class TweetsAdapter(val tweets: ArrayList<Tweet>): RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // inflate our item layout
        val view = inflater.inflate(R.layout.item_tweet, parent, false)

        return ViewHolder(view)
    }

    // populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get the data model based on the position
        val tweet: Tweet = tweets.get(position)

        // set item views based on views and data model
        holder.tvUserName.text = tweet.user?.name
        holder.tvTweetBody.text = tweet.body
        var formattedTime = TimeFormatter.getTimeDifference(tweet.createdAt)
        holder.tvTime.text = formattedTime
        Glide.with(holder.itemView).load(tweet.user?.publicImageUrl).circleCrop().into(holder.ivProfileImage)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    fun addAll(tweetList: List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUserName = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tvTweetBody)
        var tvTime = itemView.findViewById<TextView>(R.id.tvTime)
    }

}