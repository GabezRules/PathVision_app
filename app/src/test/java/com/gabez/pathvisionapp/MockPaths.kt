package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import com.gabez.pathvisionapp.app.view.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.view.search.entities.PathStatus

val mockPaths = arrayListOf<PathObject>(
    PathObject(
        title = "Android Developer",
        items = arrayListOf<SkillObject>(
            SkillObject("Kotlin", SkillStatus.DONE),
            SkillObject("English", SkillStatus.DONE),
            SkillObject("Java", SkillStatus.EMPTY)
        ),
        status = PathStatus.ADDED
    ),

    PathObject(
        title = "Project Manager",
        items = arrayListOf<SkillObject>(
            SkillObject("Jira", SkillStatus.IN_PROGRESS),
            SkillObject("Soft skills", SkillStatus.DONE),
            SkillObject("Java", SkillStatus.EMPTY)
        ),
        status = PathStatus.ADDED
    ),

    PathObject(
        title = "UX designer",
        items = arrayListOf<SkillObject>(
            SkillObject("Figma", SkillStatus.EMPTY),
            SkillObject("A/B Testing", SkillStatus.EMPTY),
            SkillObject("English", SkillStatus.EMPTY)
        ),
        status = PathStatus.ADDED
    )
)