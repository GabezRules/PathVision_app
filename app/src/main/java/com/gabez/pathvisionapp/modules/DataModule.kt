package com.gabez.pathvisionapp.modules

import com.gabez.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.data.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapterImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val dataModule = module {
    single { LocalDatabase.getInstance(get()) }
    single { NetworkClient(get()) }
    single { FirebaseDbAdapterImpl(get(), get(), get()) as FirebaseDbAdapter }

    single { FirebaseAuth.getInstance() }
    single { FirebaseDatabase.getInstance() }
}