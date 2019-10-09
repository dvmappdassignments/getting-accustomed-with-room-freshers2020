
package com.shikhar.acadCourses.listitem

data class ListItemViewModel(var listItem: ListItem) {

  fun setPriority(priority: String) {
    if (priority.isNotEmpty())
      listItem.itemID = Integer.valueOf(priority)
  }

  fun getPriority(): String {
    return listItem.itemID.toString()
  }
}