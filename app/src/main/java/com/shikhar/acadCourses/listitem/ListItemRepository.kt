
package com.shikhar.acadCourses.listitem

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.shikhar.acadCourses.AcadCoursesApplication

class ListItemRepository {
  //1
  private val listItemDao = AcadCoursesApplication.database!!.listItemDao()

  //2
  fun insertAll(vararg listItems: ListItem) {
    AsyncTask.execute({
      listItemDao.insertAll(*listItems)
    })
  }

  fun getAllByListCategoryId(listCategoryId: Long): LiveData<List<ListItem>> {
    return listItemDao.getAllByListCategoryId(listCategoryId)
  }
}
