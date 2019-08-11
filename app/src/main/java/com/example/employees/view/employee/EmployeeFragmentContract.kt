package com.example.employees.view.employee

import android.graphics.Bitmap

interface EmployeeFragmentContract {
    interface View {
        fun setAvatar(avatar: Bitmap)
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
        fun setBirthday(birthday: String)
        fun setAge(age: String)
        fun setSpecialties(specialties: String)
    }
    interface Presenter {
        fun onViewCreated(view: View)
        fun onDestroyView()
    }
}