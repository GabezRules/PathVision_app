package com.gabez.authentication.authentication.entities

import com.gabez.pathvisionapp.domain.entities.PathObj

data class UserObj(var uid: String = "", var email: String = "", var login: String = "", var paths: ArrayList<PathObj> = ArrayList())