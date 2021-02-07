package com.gabez.pathvisionapp.domain.entities

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus

data class SkillObj(var name: String = "", var status: SkillStatus = SkillStatus.EMPTY)