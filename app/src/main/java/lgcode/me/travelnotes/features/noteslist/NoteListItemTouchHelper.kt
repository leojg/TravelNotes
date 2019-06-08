package lgcode.me.travelnotes.features.noteslist

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class NoteListItemTouchHelper(
    listener: NoteListItemTouchHelperCallback.NoteListItemTouchHelperListener,
    callback: NoteListItemTouchHelperCallback = NoteListItemTouchHelperCallback(listener)): ItemTouchHelper(callback) {

    class NoteListItemTouchHelperCallback(val listener: NoteListItemTouchHelperListener) : ItemTouchHelper.SimpleCallback(0, LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean = true

        override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                 dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            val foregroundView = (viewHolder as NoteListAdapter.NoteListViewHolder).binding.foregroundView
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
        }

        override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                     dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            val foregroundView = (viewHolder as NoteListAdapter.NoteListViewHolder).binding.foregroundView
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,actionState, isCurrentlyActive)
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
            getDefaultUIUtil().clearView((viewHolder as NoteListAdapter.NoteListViewHolder).binding.foregroundView)

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            listener.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
        }

        interface NoteListItemTouchHelperListener {
            fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
        }

    }

}