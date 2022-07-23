package com.dicoding.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class ItemResult(
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
) : Parcelable
{
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }
        other as ItemResult
        if (id!= other.id){
            return false
        }
        if (avatarUrl!= other.avatarUrl){
            return false
        }
        if (login!= other.login){
            return false
        }
        if (htmlUrl!= other.htmlUrl){
            return false
        }
        return true
    }
}