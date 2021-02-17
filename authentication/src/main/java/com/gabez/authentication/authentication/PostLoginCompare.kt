package com.gabez.authentication.authentication

import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject

class PostLoginCompare(private var localDb: LocalDbGateway, private val remoteDb: FirebaseGateway) {
    suspend fun areTheSame(): Boolean{
        val localPathsFlow = localDb.getAllPaths()
        val localSkillsFlow = localDb.getAllSkills()

        val remotePathsFlow = remoteDb.getRemotePaths()
        val remoteSkillsFlow = remoteDb.getRemoteSkills()

        //TODO: Implement

        /*
        val properLocalPaths = runBlocking {
            localPathsFlow.combine(localSkillsFlow){
                pathList, skillList -> createPaths(skillList, pathList)
            }
        }.collect()

        val properRemotePaths = runBlocking {
            remotePathsFlow.combine(remoteSkillsFlow){
                    pathList, skillList -> createPaths(skillList, pathList)
            }.collect()
        }

        if(properLocalPaths.size != remotePaths.size) return false
        if(localSkills.size != remoteSkills.size) return false


        for(localPath in localPaths){
            for(remotePath in remotePaths){
                for(index in 0..localPath.items!!.size){
                    if(localPath.items!![index].title != remotePath.items?.get(index)!!.title) return false
                }
            }
        }
        */

        return true
    }

    private fun createPaths(skillList: List<SkillObject>, pathList: List<PathObject>): ArrayList<PathObject> {

        val pathObjectList: ArrayList<PathObject> = ArrayList()

        for (pathEntityItem in pathList) {
            val itemsList: ArrayList<SkillForView> = ArrayList()

            for (skillEntityItem in skillList) {
                var skillTitleList: List<String>? = pathEntityItem.items?.map { skillItem -> skillItem.title }

                if (skillTitleList?.contains(skillEntityItem.title) == true) itemsList.add(
                    SkillForView(
                        title = skillEntityItem.title,
                        status = skillEntityItem.status
                    )
                )
            }

            pathObjectList.add(
                PathObject(
                    title = pathEntityItem.title,
                    items = itemsList.map { skillItem -> skillItem.toSkillObject() }
                )
            )
        }

        return pathObjectList
    }
}