package com.gabez.pathvisionapp.domain.entities

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus

data class SkillObject(var title: String = "", var status: SkillStatus = SkillStatus.EMPTY)
