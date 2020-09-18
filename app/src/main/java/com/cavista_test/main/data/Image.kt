package com.cavista_test.main.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("account_id")
    val accountId: String?,
    @SerializedName("account_url")
    val accountUrl: String?,
    @SerializedName("ad_type")
    val adType: Int?,
    @SerializedName("ad_url")
    val adUrl: String?,
    @SerializedName("animated")
    val animated: Boolean?,
    @SerializedName("bandwidth")
    val bandwidth: Long?,
    @SerializedName("comment_count")
    val commentCount: String?,
    @SerializedName("datetime")
    val datetime: Long?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("downs")
    val downs: String?,
    @SerializedName("edited")
    val edited: String?,
    @SerializedName("favorite")
    val favorite: Boolean?,
    @SerializedName("favorite_count")
    val favoriteCount: String?,
    @SerializedName("gifv")
    val gifv: String?,
    @SerializedName("has_sound")
    val hasSound: Boolean?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("hls")
    val hls: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("in_gallery")
    val inGallery: Boolean?,
    @SerializedName("in_most_viral")
    val inMostViral: Boolean?,
    @SerializedName("is_ad")
    val isAd: Boolean?,
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("looping")
    val looping: Boolean?,
    @SerializedName("mp4")
    val mp4: String?,
    @SerializedName("mp4_size")
    val mp4Size: Int?,
    @SerializedName("nsfw")
    val nsfw: String?,
    @SerializedName("points")
    val points: String?,
    @SerializedName("processing")
    val processing: Processing?,
    @SerializedName("score")
    val score: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("ups")
    val ups: String?,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("vote")
    val vote: String?,
    @SerializedName("width")
    val width: Int?
): Parcelable