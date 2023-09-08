package com.mattiaferigutti.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mattiaferigutti.core.data.entities.LocalTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

  @Query("SELECT * FROM tasks")
  fun getAllTasks(): Flow<List<LocalTask>>

  @Query("SELECT * FROM tasks WHERE completed_date IS -1")
  fun getUncompletedTasks(): Flow<List<LocalTask>>

  @Query("SELECT * FROM tasks WHERE id = :id")
  fun getTaskById(id: Long): Flow<LocalTask>

  @Insert
  suspend fun insertTask(task: LocalTask)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateTask(task: LocalTask)

  @Delete
  suspend fun deleteTask(task: LocalTask)

}