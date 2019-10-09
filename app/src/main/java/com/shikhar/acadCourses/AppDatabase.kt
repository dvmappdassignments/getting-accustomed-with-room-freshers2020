
package com.shikhar.acadCourses

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.support.annotation.VisibleForTesting
import com.shikhar.acadCourses.listcategory.ListCategory
import com.shikhar.acadCourses.listcategory.ListCategoryDao
import com.shikhar.acadCourses.listitem.ListItem
import com.shikhar.acadCourses.listitem.ListItemDao
import com.shikhar.acadCourses.migrations.Migration1To2
import com.shikhar.acadCourses.migrations.Migration2To3

//1
@Database(entities = [ListCategory::class, ListItem::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

  abstract fun listCategoryDao(): ListCategoryDao

  abstract fun listItemDao(): ListItemDao

  companion object {


    val MIGRATION_1_TO_2 = Migration1To2()
    //2

    val MIGRATION_2_TO_3 = Migration2To3()
  }
}
