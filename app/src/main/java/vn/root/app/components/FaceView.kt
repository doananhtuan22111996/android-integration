package vn.root.app.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import vn.main.app.R
import vn.root.app.pages.draws.face.Face
import kotlin.math.min

class FaceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStrokeColor: Int? = null
    private var mStrokeWidth: Float? = null
    private var mFaceColor: Int? = null
    private var mEyeColor: Int? = null
    private var mMouthColor: Int? = null
    private var mSize = 0
    private var mFaceType: Face = Face.Happy

    init {
        context?.theme?.obtainStyledAttributes(attrs, R.styleable.FaceView, 0, 0)?.apply {
            try {
                mFaceColor = getColor(
                    R.styleable.FaceView_face_color,
                    ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primaryFixed)
                )
                mEyeColor = getColor(
                    R.styleable.FaceView_eye_color,
                    ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primary)
                )
                mMouthColor = getColor(
                    R.styleable.FaceView_mouth_color,
                    ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primary)
                )
                mStrokeColor = getColor(
                    R.styleable.FaceView_stroke_color,
                    ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primary)
                )
                mStrokeWidth = getDimension(
                    R.styleable.FaceView_stroke_width, 1f
                )
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        onDrawBgFace(canvas)
        onDrawEyes(canvas)
        onDrawMouth(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        // Because width and height are equal()
        mSize = min(width, height)
        setMeasuredDimension(mSize, mSize)
    }

    private fun onDrawBgFace(canvas: Canvas) {
        val padding = listOf(paddingStart, paddingEnd, paddingTop, paddingBottom).max()
        // Draw stroke
        paint.color = ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primary)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = mStrokeWidth ?: 1f
        canvas.drawCircle(mSize / 2f, mSize / 2f, mSize / 2f - padding, paint)
        // Draw circle center screen
        paint.color = mFaceColor ?: ContextCompat.getColor(
            context,
            vn.core.libx.mdc.R.color.md_theme_primaryFixed
        )
        paint.style = Paint.Style.FILL
        // Draw circle center screen
        canvas.drawCircle(mSize / 2f, mSize / 2f, mSize / 2f - paint.strokeWidth - padding, paint)
    }

    private fun onDrawEyes(canvas: Canvas) {
        paint.color =
            mEyeColor ?: ContextCompat.getColor(context, vn.core.libx.mdc.R.color.md_theme_primary)
        paint.style = Paint.Style.FILL
        val radius = mSize / 8f
        val left = RectF(radius * 2f, radius, radius * 3f, radius * 3)
        val right = RectF(radius * 5f, radius, radius * 6f, radius * 3)
        canvas.drawOval(left, paint)
        canvas.drawOval(right, paint)
    }

    private fun onDrawMouth(canvas: Canvas) {
        paint.color = mMouthColor ?: ContextCompat.getColor(
            context,
            vn.core.libx.mdc.R.color.md_theme_primary
        )
        paint.style = Paint.Style.FILL
        val radius = mSize / 8f
        val mouthPath = Path()
        if (mFaceType == Face.Happy) {
            val pointStart = Pair(radius, radius * 5f)
            val pointEnd = Pair(radius * 7f, radius * 5f)
            val point3 = Pair(radius * 4f, radius * 6f)
            val point4 = Pair(radius * 4f, radius * 8f)
            // Move to point 1
            mouthPath.moveTo(pointStart.first, pointStart.second)
            // Draw to point 2
            mouthPath.quadTo(point3.first, point3.second, pointEnd.first, pointEnd.second)
            // Draw to point 2 -> point 3 -> back to point start
            mouthPath.quadTo(point4.first, point4.second, pointStart.first, pointStart.second)
        } else {
            val pointStart = Pair(radius, radius * 5f)
            val pointEnd = Pair(radius * 7f, radius * 5f)
            val point3 = Pair(radius * 4f, radius * 3f)
            val point4 = Pair(radius * 4f, radius * 2f)
            // Move to point 1
            mouthPath.moveTo(pointStart.first, pointStart.second)
            // Draw to point 2
            mouthPath.quadTo(point3.first, point3.second, pointEnd.first, pointEnd.second)
            // Draw to point 2 -> point 3 -> back to point start
            mouthPath.quadTo(point4.first, point4.second, pointStart.first, pointStart.second)
        }
        canvas.drawPath(mouthPath, paint)
    }

    fun setFaceType(type: Face) {
        if (mFaceType == type) {
            return
        }
        mFaceType = type
        invalidate()
        requestLayout()
    }
}