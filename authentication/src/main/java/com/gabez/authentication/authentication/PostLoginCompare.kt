package com.gabez.authentication.authentication

import com.gabez.pathvisionapp.data.localDatabase.dbLogic.LocalDatabase

class PostLoginCompare(private var localDb: LocalDatabase, private val remoteDb: FirebaseDataHolder) {
    suspend fun areTheSame(): Boolean{
        val localPaths = localDb.dao().getAllPaths()
        val localSkills = localDb.dao().getAllSkills()

        val remotePaths = remoteDb.allPaths.value!!
        val remoteSkills = remoteDb.allPaths.value!!

        if(localPaths.size != remotePaths.size) return false
        if(localSkills.size != remoteSkills.size) return false


        for(localPath in localPaths){
            for(remotePath in remotePaths){
                for(index in 0..localPath.relatedSkills.split(";;").size){
                    if(localPath.relatedSkills.split(";;")[index] != remotePath.skills[index].name) return false
                }
            }
        }

        return true
    }
}