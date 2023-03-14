package uz.frankie.mahalla.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText

class CustomEditText(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (canScrollVertically(1) || canScrollVertically(-1)) {
            parent.requestDisallowInterceptTouchEvent(true)
            when (event?.actionMasked) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        return super.onTouchEvent(event)
    }
}


