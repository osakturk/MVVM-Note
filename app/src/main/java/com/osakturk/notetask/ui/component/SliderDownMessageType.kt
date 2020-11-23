package com.osakturk.notetask.ui.component

import android.animation.ValueAnimator
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.osakturk.notetask.R
import com.osakturk.notetask.util.toPx
import kotlinx.android.synthetic.main.slide_down_message.view.*

enum class SliderDownMessageType(@ColorRes val colorResourceId: Int) {
    WARNING(R.color.lightRed),
    INFO(R.color.validGreen)
}

class SlideDownMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.slide_down_message, this, true)
        initializeComponents()
    }

    private fun initializeComponents() {

    }

    fun openSlideDown(message : String, messageType: SliderDownMessageType = SliderDownMessageType.WARNING) {
        slideDown.setBackgroundColor(ContextCompat.getColor(context,messageType.colorResourceId))
        slideDownText.text = message
        val anim = ValueAnimator.ofInt((-46).toPx(), 0.toPx())
        anim.addUpdateListener {
            val value = it.animatedValue as Int
            val layoutParams = slideDown.layoutParams as FrameLayout.LayoutParams
            layoutParams.topMargin = value
            slideDown.layoutParams = layoutParams
        }
        anim.duration = 500
        anim.start()
        slideDownMenuTimer.cancel()
        slideDownMenuTimer.start()
    }

    fun closeSlideDown() {
        slideDownMenuTimer.cancel()
        val anim = ValueAnimator.ofInt(0.toPx(), (-46).toPx())
        anim.addUpdateListener {
            val value = it.animatedValue as Int
            val layoutParams = slideDown.layoutParams as FrameLayout.LayoutParams
            layoutParams.topMargin = value
            slideDown.layoutParams = layoutParams
        }
        anim.duration = 500
        anim.start()
    }

    private val slideDownMenuTimer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            closeSlideDown()
        }
    }
}
