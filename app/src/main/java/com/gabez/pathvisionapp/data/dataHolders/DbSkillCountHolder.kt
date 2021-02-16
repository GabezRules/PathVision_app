package com.gabez.pathvisionapp.data.dataHolders


class DbSkillCountHolder {
    private var skillCountInDb: HashMap<String, Int> = HashMap()

    fun isInDb(skill: String): Boolean =  skillCountInDb[skill] != null
    fun isInOtherPath(skill: String) = skillCountInDb[skill]!! > 1

    fun refreshSkillHolder(pathList: List<PathEntity>){
        skillCountInDb = HashMap()

        for(item in pathList){
            for(skill in item.relatedSkills.split(";;")){
                if(skillCountInDb[skill] == null) skillCountInDb[skill] = 1
                else skillCountInDb[skill]?.plus(1)
            }
        }
    }
}