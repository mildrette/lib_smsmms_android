package com.afkanerd.smswithoutborders_libsmsmms.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afkanerd.smswithoutborders_libsmsmms.data.dao.ConversationsDao
import com.afkanerd.smswithoutborders_libsmsmms.data.dao.ThreadsDao
import com.afkanerd.smswithoutborders_libsmsmms.data.entities.Conversations
import com.afkanerd.smswithoutborders_libsmsmms.data.entities.Threads
import kotlin.concurrent.Volatile

@Database(
    entities = [
        Conversations::class,
        Threads::class],
    version = 1,
    exportSchema = true
)
abstract class DatabaseImpl : RoomDatabase() {
    abstract fun conversationsDao(): ConversationsDao?
    abstract fun threadsDao(): ThreadsDao?

    companion object {
        @Volatile
        private var datastore: DatabaseImpl? = null
        private var databaseName: String = "afkanerd.DekuSms"

        @Synchronized
        fun setDatabaseName(databaseName: String) {
            this.databaseName = databaseName
        }

        @Synchronized
        fun getDatabaseImpl(context: Context): DatabaseImpl {
            if (datastore == null) {
                datastore = create(context)
            }
            return datastore!!
        }

        private fun create(context: Context): DatabaseImpl {
            return Room.databaseBuilder(
                context,
                DatabaseImpl::class.java,
                databaseName
            ).enableMultiInstanceInvalidation().build()
        }
    }
}
