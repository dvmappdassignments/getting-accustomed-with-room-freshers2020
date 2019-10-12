
package com.shikhar.acadCourses.listitem

import android.support.v7.widget.RecyclerView
import com.shikhar.acadCourses.databinding.HolderListItemBinding

data class ListItemViewHolder(
    private val holderListItemBinding: HolderListItemBinding,
    val listItemsActivity: ListItemsActivity) :
    RecyclerView.ViewHolder(holderListItemBinding.root) {

  fun setListItem(listItem: ListItem) {
    holderListItemBinding.listItemViewModel = ListItemViewModel(listItem)
    holderListItemBinding.executePendingBindings()
  }
}