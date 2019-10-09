
package com.shikhar.acadCourses.listcategory

import android.util.Log
import com.shikhar.acadCourses.R

data class ListCategoryViewModel(val listCategory: ListCategory = ListCategory("")) {

  private val highlightColors = arrayOf(
      R.color.colorPrimary,
      R.color.colorPrimaryDark,
      R.color.colorAccent,
      R.color.primaryLightColor,
      R.color.secondaryLightColor,
      R.color.secondaryDarkColor)

  fun getHighlightLetter(): String {
    return listCategory.categoryName.first().toString()
  }

  fun getHighlightLetterColor(): Int {
    val uniqueIdMultiplier = getHighlightLetter().hashCode().div(6)
    val colorArrayIndex = getHighlightLetter().hashCode() - (uniqueIdMultiplier * 6)
    Log.i("color", colorArrayIndex.toString())
    return (highlightColors[colorArrayIndex])
  }
}
