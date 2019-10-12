
package com.shikhar.acadCourses.listitem

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.shikhar.acadCourses.R
import com.shikhar.acadCourses.databinding.ActivityListItemsBinding
import com.shikhar.acadCourses.databinding.ContentListItemsBinding
import com.shikhar.acadCourses.databinding.DialogAddItemBinding
import com.shikhar.acadCourses.listcategory.ListCategory

class ListItemsActivity : AppCompatActivity() {

  companion object {
    val LIST_CATEGORY_ID = "LIST_CATEGORY_ID"
    val CATEGORY_NAME = "CATEGORY_NAME"
  }

  private lateinit var activityListItemsBinding: ActivityListItemsBinding
  private lateinit var contentListItemsBinding: ContentListItemsBinding
  private lateinit var listItemAdapter: ListItemAdapter
  private lateinit var listCategory: ListCategory

  private lateinit var listItemsViewModel: ListItemsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    activityListItemsBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_list_items)

    val extras = intent.extras
    listCategory = ListCategory(extras.getString(CATEGORY_NAME), extras.getLong(LIST_CATEGORY_ID))
    supportActionBar?.title = listCategory.categoryName

    listItemsViewModel = ViewModelProviders.of(this).get(ListItemsViewModel::class.java)
    // call our function to set up the add  button logic
    setupAddButton()

    setupRecyclerAdapter()
  }

  /**
   * sets up the  button to launch a dialog allowing the user to add an item to their list.
   */
  private fun setupAddButton() {
    activityListItemsBinding.fab.setOnClickListener {

      // Setup the dialog
      val alertDialogBuilder = AlertDialog.Builder(this).setTitle("Name")
      val dialogAddItemBinding = DialogAddItemBinding.inflate(layoutInflater)
      // 1
      val listItemViewModel = ListItemViewModel(ListItem("", "", listCategory.id))
      dialogAddItemBinding.listItemViewModel = listItemViewModel

      alertDialogBuilder.setView(dialogAddItemBinding.root)

      /**
       * When the user clicks ok, a record is added to the db,
       * the db is queried and the RecyclerView is updated.
       */
      alertDialogBuilder.setPositiveButton(android.R.string.ok) { dialog: DialogInterface,
                                                                  which: Int ->
        // 2
        listItemsViewModel.insertAll(listItemViewModel.listItem)
      }
      alertDialogBuilder.setNegativeButton(android.R.string.cancel, null)
      alertDialogBuilder.show()
    }
  }

  private fun setupRecyclerAdapter() {
    val recyclerViewLinearLayoutManager = LinearLayoutManager(this)

    contentListItemsBinding = activityListItemsBinding.listItemsViewInclude!!
    contentListItemsBinding.listItemRecyclerView.layoutManager = recyclerViewLinearLayoutManager
    listItemAdapter = ListItemAdapter(listOf(), this)
    listItemsViewModel.getAllByListCategoryId(listCategory.id).observe(this, Observer { listItems: List<ListItem>? ->
      listItems?.let {
        listItemAdapter.itemList = it
        listItemAdapter.notifyDataSetChanged()
      }
    })
    contentListItemsBinding.listItemRecyclerView.adapter = listItemAdapter
  }
}
