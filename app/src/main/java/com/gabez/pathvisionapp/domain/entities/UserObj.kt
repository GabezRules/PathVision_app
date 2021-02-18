package com.gabez.pathvisionapp.domain.entities

data class UserObj(var uid: String = "", var email: String = "", var login: String = "", var paths: ArrayList<PathObject> = ArrayList())