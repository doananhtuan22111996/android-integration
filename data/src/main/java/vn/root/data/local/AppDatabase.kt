package vn.root.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.root.data.model.ItemRaw

@Database(entities = [ItemRaw::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}
