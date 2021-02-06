package com.gabez.pathvisionapp.app.search.entities

val searchMockData = arrayListOf(
    PathForSearch(
        title = "Junior Android Developer",
        status = PathStatus.ADDED,
        items = arrayListOf(
            SkillForSearch(
                title = "Kotlin"
            ),

            SkillForSearch(
                title = "Android SDK"
            ),

            SkillForSearch(
                title = "Object oriented programming"
            ),

            SkillForSearch(
                title = "English"
            ),

            SkillForSearch(
                title = "Jira"
            )
        )
    ),

    PathForSearch(
        title = "Project manager",
        status = PathStatus.NOT_ADDED,
        items = arrayListOf(
            SkillForSearch(
                title = "Scrum"
            ),

            SkillForSearch(
                title = "Jira"
            ),

            SkillForSearch(
                title = "English"
            )
        )
    ),

    PathForSearch(
        title = "UI designer",
        status = PathStatus.NOT_ADDED,
        items = arrayListOf(
            SkillForSearch(
                title = "Prototyping"
            ),

            SkillForSearch(
                title = "Photoshop"
            ),

            SkillForSearch(
                title = "English"
            )
        )
    ),

    PathForSearch(
        title = "Social media manager",
        status = PathStatus.NOT_ADDED,
        items = arrayListOf(
            SkillForSearch(
                title = "Prototyping"
            ),

            SkillForSearch(
                title = "Photoshop"
            ),

            SkillForSearch(
                title = "English"
            )
        )
    )
)