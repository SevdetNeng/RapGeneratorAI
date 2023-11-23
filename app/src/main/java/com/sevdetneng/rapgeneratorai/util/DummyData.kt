package com.sevdetneng.rapgeneratorai.util

import android.content.res.loader.ResourcesProvider
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.model.data.Category
import com.sevdetneng.rapgeneratorai.model.data.Prompt

object DummyData {
    val categories : List<Category> = listOf(
        Category(Categories.FUN.getName(),true),
        Category(Categories.HAPPY.getName(),false),
        Category(Categories.LOVE.getName(),false),
        Category(Categories.SAD.getName(),false),
        Category(Categories.SEXY.getName(),false)
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