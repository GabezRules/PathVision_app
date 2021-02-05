package com.gabez.pathvisionapp.app.paths.entities

val mainMockData = arrayListOf(
    PathForView(
        title = "Junior Android Developer",
        items = arrayListOf(
            SkillForView(
                status = SkillStatus.DONE,
                title = "Kotlin"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Android SDK"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Object oriented programming"
            ),

            SkillForView(
                status = SkillStatus.IN_PROGRESS,
                title = "English"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Jira"
            )
        )
    ),

    PathForView(
        title = "Project manager",
        items = arrayListOf(
            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Scrum"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Jira"
            ),

            SkillForView(
                status = SkillStatus.IN_PROGRESS,
                title = "English"
            )
        )
    ),

    PathForView(
        title = "UI designer",
        items = arrayListOf(
            SkillForView(
                status = SkillStatus.DONE,
                title = "Prototyping"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Photoshop"
            ),

            SkillForView(
                status = SkillStatus.IN_PROGRESS,
                title = "English"
            )
        )
    ),

    PathForView(
        title = "Social media manager",
        items = arrayListOf(
            SkillForView(
                status = SkillStatus.DONE,
                title = "Prototyping"
            ),

            SkillForView(
                status = SkillStatus.EMPTY,
                title = "Photoshop"
            ),

            SkillForView(
                status = SkillStatus.IN_PROGRESS,
                title = "English"
            )
        )
    )
)