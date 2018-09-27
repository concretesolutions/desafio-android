package br.com.desafio.concrete.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.com.desafio.concrete.R

/**
 * Created by Malkes on 25/09/2018.
 */
enum class ActivityAnimation {
    TRANSLATE_LEFT, TRANSLATE_RIGHT, TRANSLATE_UP, TRANSLATE_DOWN, TRANSLATE_FADE
}

inline fun <reified T : Activity> Activity.launchActivity(extras: Bundle? = null,
                                                          animation: ActivityAnimation? =
                                                           ActivityAnimation.TRANSLATE_LEFT) {
    val intent = Intent(this, T::class.java)

    extras?.let {
        intent.putExtras(it)
    }
    startActivity(intent)

    val animations = getAnimation(animation)
    overridePendingTransition(animations[0], animations[1])
}

fun Activity.finishActivity(animation: ActivityAnimation? = ActivityAnimation.TRANSLATE_RIGHT) {
    finish()
    val animations = getAnimation(animation)
    overridePendingTransition(animations[0], animations[1])
}

fun getAnimation(animation: ActivityAnimation?): IntArray {
    val exitAnim: Int
    val enterAnim: Int

    when (animation) {

        ActivityAnimation.TRANSLATE_UP -> {
            enterAnim = R.anim.translate_slide_up
            exitAnim = R.anim.translate_no_change
        }

        ActivityAnimation.TRANSLATE_DOWN -> {
            enterAnim = R.anim.translate_no_change
            exitAnim = R.anim.translate_slide_down
        }

        ActivityAnimation.TRANSLATE_RIGHT -> {
            enterAnim = R.anim.translate_right_enter
            exitAnim = R.anim.translate_right_exit
        }

        ActivityAnimation.TRANSLATE_FADE -> {
            enterAnim = R.anim.translate_fade_in
            exitAnim = R.anim.translate_fade_out
        }

        else -> {
            enterAnim = R.anim.translate_left_enter
            exitAnim = R.anim.translate_left_exit
        }
    }

    return intArrayOf(enterAnim, exitAnim)
}