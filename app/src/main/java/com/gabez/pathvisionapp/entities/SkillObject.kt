package com.gabez.pathvisionapp.entities

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus

data class SkillObject(open var title: String = "", open var status: SkillStatus = SkillStatus.EMPTY)
