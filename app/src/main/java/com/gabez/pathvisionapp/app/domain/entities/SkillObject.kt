package com.gabez.pathvisionapp.app.domain.entities

import com.gabez.pathvisionapp.app.view.paths.entities.SkillStatus

data class SkillObject(var title: String = "", var status: SkillStatus = SkillStatus.EMPTY)
