package com.multimodule.monolith.navigation

import android.app.Activity
import com.multimodule.monolith.navigation.module_router.PhotoPickerRouterImpl
import com.multimodule.monolith.navigation.module_router.ProfileRouterImpl
import com.multimodule.monolith.navigation.router.DoterraRouter
import com.multimodule.monolith.navigation.router.type.RouterType
import com.multimodule.monolith.navigation.screen.factory.DefaultScreenFactory
import com.multimodule.monolith.navigation.screen.factory.ScreenFactory
import com.multimodule.monolith.navigation.task.TaskCiceroneHolder
import com.example.photo_picker.PhotoPickerRouter
import com.example.profile.ProfileRouter
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

val navigationModule = module {
    val cicerone = Cicerone.create(DoterraRouter())

    factory<DoterraRouter>(named(RouterType.Root)) { cicerone.router }
    factory<DoterraRouter>(named(RouterType.Task)) { get<TaskCiceroneHolder>().getCurrentTaskCicerone().router }

    single { cicerone.getNavigatorHolder() }
    single { TaskCiceroneHolder() }

    single<ScreenFactory> { DefaultScreenFactory() }

    factory<ProfileRouter> { ProfileRouterImpl(getRootRouter(), get()) }
    factory<PhotoPickerRouter> { PhotoPickerRouterImpl(getRootRouter()) }
}

fun Scope.getRootRouter(): DoterraRouter = get(named(RouterType.Root))
fun Activity.getRootRouter(): DoterraRouter = get(named(RouterType.Root))
fun Scope.getTaskRouter(): DoterraRouter = get(named(RouterType.Task))
