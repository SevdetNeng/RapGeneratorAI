package com.sevdetneng.rapgeneratorai.util

import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.domain.model.local.Category
import com.sevdetneng.rapgeneratorai.domain.model.local.Prompt
import com.sevdetneng.rapgeneratorai.domain.model.local.Rapper

object DummyData {
    val categories : List<Category> = listOf(
        Category(Categories.FUN.getName(),true),
        Category(Categories.HAPPY.getName(),false),
        Category(Categories.LOVE.getName(),false),
        Category(Categories.SAD.getName(),false),
        Category(Categories.SEXY.getName(),false)
    )

    val rapperImages : List<Int> = listOf(
        R.drawable.img_rapper1,
        R.drawable.img_rapper2,
        R.drawable.img_rapper3,
        R.drawable.img_rapper4,
        R.drawable.img_rapper5,
        R.drawable.img_rapper6,
        R.drawable.img_rapper7,
        R.drawable.img_rapper8,
        R.drawable.img_rapper9,
        R.drawable.img_rapper10,

    )

    val rappers : List<Rapper> = listOf(
        Rapper(uuid = "e76808ae-e81d-46a1-97cd-29bc3783d25b",rapperName = "Rose Urgent", rapperImg = R.drawable.img_rapper4,
            rapperVoice = R.raw.rose),
        Rapper(uuid = "f8f6d000-8e18-4af7-ae57-e19a8774d40c",rapperName = "Angus Upbeat", rapperImg = R.drawable.img_rapper1,
            rapperVoice = R.raw.angusupbeat),
        Rapper(uuid = "2ed05e1e-267e-49ac-8cd4-7a9686f57ad3",rapperName = "Loser Boy", rapperImg = R.drawable.img_rapper2,
            rapperVoice = R.raw.loserboy),
        Rapper(uuid = "23fb3c48-4115-4525-84c8-90dba2c290d6",rapperName = "Davo", rapperImg = R.drawable.img_rapper3,
            rapperVoice = R.raw.davo),
        Rapper(uuid = "769bdb7a-2763-4e2a-a4e5-d237727e033e",rapperName = "Kiwi", rapperImg = R.drawable.img_rapper5,
            rapperVoice = R.raw.kiwi),
        Rapper(uuid = "464814e0-efe1-42dc-afd4-588ff22f6e70",rapperName = "Frosty John", rapperImg = R.drawable.img_rapper6,
            R.raw.frostyjohn),
        Rapper(uuid ="423710f0-00b8-45ea-a349-632991c9d401" ,rapperName = "Angus Pleading", rapperImg = R.drawable.img_rapper7,
            R.raw.anguspleading),
        Rapper(uuid = "639f5a27-edbc-444f-bfe9-c7b62aa014f8",rapperName = "JSXI", rapperImg = R.drawable.img_rapper8,
            R.raw.jsxi),
        Rapper(uuid = "7b3e6d97-d557-4a8a-818e-b645a37577c0",rapperName = "Bertie",rapperImg =R.drawable.img_rapper9,
            R.raw.bertie),
        Rapper(uuid ="5cf1a6ee-488c-43d0-a5d3-3f17273120f3" ,rapperName = "Quackmaster", rapperImg = R.drawable.img_rapper10,
            R.raw.quackmaster)

    )


    val defaultPrompt = "A diss about my friend Jamie who is spending too much time with his dog"



    val prompts : List<Prompt> = listOf(
        Prompt(defaultPrompt,Categories.FUN.getName()),
        Prompt(defaultPrompt,Categories.FUN.getName()),
        Prompt(defaultPrompt,Categories.FUN.getName()),
        Prompt(defaultPrompt,Categories.FUN.getName()),
        Prompt(defaultPrompt,Categories.HAPPY.getName()),
        Prompt(defaultPrompt,Categories.HAPPY.getName()),
        Prompt(defaultPrompt,Categories.HAPPY.getName()),
        Prompt(defaultPrompt,Categories.HAPPY.getName()),
        Prompt(defaultPrompt,Categories.SAD.getName()),
        Prompt(defaultPrompt,Categories.SAD.getName()),
        Prompt(defaultPrompt,Categories.SAD.getName()),
        Prompt(defaultPrompt,Categories.SAD.getName()),
        Prompt(defaultPrompt,Categories.SEXY.getName()),
        Prompt(defaultPrompt,Categories.SEXY.getName()),
        Prompt(defaultPrompt,Categories.SEXY.getName()),
        Prompt(defaultPrompt,Categories.SEXY.getName()),
        Prompt(defaultPrompt,Categories.LOVE.getName()),
        Prompt(defaultPrompt,Categories.LOVE.getName()),
        Prompt(defaultPrompt,Categories.LOVE.getName()),
        Prompt(defaultPrompt,Categories.LOVE.getName()),
    )

}