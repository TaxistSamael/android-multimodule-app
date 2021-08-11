package com.example.japan.navigation.task

import com.example.japan.navigation.router.DoterraRouter
import com.github.terrakok.cicerone.Cicerone

class TaskCiceroneHolder {
  private val containers = HashMap<String, Cicerone<DoterraRouter>>()
  private var currentTask: String? = null

  fun getCicerone(containerTag: String): Cicerone<DoterraRouter> {
    return containers.getOrPut(containerTag) { Cicerone.create(DoterraRouter()) }
  }

  fun getCurrentTaskCicerone(): Cicerone<DoterraRouter> {
    val currentTask =
        this.currentTask ?: throw IllegalStateException("Current task is not set.")
    return getCicerone(currentTask)
  }

  fun getCurrentTask() = currentTask

  fun setCurrentTask(value: String) {
    currentTask = value
  }

  fun finishTask(value: String) {
    containers.remove(value)
  }
}
