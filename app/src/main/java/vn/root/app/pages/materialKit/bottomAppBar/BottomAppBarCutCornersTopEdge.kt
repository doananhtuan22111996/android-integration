/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.root.app.pages.materialKit.bottomAppBar

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.ShapePath

/**
 * A [BottomAppBar] top edge that works with a Diamond shaped [FloatingActionButton]
 */
class BottomAppBarCutCornersTopEdge internal constructor(
	private val fabMargin: Float,
	roundedCornerRadius: Float,
	private val cradleVerticalOffset: Float
) : BottomAppBarTopEdgeTreatment(
	fabMargin, roundedCornerRadius, cradleVerticalOffset
) {
	@SuppressWarnings("RestrictTo")
	override fun getEdgePath(
		length: Float, center: Float, interpolation: Float, shapePath: ShapePath
	) {
		if (fabDiameter == 0f) {
			shapePath.lineTo(length, 0f)
			return
		}
		
		val diamondSize = fabDiameter / 2f
		val middle = center + horizontalOffset
		
		val verticalOffsetRatio = cradleVerticalOffset / diamondSize
		if (verticalOffsetRatio >= 1.0f) {
			shapePath.lineTo(length, 0f)
			return
		}
		
		shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 0f)
		
		shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation)
		
		shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 0f)
		
		shapePath.lineTo(length, 0f)
	}
}
