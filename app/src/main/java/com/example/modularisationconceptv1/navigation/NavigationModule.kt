package com.example.modularisationconceptv1.navigation

import com.example.modularisationconceptv1.navigation.router.DoterraRouter
import com.example.modularisationconceptv1.navigation.router.type.RouterType
import com.example.modularisationconceptv1.navigation.screen.factory.DefaultScreenFactory
import com.example.modularisationconceptv1.navigation.screen.factory.ScreenFactory
import com.example.modularisationconceptv1.navigation.task.TaskCiceroneHolder
import com.github.terrakok.cicerone.Cicerone
import org.koin.core.qualifier.named
import org.koin.dsl.module

val navigationModule = module {
    val cicerone = Cicerone.create(DoterraRouter())

    factory<DoterraRouter>(named(RouterType.Root)) { cicerone.router }
    factory<DoterraRouter>(named(RouterType.Task)) { get<TaskCiceroneHolder>().getCurrentTaskCicerone().router }

    single { cicerone.getNavigatorHolder() }
    single { TaskCiceroneHolder() }

    single<ScreenFactory> { DefaultScreenFactory() }
}