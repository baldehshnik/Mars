package com.firstapplication.mars.ui.utils

import android.animation.Animator
import androidx.lifecycle.MutableLiveData
import androidx.transition.Transition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.setValueWithMainDispatcher(newValue: T) {
    withContext(Dispatchers.Main) {
        this@setValueWithMainDispatcher.value = newValue
    }
}

fun getAnimationListener(onAnimationStart: (animation: Animator) -> Unit): Animator.AnimatorListener {
    return object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart(animation)
        }

        override fun onAnimationEnd(animation: Animator) {}

        override fun onAnimationCancel(animation: Animator) {}

        override fun onAnimationRepeat(animation: Animator) {}
    }
}

fun getTransitionListener(onStartTransition: () -> Unit): Transition.TransitionListener {
    return object : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) {
            onStartTransition()
        }

        override fun onTransitionEnd(transition: Transition) {}

        override fun onTransitionCancel(transition: Transition) {}

        override fun onTransitionPause(transition: Transition) {}

        override fun onTransitionResume(transition: Transition) {}
    }
}