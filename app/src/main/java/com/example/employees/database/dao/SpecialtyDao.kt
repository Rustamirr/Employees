package com.example.employees.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Specialty
import io.reactivex.Observable

@Dao
abstract class SpecialtyDao {

    fun insert(specialty: Specialty){
        insertEntitySpecialty(EntitySpecialty(specialty))
    }

    fun getAll(): Observable<List<Specialty>> =
        getAllEntitySpecialty().map { entitySpecialties ->
            entitySpecialties.map { Specialty(it.id, it.name)}
        }

    @Insert
    abstract fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)

    @Query("Select * From Specialties")
    abstract fun getAllEntitySpecialty(): Observable<List<EntitySpecialty>>
}