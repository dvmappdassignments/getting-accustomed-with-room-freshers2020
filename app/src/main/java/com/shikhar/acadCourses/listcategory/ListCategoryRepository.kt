
package com.shikhar.acadCourses.listcategory

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.shikhar.acadCourses.ListMasterApplication

class ListCategoryRepository {

  private val listCategoryDao: ListCategoryDao
  val listCategories: LiveData<List<ListCategory>>

  init {
    val database = ListMasterApplication.database
    listCategoryDao = database!!.listCategoryDao()
    listCategories = listCategoryDao.getAll()
  }

  fun insertAll(vararg listCategories: ListCategory) {
    AsyncTask.execute {
      listCategoryDao.insertAll(*listCategories)
    }
  }
}