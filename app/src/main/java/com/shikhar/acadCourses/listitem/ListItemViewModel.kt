
package com.shikhar.acadCourses.listitem

data class ListItemViewModel(var listItem: ListItem) {

  fun setCourseNumber(Number: String) {
    if (Number.isNotEmpty())
      listItem.itemID = Number
  }

  fun getCourseNumber(): String {
    return listItem.itemID
  }
}