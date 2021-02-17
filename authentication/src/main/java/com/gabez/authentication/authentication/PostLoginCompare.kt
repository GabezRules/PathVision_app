package com.gabez.authentication.authentication

import com.gabez.pathvisionapp.data.dataHolders.FirebaseDataHolder
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder

class PostLoginCompare(private var localDb: DbPathsHolder, private val remoteDb: FirebaseDataHolder) {
    suspend fun areTheSame(): Boolean{
        val localPaths = localDb.allPaths.value!!
        val localSkills = localDb.allSkills.value!!

        val remotePaths = remoteDb.allPaths.value!!
        val remoteSkills = remoteDb.allPaths.value!!

        if(localPaths.size != remotePaths.size) return false
        if(localSkills.size != remoteSkills.size) return false


        for(localPath in localPaths){
            for(remotePath in remotePaths){
                for(index in 0..localPath.items!!.size){
                    if(localPath.items!![index].title != remotePath.items?.get(index)!!.title) return false
                }
            }
        }

        return true
    }
}