package com.gabez.pathvisionapp.app.statusHolders

import com.gabez.pathvisionapp.domain.entities.PathObject


class DbSkillCountHolder {
    private var skillCountInDb: HashMap<String, Int> = HashMap()

    fun isInDb(skill: String): Boolean =  skillCountInDb[skill] != null
    fun isInOtherPath(skill: String) = skillCountInDb[skill]!! > 1

    fun refreshSkillHolder(pathList: List<PathObject>){
        skillCountInDb = HashMap()

        for(item in pathList){
            for(skill in item.items!!){
                if(skillCountInDb[skill.title] == null) skillCountInDb[skill.title] = 1
                else skillCountInDb[skill.title]?.plus(1)
            }
        }
    }
}